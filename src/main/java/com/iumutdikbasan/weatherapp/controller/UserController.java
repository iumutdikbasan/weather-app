package com.iumutdikbasan.weatherapp.controller;

import com.iumutdikbasan.weatherapp.controller.contract.UserControllerContract;
import com.iumutdikbasan.weatherapp.dto.user.UserDTO;
import com.iumutdikbasan.weatherapp.dto.user.UserSaveRequestDTO;
import com.iumutdikbasan.weatherapp.general.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserControllerContract userControllerContract;

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> save(@RequestBody UserSaveRequestDTO userSaveRequestDTO){
        UserDTO userDTO = userControllerContract.save(userSaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> findAll(){
        List<UserDTO> userDTOList = userControllerContract.findAll();
        return ResponseEntity.ok(RestResponse.of(userDTOList));
    }
}
