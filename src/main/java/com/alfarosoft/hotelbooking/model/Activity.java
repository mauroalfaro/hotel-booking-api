package com.alfarosoft.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Activity {
    @JsonProperty("activityId")
    private String id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("isAvailable")
    private Boolean isAvailable;

    public Activity(String id, String description, Boolean isAvailable) {
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

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id) &&
                Objects.equals(description, activity.description) &&
                Objects.equals(isAvailable, activity.isAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isAvailable);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", isAvailable='" + isAvailable + '\'' +
                '}';
    }
}
