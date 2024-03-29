package com.iumutdikbasan.weatherapp.controller;

import com.iumutdikbasan.weatherapp.controller.contract.CityControllerContract;
import com.iumutdikbasan.weatherapp.dto.city.request.CitySaveRequestDTO;
import com.iumutdikbasan.weatherapp.dto.city.response.CityResponseDTO;
import com.iumutdikbasan.weatherapp.general.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityControllerContract cityControllerContract;

    @GetMapping
    public ResponseEntity<RestResponse<List<CityResponseDTO>>> findCityByUserId(){
        List<CityResponseDTO> cityResponseDTOList = cityControllerContract.findCityByUserId();
        return ResponseEntity.ok(RestResponse.of(cityResponseDTOList));
    }

    @PostMapping
    public ResponseEntity<RestResponse<CityResponseDTO>> save(@RequestBody CitySaveRequestDTO citySaveRequestDTO){
        CityResponseDTO cityResponseDTO = cityControllerContract.save(citySaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(cityResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Object>> delete(@PathVariable Long id){
        cityControllerContract.delete(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}