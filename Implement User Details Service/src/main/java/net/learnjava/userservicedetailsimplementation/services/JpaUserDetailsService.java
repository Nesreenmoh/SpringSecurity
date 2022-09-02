package net.learnjava.userservicedetailsimplementation.services;

import net.learnjava.userservicedetailsimplementation.model.User;
import net.learnjava.userservicedetailsimplementation.repositories.UserRepository;
import net.learnjava.userservicedetailsimplementation.services.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
         User u = user.orElseThrow(()-> new UsernameNotFoundException("User is not found!"));
         return new SecurityUser(u);
    }
}
