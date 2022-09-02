package net.learnjava.userservicedetailsimplementation.repositories;

import net.learnjava.userservicedetailsimplementation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer>{

    Optional<User> findUserByUsername(String username);
}
