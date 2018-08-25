package com.customers.backend.domain.entities.response;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum  MetadataType {
    OBJECT("object"),
    LIST("list");

    private final String tipo;

    MetadataType(String tipo) {
        this.tipo = tipo;
    }

    public String value() {
        return tipo;
    }

    public static MetadataType fromValue(String v) {
        MetadataType tipo = Arrays.stream(values())
                .filter(t -> t.tipo.equalsIgnoreCase(v))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de metadado nao encontrado para valor: " + v));
        return tipo;
    }

    @JsonValue
    @Override
    public String toString() {
        return tipo;
    }
}