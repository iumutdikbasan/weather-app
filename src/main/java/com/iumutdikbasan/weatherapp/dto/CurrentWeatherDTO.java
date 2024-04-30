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
public class CurrentWeatherDTO {
    private Coord coord;
    private ArrayList<Object> weather = new ArrayList<Object>();
    private String base;
    private Main main;
    private float visibility;
    private Wind wind;
    private Clouds clouds;
    private float dt;
    private Sys sys;
    private float timezone;
    private float id;
    private String name;
    private float cod;

    @Getter
    @Setter
    public class Coord {
        private float lon;
        private float lat;

    }


    @Getter
    @Setter
    public class Sys {
        private float type;
        private float id;
        private String country;
        private float sunrise;
        private float sunset;

    }


    @Getter
    @Setter
    public class Clouds {
        private float all;

    }

    @Getter
    @Setter
    public class Wind {
        private float speed;
        private float deg;

    }


    @Getter
    @Setter
    public class Main {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private float pressure;
        private float humidity;


    }
}