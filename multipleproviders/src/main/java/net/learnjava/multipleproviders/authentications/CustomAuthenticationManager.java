package net.learnjava.multipleproviders.authentications;

import lombok.RequiredArgsConstructor;
import net.learnjava.multipleproviders.providers.ApiKeyProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final String key;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        ApiKeyProvider apiKeyProvider = new ApiKeyProvider(key);
        if(apiKeyProvider.supports(authentication.getClass())) {
        return apiKeyProvider.authenticate(authentication);
        } else {
            throw new BadCredentialsException(":(");
        }

    }
}
