package net.learnjava.endpoint.authorization.config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
      return   http.httpBasic()
              .and()
              .authorizeRequests()
             // .anyRequest().permitAll() // permit all
             // .anyRequest().denyAll() // deny some requests
            //  .anyRequest().hasAuthority("read") // has only this rule
             // .anyRequest().hasAnyAuthority("read","write") // has any of these rules
             // .anyRequest().hasRole("ADMIN") // has only this role
             // .anyRequest().hasAnyRole("ADMIN", "USER") // has any of these roles
            //  .anyRequest().access("isAuthenticated() and hasRole('ADMIN')")// SpEl
              .mvcMatchers("/demo").hasAuthority("read")
              .anyRequest().authenticated()
               // endpoint level authorization
              .and()
              .build();

      // match method and authorization rule
        // 1. which matcher method to use and how (anyrequests(), mvcMatchers(),antMatchers(),reqexMatchers())
        // 2. how to apply different authorization rules
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.withUsername("nesreen")
                .password(passwordEncoder().encode("nesreen"))
                .authorities("read")
               // .roles("ADMIN") // equal to authority with ROLE_ADMIN
                .build();
        UserDetails user2 = User.withUsername("john")
                .password(passwordEncoder().encode("john"))
                .authorities("write")
               // .roles("USER")
                .build();
    var uData = new InMemoryUserDetailsManager();
    uData.createUser(user1);
    uData.createUser(user2);
    return uData;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
