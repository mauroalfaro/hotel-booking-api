package com.alfarosoft.hotelbooking.service;

import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import org.hibernate.Session;

public class BookingService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session bookingSession;

    public BookingService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        bookingSession = hibernateSessionFactory.buildSession();
    }
}
