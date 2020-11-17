package com.alfarosoft.hotelbooking.service;

import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import com.alfarosoft.hotelbooking.exception.PaymentAccountException;
import com.alfarosoft.hotelbooking.exception.RoomException;
import com.alfarosoft.hotelbooking.model.Booking;
import com.alfarosoft.hotelbooking.model.Room;
import com.alfarosoft.hotelbooking.model.enums.AccountStatus;
import com.alfarosoft.hotelbooking.model.payment.PaymentAccount;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;

import static net.logstash.logback.argument.StructuredArguments.a;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

public class PaymentAccountService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session paymentSession;
    private static final Logger LOG = LoggerFactory.getLogger(PaymentAccountService.class);

    public PaymentAccountService() {
    }

    public PaymentAccountService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        paymentSession = this.hibernateSessionFactory.buildSession();
    }

    public AccountStatus checkAccountStatus (String paymentAccountId){
        return retrievePaymentAccountById(paymentAccountId).getAccountStatus();
    }

    public PaymentAccount registerPayment (Booking booking){

    }

    private PaymentAccount retrievePaymentAccountById (String paymentAccountId){
        PaymentAccount paymentAccountRetrieved;
        try{
            paymentSession.beginTransaction();
            Query selectQuery = paymentSession.createQuery("from PaymentAccount WHERE Id=:paymentAccountId");
            selectQuery.setParameter("paymentAccountId", paymentAccountId);
            paymentAccountRetrieved = (PaymentAccount) selectQuery.uniqueResult();
            LOG.info("Payment Account retrieved", keyValue("paymentAccountRetrieved", paymentAccountRetrieved));
            return paymentAccountRetrieved;
        } catch (EntityNotFoundException e){
            LOG.error("Payment Account not found", keyValue("paymentAccountIdInError", paymentAccountId));
            throw new PaymentAccountException("Payment Account with id " + paymentAccountId + " was not found", 404);
        }
    }

    public PaymentAccount createNewPaymentAccount(Booking booking){
        
    }
}
