package com.alfarosoft.hotelbooking.service;

import com.alfarosoft.hotelbooking.utils.CardIdEncrypter;
import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import org.hibernate.Session;

public class CardEncrypterService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session cardEncrypterSession;
    private CardIdEncrypter cardIdEncrypter;

    public CardEncrypterService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        cardEncrypterSession = hibernateSessionFactory.buildSession();
    }

}
