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
    /* Kendime not :Bir sequence üzerinden arttırım yapılıyor user id 5 , city id 6 olmuyor.
    user id 5 ise tekrar user eklanince 6 oluyor. */
    private Long id;

    @NotBlank
    @Column(name = "NAME",length = 100,nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;
}
