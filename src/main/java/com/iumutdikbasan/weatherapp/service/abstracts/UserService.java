package com.iumutdikbasan.weatherapp.service.abstracts;


import com.iumutdikbasan.weatherapp.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllByOrderByUsernameAsc();

    List<User> getByUsernameContaining(String username);

    List<User> getByUsernameStartsWith(String username);

    List<User> getUsersPage(int pageNumber, int pageSize);

    User save(User user);
}