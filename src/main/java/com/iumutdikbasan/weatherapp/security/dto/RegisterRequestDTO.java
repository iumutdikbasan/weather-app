package com.iumutdikbasan.weatherapp.security.dto;

import com.iumutdikbasan.weatherapp.security.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}