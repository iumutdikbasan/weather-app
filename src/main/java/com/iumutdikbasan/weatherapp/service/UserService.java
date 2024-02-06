package com.iumutdikbasan.weatherapp.service;

import com.iumutdikbasan.weatherapp.entity.User;
import com.iumutdikbasan.weatherapp.general.BaseEntityService;
import com.iumutdikbasan.weatherapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseEntityService<User, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}
