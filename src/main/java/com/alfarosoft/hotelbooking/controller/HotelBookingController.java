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

    @PostMapping(value = "/checkIn", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> addBooking (@RequestBody Booking booking){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingService.addBooking(booking));
    }

    @PutMapping(value = "/checkOut/{bookingId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> closeBooking (@PathVariable String bookingId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @GetMapping(value = "/getBooking/{bookingId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getBookingById (@PathVariable String bookingId){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(value = "/getBookings", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getAllBookings (){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(value = "/getBookings/{customerId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getBookingsByCustomerId (@PathVariable String customerid){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(value = "/getBookings/{date}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getBookingsByDate (@PathVariable Date date){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(value = "/getAvailableRooms", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Room>> getAvailableRooms (){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
