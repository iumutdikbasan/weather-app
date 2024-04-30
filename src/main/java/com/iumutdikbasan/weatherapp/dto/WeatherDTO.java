package com.iumutdikbasan.weatherapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherDTO {
    private String cod;
    private ArrayList<Object> list = new ArrayList<Object>();
    private City city;

    @Getter
    @Setter
    public class City {
        private float id;
        private String name;
        private Coord coord;
        private String country;
        private float population;
        private float timezone;
        private float sunrise;
        private float sunset;

        @Getter
        @Setter
        public class Coord {
            private float lat;
            private float lon;


        }


    }


}