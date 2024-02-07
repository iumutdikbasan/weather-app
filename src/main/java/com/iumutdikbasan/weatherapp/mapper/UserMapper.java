package com.iumutdikbasan.weatherapp.mapper;

import com.iumutdikbasan.weatherapp.dto.user.UserDTO;
import com.iumutdikbasan.weatherapp.dto.user.UserSaveRequestDTO;
import com.iumutdikbasan.weatherapp.security.dto.AuthenticationRequestDTO;
import com.iumutdikbasan.weatherapp.security.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User convertToUser(AuthenticationRequestDTO authenticationRequestDTO);

    UserDTO convertToUserDTO(User user);

    List<UserDTO> convertToUserDtoList(List<User> userList);
}
