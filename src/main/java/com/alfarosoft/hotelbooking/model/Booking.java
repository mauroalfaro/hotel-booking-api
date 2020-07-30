package com.alfarosoft.hotelbooking.model;

import java.util.Date;
import java.util.List;

public class Booking {
    private List<Customer> customers;
    private List<Amenity> amenities;
    private List<Activity> activities;
    private List<Room> rooms;
    private Date checkInDate;
    private Date checkOutDate;
}
