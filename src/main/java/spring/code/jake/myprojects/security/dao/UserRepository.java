package spring.code.jake.myprojects.security.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.code.jake.myprojects.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
        Optional<User> findByUsername(String username);
}
