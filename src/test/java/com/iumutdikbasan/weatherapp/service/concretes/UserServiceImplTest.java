package com.iumutdikbasan.weatherapp.service.concretes;

import com.iumutdikbasan.weatherapp.entity.User;
import com.iumutdikbasan.weatherapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        // Test verileri
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        // Beklenen sonuç
        User expectedUser = new User();
        expectedUser.setId(1L);
        expectedUser.setUsername("testuser");
        expectedUser.setPassword("encodedpassword");

        // Mock PasswordEncoder'nin davranışını ayarla
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedpassword");

        // Mock UserRepository'nin davranışını ayarla
        when(userRepository.save(user)).thenReturn(expectedUser);

        // Metodu çağır ve sonucu kontrol et
        User savedUser = userService.save(user);
        assertNotNull(savedUser);
        assertEquals(expectedUser.getUsername(), savedUser.getUsername());
        assertEquals(expectedUser.getPassword(), savedUser.getPassword());


        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUsersPage() {

        int pageNumber = 0;
        int pageSize = 10;
        List<User> expectedUsers = Arrays.asList(
                new User(1L, "user1", "password1",null),
                new User(2L, "user2", "password2",null),
                new User(3L, "user3", "password3",null)
        );
        Page<User> page = new PageImpl<>(expectedUsers);


        when(userRepository.findAll(any(Pageable.class))).thenReturn(page);


        List<User> users = userService.getUsersPage(pageNumber, pageSize);
        assertNotNull(users);
        assertEquals(expectedUsers.size(), users.size());


        verify(userRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testGetByUsernameStartsWith() {

        String username = "user";
        List<User> expectedUsers = Arrays.asList(
                new User(1L, "user1", "password1" ,null),
                new User(2L, "user2", "password2" ,null),
                new User(3L, "user3", "password3" ,null)
        );


        when(userRepository.getByUsernameStartsWith(username)).thenReturn(expectedUsers);


        List<User> users = userService.getByUsernameStartsWith(username);
        assertNotNull(users);
        assertEquals(expectedUsers.size(), users.size());


        verify(userRepository, times(1)).getByUsernameStartsWith(username);
    }

    @Test
    public void testGetByUsernameContaining() {

        String username = "user";
        List<User> expectedUsers = Arrays.asList(
                new User(1L, "user1", "password1" ,null),
                new User(2L, "user2", "password2" ,null),
                new User(3L, "user3", "password3" ,null)
        );


        when(userRepository.getByUsernameContaining(username)).thenReturn(expectedUsers);


        List<User> users = userService.getByUsernameContaining(username);
        assertNotNull(users);
        assertEquals(expectedUsers.size(), users.size());


        verify(userRepository, times(1)).getByUsernameContaining(username);
    }

    @Test
    public void testGetAllByOrderByUsernameAsc() {

        List<User> expectedUsers = Arrays.asList(
                new User(1L, "user1", "password1" ,null),
                new User(2L, "user2", "password2" ,null),
                new User(3L, "user3", "password3" ,null)
        );


        when(userRepository.getAllByOrderByUsernameAsc()).thenReturn(expectedUsers);


        List<User> users = userService.getAllByOrderByUsernameAsc();
        assertNotNull(users);
        assertEquals(expectedUsers.size(), users.size());


        verify(userRepository, times(1)).getAllByOrderByUsernameAsc();
    }
}