package com.client.rest.model;

import com.client.rest.enums.Generic;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
//@Data
//@Builder
public abstract class Person {

    private String name;
    @Enumerated(EnumType.STRING)
    private Generic generic;
    private Integer age;
    @Column(unique = true)
    private String identification;
    private String address;
    private String phone;

}
