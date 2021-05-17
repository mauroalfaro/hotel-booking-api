package com.alfarosoft.hotelbooking.service;

import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import com.alfarosoft.hotelbooking.exception.HotelBookingException;
import com.alfarosoft.hotelbooking.exception.RoomException;
import com.alfarosoft.hotelbooking.model.Booking;
import com.alfarosoft.hotelbooking.model.Room;
import com.alfarosoft.hotelbooking.model.enums.AccountStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

public class BookingService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session bookingSession;
    private RoomService roomService;
    private PaymentAccountService paymentAccountService;
    private static final Logger LOG = LoggerFactory.getLogger(BookingService.class);

    public BookingService(HibernateSessionFactory hibernateSessionFactory, RoomService roomService, PaymentAccountService paymentAccountService) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        bookingSession = hibernateSessionFactory.buildSession();
        this.roomService = roomService;
        this.paymentAccountService = paymentAccountService;
    }

    public Booking addBooking (Booking booking) {
        AccountStatus accountStatus;
        if(booking.getPaymentAccount().getPaymentAccountId() == null){
            accountStatus = paymentAccountService.createNewPaymentAccount(booking).getAccountStatus();
        } else {
            accountStatus = paymentAccountService.checkAccountStatus(booking.getPaymentAccount().getPaymentAccountId());
        }
        if(accountStatus.toString().equals("Available")){
            bookingSession.beginTransaction();
            paymentAccountService.checkAccountStatus(booking.getPaymentAccount().getPaymentAccountId());
            for(Room room : booking.getRooms()){
                List<Room> roomsAvailable = roomService.getAvailableRoomsWithCapacity(room.getCapacity());
                if (roomsAvailable == null || roomsAvailable.isEmpty()){
                    throw new RoomException("Room not available with capacity of " + room.getCapacity(), 400);
                }
                else {
                    for (Room roomAvailable : roomsAvailable){
                        roomService.occupyRoom(roomAvailable.getRoomNumber());
                    }
                }
            }
            bookingSession.save(booking);
            bookingSession.getTransaction().commit();
            LOG.info("Booking created");
            return booking;
        }
        return null;
        //if (accountStatus.toString().equals("Forbidden") !!)
    }

    public Booking checkInBooking (String bookingId){
        Booking bookingRetrieved = retrieveBookingById(bookingId);
        bookingRetrieved.setCheckInDate(LocalDate.now());
        bookingRetrieved.setActive(true);
        bookingSession.save(bookingRetrieved);
        bookingSession.getTransaction().commit();
        LOG.info("Booking succesfully checked in");
        return bookingRetrieved;
    }

    public Booking checkOutBooking (String bookingId){
        Booking bookingRetrieved = retrieveBookingById(bookingId);
        bookingRetrieved.setCheckOutDate(LocalDate.now());
        bookingRetrieved.setActive(false);
        paymentAccountService.registerPayment(bookingRetrieved);
        bookingRetrieved.setPayedFor(true);
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

    public Booking retrieveBookingById (String bookingId){
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
            throw new HotelBookingException("Booking with id " + bookingId + " was not found", 404);
        }
    }

    public List<Booking> retrieveBookingsByCustomerId (String customerId){
        List<Booking> bookingsRetrieved;
        try{
            bookingSession.beginTransaction();
            Query selectQuery = bookingSession.createQuery("from Booking WHERE customerId=:customerId");
            selectQuery.setParameter("customerId", customerId);
            bookingsRetrieved = (List<Booking>) selectQuery.list();
            LOG.info("Bookings retrieved for customer " + customerId, keyValue("bookingRetrieved" , bookingsRetrieved));
            return bookingsRetrieved;
        } catch (EntityNotFoundException e){
            LOG.error("Bookings not found for customer", keyValue("customerIdInError", customerId));
            throw new HotelBookingException("Bookings not found for customerId " + customerId, 404);
        }
    }

    public List<Booking> retrieveBookingsByCheckInDate (LocalDate checkInDate){
        List<Booking> bookingsRetrieved;
        try{
            bookingSession.beginTransaction();
            Query selectQuery = bookingSession.createQuery("from Booking WHERE checkInDate=:checkInDate");
            selectQuery.setParameter("checkInDate", checkInDate);
            bookingsRetrieved = (List<Booking>) selectQuery.list();
            return bookingsRetrieved;
        } catch (EntityNotFoundException e){
            LOG.error("Bookings not found with checkInDate", keyValue("checkInDate", checkInDate.toString()));
            throw new HotelBookingException("Bookings not found with checkInDate " + checkInDate.toString(), 404);
        }
    }

    public List<Booking> retrieveBookingsByCheckOutDate (LocalDate checkOutDate){
        List<Booking> bookingsRetrieved;
        try{
            bookingSession.beginTransaction();
            Query selectQuery = bookingSession.createQuery("from Booking WHERE checkOutDate=:checkOutDate");
            selectQuery.setParameter("checkOutDate", checkOutDate);
            bookingsRetrieved = (List<Booking>) selectQuery.list();
            return bookingsRetrieved;
        } catch (EntityNotFoundException e){
            LOG.error("Bookings not found with checkOutDate", keyValue("checkOutDate", checkOutDate.toString()));
            throw new HotelBookingException("Bookings not found with checkOutDate " + checkOutDate.toString(), 404);
        }
    }

    public List<Booking> retrieveActiveBookings(){
        List<Booking> bookingsRetrieved;
        try{
            bookingSession.beginTransaction();
            bookingsRetrieved = bookingSession.createQuery("from Booking WHERE isActive=true").list();
            return bookingsRetrieved;
        } catch (EntityNotFoundException e){
            LOG.error("Active bookings not found");
            throw new HotelBookingException("Active bookings not found", 404);
        }
    }

    public List<Booking> retrieveUnactiveBookings(){
        List<Booking> bookingsRetrieved;
        try{
            bookingSession.beginTransaction();
            bookingsRetrieved = bookingSession.createQuery("from Booking WHERE isActive=false").list();
            return bookingsRetrieved;
        } catch (EntityNotFoundException e){
            LOG.error("Unactive bookings not found");
            throw new HotelBookingException("Unactive bookings not found", 404);
        }
    }
}
