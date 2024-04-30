package com.iumutdikbasan.weatherapp.controller.contract.impl;

import com.iumutdikbasan.weatherapp.controller.contract.UserControllerContract;
import com.iumutdikbasan.weatherapp.dto.requests.UserRequestDTO;
import com.iumutdikbasan.weatherapp.dto.responses.UserResponseDTO;
import com.iumutdikbasan.weatherapp.exception.exceptions.MyException;
import com.iumutdikbasan.weatherapp.kafka.KafkaService;
import com.iumutdikbasan.weatherapp.mapper.UserMapper;
import com.iumutdikbasan.weatherapp.entity.User;
import com.iumutdikbasan.weatherapp.service.concretes.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.Optional;



@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {

    private final UserMapper userMapper;

    private final UserServiceImpl userServiceImpl;

    private final KafkaService kafkaService;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        String record = String.format("[%s:%d] createUser method called with userRequestDTO: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), userRequestDTO);
        kafkaService.sendMessage(record, "debug");

        User user = userServiceImpl.save(userMapper.toUser(userRequestDTO));
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);


        record = String.format("[%s:%d] User created: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), userResponseDTO);
        kafkaService.sendMessage(record, "info");
        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> getUsers() {

        String record = String.format("[%s:%d] getUsers method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record, "debug");

        List<UserResponseDTO> userResponseDTOS = userServiceImpl.findAll()
                .stream()
                .map(userMapper::toUserResponseDTO)
                .toList();

        if (userResponseDTOS.isEmpty()) {

            record = String.format("[%s:%d] No users found", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
            kafkaService.sendMessage(record, "warn");
            throw new MyException("Kullanıcı Bulunamadı");
        }


        record = String.format("[%s:%d] Retrieved %d users", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), userResponseDTOS.size());
        kafkaService.sendMessage(record, "info");
        return userResponseDTOS;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        String record = String.format("[%s:%d] getUserById method called with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
        kafkaService.sendMessage(record, "debug");

        Optional<User> user = userServiceImpl.findById(id);
        if (user.isPresent()) {
            UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user.get());

            record = String.format("[%s:%d] Retrieved user by id %d: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id, userResponseDTO);
            kafkaService.sendMessage(record, "info");
            return userResponseDTO;
        } else {

            record = String.format("[%s:%d] User not found with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
            kafkaService.sendMessage(record, "warn");
            throw new MyException("Kullanıcı Bulunamadı");
        }
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {

        String record = String.format("[%s:%d] updateUser method called with id: %d and userRequestDTO: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id, userRequestDTO);
        kafkaService.sendMessage(record, "debug");

        Optional<User> user = userServiceImpl.findById(id);
        if (user.isPresent()) {
            user.get().setUsername(userRequestDTO.username());
            user.get().setPassword(userRequestDTO.password());
            User updatedUser = userServiceImpl.save(user.get());
            UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(updatedUser);


            record = String.format("[%s:%d] User updated with id %d: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id, userResponseDTO);
            kafkaService.sendMessage(record, "info");
            return userResponseDTO;
        } else {

            record = String.format("[%s:%d] User not found with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
            kafkaService.sendMessage(record, "warn");
            throw new MyException("Kullanıcı Güncellenemedi");
        }
    }

    @Override
    public void deleteUser(Long id) {

        String record = String.format("[%s:%d] deleteUser method called with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
        kafkaService.sendMessage(record, "debug");

        Optional<User> user = userServiceImpl.findById(id);
        if (user.isPresent()) {
            userServiceImpl.delete(user.get());


            record = String.format("[%s:%d] User deleted with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
            kafkaService.sendMessage(record, "info");

        } else {

            record = String.format("[%s:%d] User not found with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
            kafkaService.sendMessage(record, "warn");
            throw new MyException("Kullanıcı Silinemedi");
        }
    }

    @Override
    public List<UserResponseDTO> getUsersPage(int pageNumber, int pageSize) {

        String record = String.format("[%s:%d] getUsersPage method called with pageNumber: %d and pageSize: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), pageNumber, pageSize);
        kafkaService.sendMessage(record, "debug");

        if (pageNumber < 0 || pageSize < 0) {

            record = String.format("[%s:%d] Invalid pageNumber or pageSize: %d, %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), pageNumber, pageSize);
            kafkaService.sendMessage(record, "warn");
            throw new MyException("Sayfa numarası ve sayfa boyutu 0'dan küçük olamaz");
        }

        List<User> users = userServiceImpl.getUsersPage(pageNumber, pageSize);
        List<UserResponseDTO> userResponseDTOS = users.stream()
                .map(userMapper::toUserResponseDTO)
                .toList();


        record = String.format("[%s:%d] Retrieved %d users for page number %d and page size %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), userResponseDTOS.size(), pageNumber, pageSize);
        kafkaService.sendMessage(record, "info");
        return userResponseDTOS;
    }

    @Override
    public List<UserResponseDTO> getByUsernameStartsWith(String username) {

        String record = String.format("[%s:%d] getByUsernameStartsWith method called with username: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), username);
        kafkaService.sendMessage(record, "debug");

        if (username == null || username.isEmpty()) {

            record = String.format("[%s:%d] Empty username provided", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
            kafkaService.sendMessage(record, "warn");
            throw new MyException("Boş bırakılamaz");
        }

        List<User> users = userServiceImpl.getByUsernameStartsWith(username);
        List<UserResponseDTO> userResponseDTOS = users.stream()
                .map(userMapper::toUserResponseDTO)
                .toList();


        record = String.format("[%s:%d] Retrieved %d users with username starting with: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), userResponseDTOS.size(), username);
        kafkaService.sendMessage(record, "info");

        return userResponseDTOS;
    }

    @Override
    public List<UserResponseDTO> getByUsernameContaining(String username) {

        String record = String.format("[%s:%d] getByUsernameContaining method called with username: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), username);
        kafkaService.sendMessage(record, "debug");

        if (username == null || username.isEmpty()) {

            record = String.format("[%s:%d] Empty username provided", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
            kafkaService.sendMessage(record, "warn");
            throw new MyException("Boş bırakılamaz");
        }

        List<User> users = userServiceImpl.getByUsernameContaining(username);
        List<UserResponseDTO> userResponseDTOS = users.stream()
                .map(userMapper::toUserResponseDTO)
                .toList();


        record = String.format("[%s:%d] Retrieved %d users with username containing: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), userResponseDTOS.size(), username);
        kafkaService.sendMessage(record, "info");
        return userResponseDTOS;
    }

    @Override
    public List<UserResponseDTO> getAllByOrderByUsernameAsc() {

        String record = String.format("[%s:%d] getAllByOrderByUsernameAsc method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record, "debug");

        List<User> users = userServiceImpl.getAllByOrderByUsernameAsc();
        List<UserResponseDTO> userResponseDTOS = users.stream()
                .map(userMapper::toUserResponseDTO)
                .toList();


        record = String.format("[%s:%d] Retrieved %d users ordered by username ascending", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), userResponseDTOS.size());
        kafkaService.sendMessage(record, "info");
        return userResponseDTOS;
    }
}
