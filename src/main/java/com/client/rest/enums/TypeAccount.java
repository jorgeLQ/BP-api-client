package com.client.rest.enums;

import lombok.Getter;

@Getter
public enum TypeAccount {
    AHORROS("Ahorros"),
    CORRIENTE("Corriente");

    String name;

    TypeAccount(String name) {
        this.name = name;
    }
}
