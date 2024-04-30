package com.iumutdikbasan.weatherapp.controller.contract;

import com.iumutdikbasan.weatherapp.dto.requests.UserRequestDTO;
import com.iumutdikbasan.weatherapp.dto.responses.UserResponseDTO;

import java.util.List;

public interface UserControllerContract {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    List<UserResponseDTO> getUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    void deleteUser(Long id);

    List<UserResponseDTO> getUsersPage(int pageNumber, int pageSize);

    List<UserResponseDTO> getByUsernameStartsWith(String username);

    List<UserResponseDTO> getByUsernameContaining(String username);

    List<UserResponseDTO> getAllByOrderByUsernameAsc();
}
