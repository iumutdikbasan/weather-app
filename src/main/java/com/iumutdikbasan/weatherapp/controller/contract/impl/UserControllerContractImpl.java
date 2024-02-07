package com.iumutdikbasan.weatherapp.controller.contract.impl;

import com.iumutdikbasan.weatherapp.controller.contract.UserControllerContract;
import com.iumutdikbasan.weatherapp.dto.user.UserDTO;
import com.iumutdikbasan.weatherapp.mapper.UserMapper;
import com.iumutdikbasan.weatherapp.security.user.User;
import com.iumutdikbasan.weatherapp.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {

    private final UserEntityService service;
    private final UserMapper mapper;
//    @Override
//    public UserDTO save(UserSaveRequestDTO userSaveRequestDTO) {
//
//        String username = userSaveRequestDTO.username();
//        boolean isUsernameTaken = service.existByUsername(username);
//        if (isUsernameTaken) {
//            throw new BusinessException(UserErrorMessage.USERNAME_ALREADY_TAKEN);
//        }
//        User user = mapper.convertToUser(userSaveRequestDTO);
//
//        service.save(user);
//        return mapper.convertToUserDTO(user);
//    }

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = service.findAll();
        return mapper.convertToUserDtoList(userList);
    }
}
