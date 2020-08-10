package com.alfarosoft.hotelbooking.service;

import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import com.alfarosoft.hotelbooking.model.Booking;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

public class BookingService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session bookingSession;
    private static final Logger LOG = LoggerFactory.getLogger(BookingService.class);

    public BookingService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        bookingSession = hibernateSessionFactory.buildSession();
    }

    public Booking addBooking (Booking booking) {
        bookingSession.beginTransaction();
        bookingSession.save(booking);
        bookingSession.getTransaction().commit();
        LOG.info("Booking created");
        return booking;
    }

    public Booking checkingBooking (String bookingId){
        Booking bookingRetrieved = retrieveBookingById(bookingId);
        bookingRetrieved.setCheckInDate(LocalDate.now());
        bookingRetrieved.setActive(true);
        bookingSession.save(bookingRetrieved);
        bookingSession.getTransaction().commit();
        LOG.info("Booking succesfully checked in");
        return bookingRetrieved;
    }

    public Booking checkoutBooking (String bookingId){
        Booking bookingRetrieved = retrieveBookingById(bookingId);
        bookingRetrieved.setCheckOutDate(LocalDate.now());
        bookingRetrieved.setActive(false);
        bookingSession.save(bookingRetrieved);
        bookingSession.getTransaction().commit();
        LOG.info("Booking succesfully checked out");
        return bookingRetrieved;
    }

    public List<Booking> getBookings(){
        bookingSession.beginTransaction();
        List<Booking> bookings = bookingSession.createQuery("from Booking", Booking.class).list();
        bookingSession.getTransaction().commit();
        LOG.info("Bookings returned");
        return bookings;
    }

    private Booking retrieveBookingById (String bookingId){
        Booking bookingRetrieved;
        try{
            bookingSession.beginTransaction();
            Query selectQuery = bookingSession.createQuery("from Booking WHERE Id=:paramId");
            selectQuery.setParameter("paramId", bookingId);
            bookingRetrieved = (Booking) selectQuery.uniqueResult();
            LOG.info("Booking retrieved", keyValue("bookingRetrieved" , bookingRetrieved));
            return bookingRetrieved;
        } catch (EntityNotFoundException e){
            LOG.error("Booking not found", keyValue("bookingIdInError", bookingId));
            //throw new HotelBookingException("Booking
            // with id " + id + " was not found", 404);
        }
    }
}
