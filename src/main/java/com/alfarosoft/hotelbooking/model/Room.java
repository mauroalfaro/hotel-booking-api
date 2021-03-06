package com.alfarosoft.hotelbooking.model;

import com.alfarosoft.hotelbooking.model.enums.RoomQualification;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Room")
public class Room {
    @JsonProperty("roomNumber")
    @Id
    private String roomNumber;

    @JsonProperty("capacity")
    @Column(name = "capacity")
    private Integer capacity;

    @JsonProperty("roomQualification")
    @Column(name = "roomQualification")
    private RoomQualification roomQualification;

    @JsonProperty("isAvailable")
    @Column(name = "isAvailable")
    private Boolean isAvailable;

    public Room(String roomNumber, Integer capacity, RoomQualification roomQualification, Boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.roomQualification = roomQualification;
        this.isAvailable = isAvailable;
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

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) &&
                Objects.equals(capacity, room.capacity) &&
                roomQualification == room.roomQualification &&
                Objects.equals(isAvailable, room.isAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, capacity, roomQualification, isAvailable);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", capacity=" + capacity +
                ", roomQualification=" + roomQualification +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
