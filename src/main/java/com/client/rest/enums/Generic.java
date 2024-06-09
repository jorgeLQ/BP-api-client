package com.client.rest.enums;

import lombok.Getter;

@Getter
public enum Generic {
    MASCULINO("M"),
    FEMENINO("F");

    String name;

    Generic(String name) {
        this.name = name;
    }
}
