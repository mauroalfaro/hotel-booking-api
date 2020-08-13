package com.alfarosoft.hotelbooking.model;

import com.alfarosoft.hotelbooking.model.payment.PaymentAccount;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Booking")
public class Booking {
    @JsonProperty("bookingId")
    @Id
    private String bookingId;

    @JsonProperty("customerId")
    @Column(name = "customerId")
    private String customerId;

    @JsonProperty("customers")
    @Column(name = "customers")
    private List<Customer> customers;

    @JsonProperty("amenities")
    @Column(name = "amenities")
    private List<Amenity> amenities;

    @JsonProperty("activities")
    @Column(name = "activities")
    private List<Activity> activities;

    @JsonProperty("rooms")
    @Column(name = "rooms")
    private List<Room> rooms;

    @JsonProperty("dateBooked")
    @Column(name = "dateBooked")
    private LocalDate dateBooked;

    @JsonProperty("checkInDate")
    @Column(name = "checkInDate")
    private LocalDate checkInDate;

    @JsonProperty("checkOutDate")
    @Column(name = "checkOutDate")
    private LocalDate checkOutDate;

    @JsonProperty("isActive")
    @Column(name = "isActive")
    private Boolean isActive;

    @JsonProperty("isPayedFor")
    @Column(name = "isPayedFor")
    private Boolean isPayedFor;

    @JsonProperty("totalAmount")
    @Column(name = "totalAmount")
    private Double totalAmount;

    @JsonProperty("paymentAccount")
    @Column(name = "paymentAccount")
    private PaymentAccount paymentAccount;

    public Booking(String bookingId, String customerId, List<Customer> customers, List<Amenity> amenities, List<Activity> activities, List<Room> rooms, LocalDate dateBooked, LocalDate checkInDate, LocalDate checkOutDate, Boolean isActive, Boolean isPayedFor, Double totalAmount, PaymentAccount paymentAccount) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.customers = customers;
        this.amenities = amenities;
        this.activities = activities;
        this.rooms = rooms;
        this.dateBooked = dateBooked;
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

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
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
