package com.alfarosoft.hotelbooking.model.enums;

public enum CardType {
    CREDIT("Credit"),
    DEBIT("Debit");

    private String value;

    CardType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
