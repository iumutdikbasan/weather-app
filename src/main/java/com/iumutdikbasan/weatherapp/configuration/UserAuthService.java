package com.iumutdikbasan.weatherapp.configuration;

import com.iumutdikbasan.weatherapp.kafka.KafkaService;
import com.iumutdikbasan.weatherapp.entity.User;
import com.iumutdikbasan.weatherapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    private KafkaService kafkaService;

    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String record = String.format("[%s:%d] UserAuthService loadUserByUsername method called with username: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), username);
        kafkaService.sendMessage(record, "info");

        User user = userRepository.findByUsername(username);
        if(user == null){

            record = String.format("[%s:%d] UserAuthService loadUserByUsername method called with username: %s not found", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), username);
            kafkaService.sendMessage(record, "info");
            throw new UsernameNotFoundException("User not found");
        }

        record = String.format("[%s:%d] UserAuthService loadUserByUsername method called with username: %s found", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), username);
        kafkaService.sendMessage(record, "info");
        return new WeatherAppUserDetails(user);
    }
}