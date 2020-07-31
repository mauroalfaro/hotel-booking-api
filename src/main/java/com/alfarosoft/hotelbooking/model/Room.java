package com.alfarosoft.hotelbooking.model;

import com.alfarosoft.hotelbooking.model.enums.RoomQualification;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Room {
    @JsonProperty("roomNumber")
    private String roomNumber;

    @JsonProperty("capacity")
    private Integer capacity;

    @JsonProperty("roomQualification")
    private RoomQualification roomQualification;

    public Room(String roomNumber, Integer capacity, RoomQualification roomQualification) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.roomQualification = roomQualification;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public RoomQualification getRoomQualification() {
        return roomQualification;
    }

    public void setRoomQualification(RoomQualification roomQualification) {
        this.roomQualification = roomQualification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) &&
                Objects.equals(capacity, room.capacity) &&
                roomQualification == room.roomQualification;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, capacity, roomQualification);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", capacity=" + capacity +
                ", roomQualification=" + roomQualification +
                '}';
    }
}
