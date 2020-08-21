package com.alfarosoft.hotelbooking.controller;

import com.alfarosoft.hotelbooking.exception.HotelBookingException;
import com.alfarosoft.hotelbooking.exception.RoomException;
import com.alfarosoft.hotelbooking.model.Booking;
import com.alfarosoft.hotelbooking.model.Room;
import com.alfarosoft.hotelbooking.service.BookingService;
import com.alfarosoft.hotelbooking.service.CardEncrypterService;
import com.alfarosoft.hotelbooking.service.CustomerService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Adds a new booking without checking it in")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Booking successfully created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid body applied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping(value = "/addBooking", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> addBooking (@RequestBody Booking booking){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingService.addBooking(booking));
    }

    @Operation(summary = "Checks in an already existing booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Booking successfully checked in",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid body applied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping(value = "/checkIn/{bookingId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> checkInBooking (@PathVariable String bookingId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingService.checkInBooking(bookingId));
    }

    @Operation(summary = "Checks out an already existing booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Booking successfully checked out",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid body applied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PutMapping(value = "/checkOut/{bookingId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> closeBooking (@PathVariable String bookingId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingService.checkOutBooking(bookingId));
    }

    @Operation(summary = "Returns an existing booking by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Booking found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping(value = "/getBooking/byId/{bookingId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> getBookingById (@PathVariable String bookingId){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveBookingById(bookingId));
    }

    @Operation(summary = "Returns all existent bookings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "404", description = "Bookings not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping(value = "/getBookings", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getAllBookings (){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookings());
    }

    @Operation(summary = "Returns all existent bookings by customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "404", description = "Bookings not found for that customer id",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping(value = "/getBookings/byCustomer/{customerId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getBookingsByCustomerId (@PathVariable String customerid){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveBookingsByCustomerId(customerid));
    }

    @Operation(summary = "Returns all existent bookings by a specific check in date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "404", description = "Bookings not found for that check in date",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping(value = "/getBookings/checkInDate/{date}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getBookingsByCheckInDate (@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveBookingsByCheckInDate(date));
    }

    @Operation(summary = "Returns all existent bookings by a specific check out date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "404", description = "Bookings not found for that check out date",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping(value = "/getBookings/checkOutDate/{date}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getBookingsByCheckOutDate (@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveBookingsByCheckOutDate(date));
    }

    @Operation(summary = "Returns all active bookings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "404", description = "Active bookings not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping(value = "/getActiveBookings", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getActiveBookings (){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveActiveBookings());
    }

    @Operation(summary = "Returns all unactive bookings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "404", description = "Unactive bookings not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping(value = "/getUnactiveBookings", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getUnactiveBookings (@PathVariable Date date){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveUnactiveBookings());
    }

    @ExceptionHandler(HotelBookingException.class)
    public ResponseEntity<String> handleException(final HotelBookingException e){
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(RoomException.class)
    public ResponseEntity<String> handleException(final RoomException e){
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

}
