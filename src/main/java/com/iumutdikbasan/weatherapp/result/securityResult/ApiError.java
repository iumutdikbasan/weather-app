package com.iumutdikbasan.weatherapp.result.securityResult;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiError {

    private Map<String, String> validationErrors;



}