package com.iumutdikbasan.weatherapp.entity;

import com.iumutdikbasan.weatherapp.general.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CITY")
public class City extends BaseEntity {
    @Id
    @GeneratedValue(generator = "City", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "City", sequenceName = "CITY_ID_SEQ")
    private Long id;

    @NotBlank
    @Column(name = "NAME",length = 100,nullable = false)
    private String cityName;

    @Column(name = "temp", length = 100)
    private String temp;

    @Column(name = "info_date", length = 100)
    private String infoDate;

    @Column(name = "icon", length = 100)
    private String icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
