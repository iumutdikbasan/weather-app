package com.iumutdikbasan.weatherapp.auth;

import com.iumutdikbasan.weatherapp.configuration.WeatherAppUserDetails;
import com.iumutdikbasan.weatherapp.kafka.KafkaService;
import com.iumutdikbasan.weatherapp.entity.User;
import com.iumutdikbasan.weatherapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private static final Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserRepository userRepository;

    @Autowired
    private KafkaService kafkaService;

    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/api/1.0/auth")
    public ResponseEntity<?> handleAuthentication() {
        //log.info("AuthController handleAuthentication method called");
        String record = String.format("[%s:%d] AuthController handleAuthentication method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record, "info");

        WeatherAppUserDetails weatherAppUserDetails = (WeatherAppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = weatherAppUserDetails.getUsername();

        User inDB = userRepository.findByUsername(username);
        //log.info("AuthController handleAuthentication method called with username: {}", username);
        record = String.format("[%s:%d] AuthController handleAuthentication method called with username: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), username);
        kafkaService.sendMessage(record, "info");
        return ResponseEntity.ok(inDB);

    }
}
