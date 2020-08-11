package com.alfarosoft.hotelbooking.model.enums;

public enum RoomQualification {
    ONE_STAR("1"),
    TWO_STARS("2"),
    THREE_STARS("3"),
    FOUR_STARS("4"),
    FIVE_STARS("5");
    private String value;

    RoomQualification(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
