package com.alfarosoft.hotelbooking.configuration;

import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import com.alfarosoft.hotelbooking.service.*;
import com.alfarosoft.hotelbooking.utils.CardIdEncrypter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class HotelBookingConfiguration {
    @Bean
    @RequestScope
    public CustomerService customerService() throws Exception {
        return new CustomerService(hibernateSessionFactory());
    }

    @Bean
    @RequestScope
    public BookingService bookingService() throws Exception {
        return new BookingService(hibernateSessionFactory(), roomService(), paymentAccountService());
    }

    @Bean
    @RequestScope
    public CardEncrypterService cardEncrypterService() throws Exception {
        return new CardEncrypterService(hibernateSessionFactory(), cardIdEncrypter());
    }

    @Bean
    @RequestScope
    public RoomService roomService() throws Exception {
        return new RoomService(hibernateSessionFactory());
    }


    @Bean
    @RequestScope
    public PaymentAccountService paymentAccountService() throws Exception {
        return new PaymentAccountService(hibernateSessionFactory());
    }

    @Bean
    @RequestScope
    public HibernateSessionFactory hibernateSessionFactory(){
        return new HibernateSessionFactory();
    }

    @Bean
    @RequestScope
    public CardIdEncrypter cardIdEncrypter() {return new CardIdEncrypter();}
}
