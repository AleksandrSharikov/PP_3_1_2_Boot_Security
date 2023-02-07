package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("From User u JOIN FETCH u.roles where u.username = :username")
    User findUserByUsername(String username);


   // @Query("From User u LEFT JOIN FETCH u.roles")
    List<User> findAll();


    @Query("From User u JOIN FETCH u.roles where u.id = :id")
    User getById(Long id);
}
