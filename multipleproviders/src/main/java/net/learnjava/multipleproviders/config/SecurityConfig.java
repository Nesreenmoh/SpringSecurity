package net.learnjava.multipleproviders.config;


import net.learnjava.multipleproviders.filters.ApiKeyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {



    @Value("${the.secret}")
    private String key;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic()
                .and()
                .addFilterBefore(new ApiKeyFilter(key), BasicAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()// authorization
                .and()
               // .and().authenticationManager() override the authentication Manager by adding a custom one/ or create a new one and add it as a bean
                //.and().authenticationProvider() it adds one more to the collections of available providers
                .build();
    }
}
