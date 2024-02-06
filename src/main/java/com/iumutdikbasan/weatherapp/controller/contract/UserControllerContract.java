package com.iumutdikbasan.weatherapp.controller.contract;

import com.iumutdikbasan.weatherapp.dto.user.UserDTO;
import com.iumutdikbasan.weatherapp.dto.user.UserSaveRequestDTO;

import java.util.List;

public interface UserControllerContract {
    UserDTO save(UserSaveRequestDTO userSaveRequestDTO);

    List<UserDTO> findAll();
}
