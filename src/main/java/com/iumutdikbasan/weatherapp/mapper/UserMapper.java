package com.iumutdikbasan.weatherapp.mapper;

import com.iumutdikbasan.weatherapp.dto.requests.UserRequestDTO;
import com.iumutdikbasan.weatherapp.dto.responses.UserResponseDTO;
import com.iumutdikbasan.weatherapp.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequestDTO userRequestDTO);
    UserRequestDTO toUserRequestDTO(User user);

    User toUser(UserResponseDTO userResponseDTO);
    UserResponseDTO toUserResponseDTO(User user);
}
