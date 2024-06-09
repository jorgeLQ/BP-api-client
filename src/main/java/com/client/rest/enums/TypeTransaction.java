package com.client.rest.enums;

import lombok.Getter;

@Getter
public enum TypeTransaction {
    DEPOSITO("Deposito de"),
    RETIRO("Retiro de");

    String name;

    TypeTransaction(String name) {
        this.name = name;
    }
}
