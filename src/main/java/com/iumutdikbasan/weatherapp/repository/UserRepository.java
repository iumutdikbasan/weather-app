package com.iumutdikbasan.weatherapp.repository;

import com.iumutdikbasan.weatherapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);

    List<User> getByUsernameStartsWith(String username);

    List<User> getByUsernameContaining(String username);

    List<User> getAllByOrderByUsernameAsc();
}