package com.iumutdikbasan.weatherapp.repository;

import com.iumutdikbasan.weatherapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
