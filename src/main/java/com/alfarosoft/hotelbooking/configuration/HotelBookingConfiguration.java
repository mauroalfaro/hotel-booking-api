package com.alfarosoft.hotelbooking.configuration;

import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import com.alfarosoft.hotelbooking.service.BookingService;
import com.alfarosoft.hotelbooking.service.CardEncrypterService;
import com.alfarosoft.hotelbooking.service.CustomerService;
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
        return new BookingService(hibernateSessionFactory());
    }

    @Bean
    @RequestScope
    public CardEncrypterService cardEncrypterService() throws Exception {
        return new CardEncrypterService(hibernateSessionFactory());
    }

    @Bean
    @RequestScope
    public HibernateSessionFactory hibernateSessionFactory(){
        return new HibernateSessionFactory();
    }
}
