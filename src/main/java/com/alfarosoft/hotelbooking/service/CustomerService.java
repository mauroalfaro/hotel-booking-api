package com.alfarosoft.hotelbooking.service;

import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import org.hibernate.Session;

public class CustomerService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session customerSession;

    public CustomerService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        customerSession = hibernateSessionFactory.buildSession();
    }
}
