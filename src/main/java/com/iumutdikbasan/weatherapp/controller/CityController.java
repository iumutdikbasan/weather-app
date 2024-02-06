package com.iumutdikbasan.weatherapp.controller;

import com.iumutdikbasan.weatherapp.controller.contract.CityControllerContract;
import com.iumutdikbasan.weatherapp.dto.city.CityDTO;
import com.iumutdikbasan.weatherapp.dto.city.CitySaveRequestDTO;
import com.iumutdikbasan.weatherapp.general.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityControllerContract cityControllerContract;

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<List<CityDTO>>> findByUserId(@PathVariable Long id) {
        List<CityDTO> cityDTOList = cityControllerContract.findByUserId(id);
        return ResponseEntity.ok(RestResponse.of(cityDTOList));
    }

    @PostMapping
    public ResponseEntity<RestResponse<CityDTO>> save(@RequestBody CitySaveRequestDTO citySaveRequestDTO){
        CityDTO cityDTO = cityControllerContract.save(citySaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(cityDTO));
    }
    @DeleteMapping
    public ResponseEntity<RestResponse<Object>> delete(@PathVariable Long id){
        cityControllerContract.delete(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
