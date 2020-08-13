package com.alfarosoft.hotelbooking.controller;

import com.alfarosoft.hotelbooking.model.Booking;
import com.alfarosoft.hotelbooking.model.Room;
import com.alfarosoft.hotelbooking.service.BookingService;
import com.alfarosoft.hotelbooking.service.CardEncrypterService;
import com.alfarosoft.hotelbooking.service.CustomerService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/services/bookings")
public class HotelBookingController {

    private final BookingService bookingService;
    private final CustomerService customerService;
    private final CardEncrypterService cardEncrypterService;
    private static final Logger LOG = LoggerFactory.getLogger(HotelBookingController.class);

    @Autowired
    public HotelBookingController(BookingService bookingService, CustomerService customerService, CardEncrypterService cardEncrypterService) {
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.cardEncrypterService = cardEncrypterService;
    }

    @PostMapping(value = "/addBooking", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> addBooking (@RequestBody Booking booking){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingService.addBooking(booking));
    }

    @PostMapping(value = "/checkIn/{bookingId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> checkInBooking (@PathVariable String bookingId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingService.checkInBooking(bookingId));
    }

    @PutMapping(value = "/checkOut/{bookingId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> closeBooking (@PathVariable String bookingId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingService.checkOutBooking(bookingId));
    }

    @GetMapping(value = "/getBooking/byId/{bookingId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> getBookingById (@PathVariable String bookingId){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveBookingById(bookingId));
    }

    @GetMapping(value = "/getBookings", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getAllBookings (){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookings());
    }

    @GetMapping(value = "/getBookings/byCustomer/{customerId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getBookingsByCustomerId (@PathVariable String customerid){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveBookingsByCustomerId(customerid));
    }

    @GetMapping(value = "/getBookings/checkInDate/{date}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getBookingsByCheckInDate (@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveBookingsByCheckInDate(date));
    }

    @GetMapping(value = "/getBookings/checkOutDate/{date}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getBookingsByCheckOutDate (@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveBookingsByCheckOutDate(date));
    }

    @GetMapping(value = "/getActiveBookings", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getActiveBookings (){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveActiveBookings());
    }

    @GetMapping(value = "/getUnactiveBookings", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getUnactiveBookings (@PathVariable Date date){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveUnactiveBookings());
    }

}
