package com.iumutdikbasan.weatherapp.mapper;

import com.iumutdikbasan.weatherapp.dto.user.response.UserResponseDTO;
import com.iumutdikbasan.weatherapp.security.dto.AuthenticationRequestDTO;
import com.iumutdikbasan.weatherapp.security.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User convertToUser(AuthenticationRequestDTO authenticationRequestDTO);

    UserResponseDTO convertToUserDTO(User user);

    List<UserResponseDTO> convertToUserDtoList(List<User> userList);
}
