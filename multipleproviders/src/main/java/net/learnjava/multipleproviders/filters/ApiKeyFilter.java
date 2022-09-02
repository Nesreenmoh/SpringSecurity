package net.learnjava.multipleproviders.filters;

import lombok.AllArgsConstructor;
import net.learnjava.multipleproviders.authentications.ApiKeyAuthentication;
import net.learnjava.multipleproviders.authentications.CustomAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CustomAuthenticationManager manager = new CustomAuthenticationManager(key);

        var requestKey = request.getHeader("x-api-key");
        System.out.println(requestKey);
        if("null".equals(requestKey) || requestKey == null){
            filterChain.doFilter(request,response);
        }
        ApiKeyAuthentication apiAuth = new ApiKeyAuthentication(requestKey);
        try{
        Authentication authenticate = manager.authenticate(apiAuth);

        if(authenticate.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            filterChain.doFilter(request,response);
        } else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        }catch (AuthenticationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
