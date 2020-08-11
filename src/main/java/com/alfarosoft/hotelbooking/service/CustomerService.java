package com.alfarosoft.hotelbooking.service;

import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import com.alfarosoft.hotelbooking.exception.CustomerException;
import com.alfarosoft.hotelbooking.exception.HotelBookingException;
import com.alfarosoft.hotelbooking.model.Booking;
import com.alfarosoft.hotelbooking.model.Customer;
import com.alfarosoft.hotelbooking.model.payment.PaymentAccount;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

public class CustomerService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session customerSession;
    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        customerSession = hibernateSessionFactory.buildSession();
    }

    public Customer addCustomer (Customer customer) {
        customerSession.beginTransaction();
        customerSession.save(customer);
        customerSession.getTransaction().commit();
        LOG.info("Customer created");
        return customer;
    }

    public Customer addPaymentAccountToCustomer (String customerId, String paymentAccountId) {
        Customer customerRetrieved = retrieveCustomerById(customerId);
        customerRetrieved.setPaymentAccountId(paymentAccountId);
        customerSession.save(customerRetrieved);
        customerSession.getTransaction().commit();
        LOG.info("Payment account added to customer");
        return customerRetrieved;
    }

    public List<Customer> getCustomers(){
        customerSession.beginTransaction();
        List<Customer> customers = customerSession.createQuery("from Customer", Customer.class).list();
        customerSession.getTransaction().commit();
        LOG.info("Customers returned");
        return customers;
    }

    private Customer retrieveCustomerById (String customerId){
        Customer customerRetrieved;
        try{
            customerSession.beginTransaction();
            Query selectQuery = customerSession.createQuery("from Customer WHERE Id=:paramId");
            selectQuery.setParameter("paramId", customerId);
            customerRetrieved = (Customer) selectQuery.uniqueResult();
            LOG.info("Customer retrieved", keyValue("customerRetrieved" , customerRetrieved));
            return customerRetrieved;
        } catch (EntityNotFoundException e){
            LOG.error("Customer not found", keyValue("customerIdInError", customerId));
            throw new CustomerException("Customer with id " + customerId + " was not found", 404);
        }
    }
}
