package com.iumutdikbasan.weatherapp.controller.contract.impl;

import com.iumutdikbasan.weatherapp.controller.contract.UserControllerContract;
import com.iumutdikbasan.weatherapp.dto.user.response.UserResponseDTO;
import com.iumutdikbasan.weatherapp.kafka.service.KafkaService;
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
    private final KafkaService kafkaService;



    @Override
    public List<UserResponseDTO> findAll() {
        List<User> userList = service.findAll();
        kafkaService.sendMessageInfo("Users returned!", "logs");
        return mapper.convertToUserDtoList(userList);
    }
}
