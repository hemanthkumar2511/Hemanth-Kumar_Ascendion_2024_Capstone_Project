package com.onlineshoppingrestapi.customer.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Customer {
    @Id
    private String custId;
    private String custName;
    private String custCity;
    private LocalDate custDOB;
    private int pincode;

    @PrePersist
    public void genrateId(){
        custId = custName.substring(0,2).toUpperCase()+
                custName.substring(custName.length()-2).toUpperCase()+
                custDOB.getDayOfMonth();
    }
}
