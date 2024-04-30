package com.iumutdikbasan.weatherapp.general;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {


    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

}
