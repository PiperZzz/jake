package spring.code.jake.myprojects.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.code.jake.myprojects.security.model.User;

public interface UsersRepository extends JpaRepository<User, Long> {

}
