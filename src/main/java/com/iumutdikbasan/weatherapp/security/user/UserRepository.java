package com.iumutdikbasan.weatherapp.security.user;

import com.iumutdikbasan.weatherapp.entity.City;
import com.iumutdikbasan.weatherapp.general.BaseEntityService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);



}