package com.iumutdikbasan.weatherapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iumutdikbasan.weatherapp.annotations.UniqueUsername;
import com.iumutdikbasan.weatherapp.general.BaseEntity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cities"})
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4, max = 255)
    @UniqueUsername
    private String username;



    @NotNull
    @Size(min = 8, max = 255)
    private String password;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<City> cities;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +

                ", password='" + password + '\'' +
                ", cities=" + cities +
                '}';
    }
}