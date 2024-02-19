package com.iumutdikbasan.weatherapp.controller.contract;

import com.iumutdikbasan.weatherapp.dto.user.response.UserResponseDTO;

import java.util.List;

public interface UserControllerContract {
//    UserDTO save(UserSaveRequestDTO userSaveRequestDTO);

    List<UserResponseDTO> findAll();
}
