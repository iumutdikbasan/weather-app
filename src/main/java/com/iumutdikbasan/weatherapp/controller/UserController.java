package com.iumutdikbasan.weatherapp.controller;

import com.iumutdikbasan.weatherapp.controller.contract.UserControllerContract;
import com.iumutdikbasan.weatherapp.dto.requests.UserRequestDTO;
import com.iumutdikbasan.weatherapp.dto.responses.UserResponseDTO;
import com.iumutdikbasan.weatherapp.result.generalResult.DataResult;
import com.iumutdikbasan.weatherapp.result.generalResult.Result;
import com.iumutdikbasan.weatherapp.result.generalResult.SuccessDataResult;
import com.iumutdikbasan.weatherapp.result.generalResult.SuccessResult;
import com.iumutdikbasan.weatherapp.result.securityResult.ApiError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




@RestController
@RequestMapping("/api/1.0/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserControllerContract userControllerContract;



    @GetMapping
    public ResponseEntity<DataResult<List<UserResponseDTO>>> getUsers() {
        List<UserResponseDTO> users = userControllerContract.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<UserResponseDTO>> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userControllerContract.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi", user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResult<UserResponseDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userControllerContract.updateUser(id, userRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Güncellendi", updatedUser));
    }

    @PostMapping
    public ResponseEntity<DataResult<UserResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userControllerContract.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessDataResult<>("Kayıt Başarılı", createdUser));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError exceptionHandler(MethodArgumentNotValidException exception){
        ApiError error = new ApiError();
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        error.setValidationErrors(validationErrors);
        return error;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteUser(@PathVariable Long id) {
        userControllerContract.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Silindi"));
    }

    @GetMapping("/page")
    public ResponseEntity<DataResult<List<UserResponseDTO>>> getUsersPage(@RequestParam int pageNumber, @RequestParam int pageSize) {
        List<UserResponseDTO> usersPage = userControllerContract.getUsersPage(pageNumber - 1, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi", usersPage));
    }

    @GetMapping("/search/startswith")
    public ResponseEntity<DataResult<List<UserResponseDTO>>> getByUsernameStartsWith(@RequestParam String username) {
        List<UserResponseDTO> users = userControllerContract.getByUsernameStartsWith(username);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi", users));
    }

    @GetMapping("/search/contains")
    public ResponseEntity<DataResult<List<UserResponseDTO>>> getByUsernameContaining(@RequestParam String username) {
        List<UserResponseDTO> users = userControllerContract.getByUsernameContaining(username);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi", users));

    }

    @GetMapping("/orderbyname")
    public ResponseEntity<DataResult<List<UserResponseDTO>>> getAllByOrderByUsernameAsc() {
        List<UserResponseDTO> users = userControllerContract.getAllByOrderByUsernameAsc();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi", users));

    }


}
