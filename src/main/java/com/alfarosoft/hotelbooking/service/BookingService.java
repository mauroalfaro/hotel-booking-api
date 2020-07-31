package com.alfarosoft.hotelbooking.service;

import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import com.alfarosoft.hotelbooking.model.Booking;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        LOG.info("Booking created and checked in");
        return booking;
    }
}
