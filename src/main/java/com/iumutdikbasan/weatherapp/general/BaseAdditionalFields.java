package com.iumutdikbasan.weatherapp.general;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/*@Embeddable ve @Embedded anotasyonları kullanarak ara tablo oluşturmaya gerek kalmadan “gömmek” istediğimiz persist sınıfımıza eklememiz yeterli olmaktadır.
Java nesnesi tarafından farklı sınıflar ile yönettiğimiz alanları, veritabanı olarak aynı tabloda görmemizi sağlamaktadır.
 */
@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {

    @Column(name = "ID_CUSTOMER_CREATED_BY")
    private Long createdBy;

    @Column(name = "ID_CUSTOMER_UPTATED_BY")
    private Long updatedBy;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

}
