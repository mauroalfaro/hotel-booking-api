package com.alfarosoft.hotelbooking.model;

import com.alfarosoft.hotelbooking.model.payment.PaymentAccount;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Booking {
    @JsonProperty("bookingId")
    private String bookingId;

    @JsonProperty("customers")
    private List<Customer> customers;

    @JsonProperty("amenities")
    private List<Amenity> amenities;

    @JsonProperty("activities")
    private List<Activity> activities;

    @JsonProperty("rooms")
    private List<Room> rooms;

    @JsonProperty("checkInDate")
    private Date checkInDate;

    @JsonProperty("checkOutDate")
    private Date checkOutDate;

    @JsonProperty("isActive")
    private Boolean isActive;

    @JsonProperty("isPayedFor")
    private Boolean isPayedFor;

    @JsonProperty("totalAmount")
    private Double totalAmount;

    @JsonProperty("paymentAccount")
    private PaymentAccount paymentAccount;

    public Booking(String bookingId, List<Customer> customers, List<Amenity> amenities, List<Activity> activities, List<Room> rooms, Date checkInDate, Date checkOutDate, Boolean isActive, Boolean isPayedFor, Double totalAmount, PaymentAccount paymentAccount) {
        this.bookingId = bookingId;
        this.customers = customers;
        this.amenities = amenities;
        this.activities = activities;
        this.rooms = rooms;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.isActive = isActive;
        this.isPayedFor = isPayedFor;
        this.totalAmount = totalAmount;
        this.paymentAccount = paymentAccount;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getPayedFor() {
        return isPayedFor;
    }

    public void setPayedFor(Boolean payedFor) {
        isPayedFor = payedFor;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId) &&
                Objects.equals(customers, booking.customers) &&
                Objects.equals(amenities, booking.amenities) &&
                Objects.equals(activities, booking.activities) &&
                Objects.equals(rooms, booking.rooms) &&
                Objects.equals(checkInDate, booking.checkInDate) &&
                Objects.equals(checkOutDate, booking.checkOutDate) &&
                Objects.equals(isActive, booking.isActive) &&
                Objects.equals(isPayedFor, booking.isPayedFor) &&
                Objects.equals(totalAmount, booking.totalAmount) &&
                Objects.equals(paymentAccount, booking.paymentAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, customers, amenities, activities, rooms, checkInDate, checkOutDate, isActive, isPayedFor, totalAmount, paymentAccount);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", customers=" + customers +
                ", amenities=" + amenities +
                ", activities=" + activities +
                ", rooms=" + rooms +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", isActive=" + isActive +
                ", isPayedFor=" + isPayedFor +
                ", totalAmount=" + totalAmount +
                ", paymentAccount=" + paymentAccount +
                '}';
    }
}