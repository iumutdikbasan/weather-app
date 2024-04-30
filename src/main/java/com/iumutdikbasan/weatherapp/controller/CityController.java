package com.iumutdikbasan.weatherapp.controller;

import com.iumutdikbasan.weatherapp.controller.contract.CityControllerContract;
import com.iumutdikbasan.weatherapp.dto.requests.CityRequestDTO;
import com.iumutdikbasan.weatherapp.dto.responses.CityResponseDTO;
import com.iumutdikbasan.weatherapp.result.generalResult.DataResult;
import com.iumutdikbasan.weatherapp.result.generalResult.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityControllerContract cityControllerContract;

    @PostMapping
    public ResponseEntity<DataResult<CityResponseDTO>> saveCity(@RequestBody CityRequestDTO cityRequestDTO){
        CityResponseDTO createdCity = cityControllerContract.saveCity(cityRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessDataResult<>("Kayıt Başarılı",createdCity));
    }

    @GetMapping
    public ResponseEntity<DataResult<List<CityResponseDTO>>> getAllCities(){
        List<CityResponseDTO> cityResponseDTOS = cityControllerContract.getAllCities();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi",cityResponseDTOS));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DataResult<CityResponseDTO>> getCityById(@PathVariable Long id){
        CityResponseDTO cityResponseDTO = cityControllerContract.getCityById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi",cityResponseDTO));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<DataResult<List<CityResponseDTO>>> getCitiesByUserId(@PathVariable Long id){
        List<CityResponseDTO> cityResponseDTOS = cityControllerContract.getCitiesByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi",cityResponseDTOS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResult<CityResponseDTO>> deleteCityById(@PathVariable Long id){
        CityResponseDTO cityResponseDTO = cityControllerContract.deleteCityById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Silindi",cityResponseDTO));
    }

    @GetMapping("/page")
    public ResponseEntity<DataResult<List<CityResponseDTO>>> getCitiesByPage(@RequestParam int page,@RequestParam int size){
        List<CityResponseDTO> cityResponseDTOS = cityControllerContract.getCitiesByPage(page-1,size);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi",cityResponseDTOS));
    }

    @GetMapping("/search/startswith")
    public ResponseEntity<DataResult<List<CityResponseDTO>>> getCitiesByCityNameStartsWith(@RequestParam String cityName){
        List<CityResponseDTO> cityResponseDTOS = cityControllerContract.getCitiesByCityNameStartsWith(cityName);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi",cityResponseDTOS));
    }

    @GetMapping("/search/contains")
    public ResponseEntity<DataResult<List<CityResponseDTO>>> getCitiesByCityNameContains(@RequestParam String cityName){
        List<CityResponseDTO> cityResponseDTOS = cityControllerContract.getCitiesByCityNameContains(cityName);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi",cityResponseDTOS));

    }

    @GetMapping("/orderbyname")
    public ResponseEntity<DataResult<List<CityResponseDTO>>> getAllCitiesOrderByCityName(){
        List<CityResponseDTO> cityResponseDTOS = cityControllerContract.getAllCitiesOrderByCityName();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi",cityResponseDTOS));

    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResult<CityResponseDTO>> updateCity(@PathVariable Long id,@RequestBody CityRequestDTO cityRequestDTO){
        CityResponseDTO cityResponseDTO = cityControllerContract.updateCity(id,cityRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Güncellendi",cityResponseDTO));
    }
}