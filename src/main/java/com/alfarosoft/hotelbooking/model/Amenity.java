package com.alfarosoft.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Amenity {
    @JsonProperty("customerId")
    private String id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("isAvailable")
    private String isAvailable;

    public Amenity(String id, String description, String isAvailable) {
        this.id = id;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amenity amenity = (Amenity) o;
        return Objects.equals(id, amenity.id) &&
                Objects.equals(description, amenity.description) &&
                Objects.equals(isAvailable, amenity.isAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isAvailable);
    }

    @Override
    public String toString() {
        return "Amenity{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", isAvailable='" + isAvailable + '\'' +
                '}';
    }
}
