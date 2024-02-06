package com.iumutdikbasan.weatherapp.mapper;

import com.iumutdikbasan.weatherapp.dto.user.UserDTO;
import com.iumutdikbasan.weatherapp.dto.user.UserSaveRequestDTO;
import com.iumutdikbasan.weatherapp.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User convertToUser(UserSaveRequestDTO userSaveRequestDTO);

    UserDTO convertToUserDTO(User user);

    List<UserDTO> convertToUserDtoList(List<User> userList);
}
