package com.iumutdikbasan.weatherapp.controller.contract.Impl;

import com.iumutdikbasan.weatherapp.controller.contract.impl.UserControllerContractImpl;
import com.iumutdikbasan.weatherapp.dto.requests.UserRequestDTO;
import com.iumutdikbasan.weatherapp.dto.responses.UserResponseDTO;
import com.iumutdikbasan.weatherapp.entity.User;
import com.iumutdikbasan.weatherapp.exception.exceptions.MyException;
import com.iumutdikbasan.weatherapp.kafka.KafkaService;
import com.iumutdikbasan.weatherapp.mapper.UserMapper;
import com.iumutdikbasan.weatherapp.service.concretes.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserControllerContractImplTest {
    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private KafkaService producerService;

    @Mock
    private UserMapper userMapper;

    private UserControllerContractImpl userControllerContract;

    @BeforeEach
    void setUp() {
        userControllerContract = new UserControllerContractImpl(userMapper,userServiceImpl, producerService);
    }

    @Test
    void shouldCreateUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO("john", "password");
        User user = new User(1L, "john", "password", new ArrayList<>());
        UserResponseDTO userResponseDTO = new UserResponseDTO(1L, "john", new ArrayList<>(), null);

        Mockito.when(userMapper.toUser(userRequestDTO)).thenReturn(user);
        Mockito.when(userServiceImpl.save(user)).thenReturn(user);
        Mockito.when(userMapper.toUserResponseDTO(user)).thenReturn(userResponseDTO);

        UserResponseDTO createdUser = userControllerContract.createUser(userRequestDTO);

        Assertions.assertEquals(userResponseDTO, createdUser);

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("info"));
    }

    @Test
    void shouldThrowExceptionWhenNoUsersFound() {
        Mockito.when(userServiceImpl.findAll()).thenReturn(new ArrayList<>());

        try {
            userControllerContract.getUsers();
            Assertions.fail("Exception expected but not thrown");
        } catch (MyException e) {
            Assertions.assertEquals("Kullanıcı Bulunamadı", e.getMessage());
        }

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("warn"));
    }

    @Test
    void shouldGetUsers() {
        User user = new User(1L, "john", "password", new ArrayList<>());
        UserResponseDTO userResponseDTO = new UserResponseDTO(1L, "john", new ArrayList<>(), null);
        List<User> userList = List.of(user);
        List<UserResponseDTO> userResponseDTOList = List.of(userResponseDTO);

        Mockito.when(userServiceImpl.findAll()).thenReturn(userList);
        Mockito.when(userMapper.toUserResponseDTO(user)).thenReturn(userResponseDTO);

        List<UserResponseDTO> retrievedUsers = userControllerContract.getUsers();

        Assertions.assertEquals(userResponseDTOList, retrievedUsers);

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("info"));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundById() {
        Long userId = 1L;

        Mockito.when(userServiceImpl.findById(userId)).thenReturn(Optional.empty());

        try {
            userControllerContract.getUserById(userId);
            Assertions.fail("Exception expected but not thrown");
        } catch (MyException e) {
            Assertions.assertEquals("Kullanıcı Bulunamadı", e.getMessage());
        }

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("warn"));
    }

    @Test
    void shouldGetUserById() {
        Long userId = 1L;
        User user = new User(userId, "john", "password", new ArrayList<>());
        UserResponseDTO userResponseDTO = new UserResponseDTO(userId, "john", new ArrayList<>(), user.getBaseAdditionalFields());

        Mockito.when(userServiceImpl.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userMapper.toUserResponseDTO(user)).thenReturn(userResponseDTO);

        UserResponseDTO retrievedUser = userControllerContract.getUserById(userId);

        Assertions.assertEquals(userResponseDTO, retrievedUser);

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("info"));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundForUpdate() {
        Long userId = 1L;
        UserRequestDTO userRequestDTO = new UserRequestDTO("john", "password");

        Mockito.when(userServiceImpl.findById(userId)).thenReturn(Optional.empty());

        try {
            userControllerContract.updateUser(userId, userRequestDTO);
            Assertions.fail("Exception expected but not thrown");
        } catch (MyException e) {
            Assertions.assertEquals("Kullanıcı Güncellenemedi", e.getMessage());
        }

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("warn"));
    }

    @Test
    void shouldUpdateUser() {
        Long userId = 1L;
        UserRequestDTO userRequestDTO = new UserRequestDTO("john", "password");
        User user = new User(userId, "old_username", "old_password", new ArrayList<>());
        User updatedUser = new User(userId, "john", "password", new ArrayList<>());

        UserResponseDTO userResponseDTO = new UserResponseDTO(userId, "john", new ArrayList<>(), null);

        Mockito.when(userServiceImpl.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userServiceImpl.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            user.setUsername(savedUser.getUsername());
            user.setPassword(savedUser.getPassword());
            return user;
        });

        Mockito.when(userMapper.toUserResponseDTO(Mockito.any(User.class))).thenReturn(userResponseDTO);

        UserResponseDTO updatedUserResponse = userControllerContract.updateUser(userId, userRequestDTO);

        Assertions.assertEquals(userResponseDTO, updatedUserResponse);

        // Verify that user fields are updated correctly
        Assertions.assertEquals("john", user.getUsername());
        Assertions.assertEquals("password", user.getPassword());

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("info"));
    }



    @Test
    void shouldThrowExceptionWhenUserNotFoundForDelete() {
        Long userId = 1L;

        Mockito.when(userServiceImpl.findById(userId)).thenReturn(Optional.empty());

        try {
            userControllerContract.deleteUser(userId);
            Assertions.fail("Exception expected but not thrown");
        } catch (MyException e) {
            Assertions.assertEquals("Kullanıcı Silinemedi", e.getMessage());
        }

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("warn"));
    }

    @Test
    void shouldDeleteUser() {
        Long userId = 1L;
        User user = new User(userId, "john", "password", new ArrayList<>());

        Mockito.when(userServiceImpl.findById(userId)).thenReturn(Optional.of(user));

        userControllerContract.deleteUser(userId);

        // Verify that user is deleted
        Mockito.verify(userServiceImpl).delete(user);

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("info"));
    }

    @Test
    void shouldThrowExceptionWhenInvalidPageNumberOrPageSizeForGetUsersPage() {
        int pageNumber = -1;
        int pageSize = 10;

        try {
            userControllerContract.getUsersPage(pageNumber, pageSize);
            Assertions.fail("Exception expected but not thrown");
        } catch (MyException e) {
            Assertions.assertEquals("Sayfa numarası ve sayfa boyutu 0'dan küçük olamaz", e.getMessage());
        }

        pageNumber = 0;
        pageSize = -1;

        try {
            userControllerContract.getUsersPage(pageNumber, pageSize);
            Assertions.fail("Exception expected but not thrown");
        } catch (MyException e) {
            Assertions.assertEquals("Sayfa numarası ve sayfa boyutu 0'dan küçük olamaz", e.getMessage());
        }

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService, Mockito.times(2)).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService, Mockito.times(2)).sendMessage(Mockito.anyString(), Mockito.eq("warn"));
    }

    @Test
    void shouldGetUsersPage() {
        int pageNumber = 1;
        int pageSize = 10;
        List<User> users = Arrays.asList(new User(1L, "john", "password", new ArrayList<>()));
        List<UserResponseDTO> userResponseDTOs = Arrays.asList(new UserResponseDTO(1L, "john", new ArrayList<>(), null));

        Mockito.when(userServiceImpl.getUsersPage(pageNumber, pageSize)).thenReturn(users);
        Mockito.when(userMapper.toUserResponseDTO(Mockito.any(User.class))).thenReturn(userResponseDTOs.get(0));

        List<UserResponseDTO> retrievedUsers = userControllerContract.getUsersPage(pageNumber, pageSize);

        Assertions.assertEquals(userResponseDTOs, retrievedUsers);

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("info"));
    }

    @Test
    void shouldThrowExceptionWhenEmptyUsernameForGetByUsernameStartsWith() {
        String username = "";

        try {
            userControllerContract.getByUsernameStartsWith(username);
            Assertions.fail("Exception expected but not thrown");
        } catch (MyException e) {
            Assertions.assertEquals("Boş bırakılamaz", e.getMessage());
        }

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("warn"));
    }

    @Test
    void shouldGetUsersByUsernameStartsWith() {
        String username = "jo";
        List<User> users = Arrays.asList(new User(1L, "john", "password", new ArrayList<>()));
        List<UserResponseDTO> userResponseDTOs = Arrays.asList(new UserResponseDTO(1L, "john", new ArrayList<>(), null));

        Mockito.when(userServiceImpl.getByUsernameStartsWith(username)).thenReturn(users);
        Mockito.when(userMapper.toUserResponseDTO(Mockito.any(User.class))).thenReturn(userResponseDTOs.get(0));

        List<UserResponseDTO> retrievedUsers = userControllerContract.getByUsernameStartsWith(username);

        Assertions.assertEquals(userResponseDTOs, retrievedUsers);

        // Verify that producerService.sendMessage() is called with the correct parameters
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("debug"));
        Mockito.verify(producerService).sendMessage(Mockito.anyString(), Mockito.eq("info"));
    }
}