package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Query("From User u JOIN FETCH u.roles where u.username = :username")
    User findUserByUsername(String username);

    @Transactional
    @Query("From User u JOIN FETCH u.roles")
    List<User> findAll();

    @Transactional
    @Query("From User u JOIN FETCH u.roles where u.id = :id")
    User getById(int id);
}
