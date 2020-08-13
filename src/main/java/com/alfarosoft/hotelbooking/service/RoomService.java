package com.alfarosoft.hotelbooking.service;

import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import com.alfarosoft.hotelbooking.exception.RoomException;
import com.alfarosoft.hotelbooking.model.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

public class RoomService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session roomSession;
    private static final Logger LOG = LoggerFactory.getLogger(RoomService.class);

    public RoomService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        roomSession = this.hibernateSessionFactory.buildSession();
    }

    public Room occupyRoom (String roomId){
        Room roomRetrieved = retrieveRoomById(roomId);
        roomRetrieved.setAvailable(false);
        roomSession.save(roomRetrieved);
        roomSession.getTransaction().commit();
        LOG.info("Payment account added to customer");
        return roomRetrieved;
    }

    public List<Room> getAvailableRoomsWithCapacity (Integer desiredCapacity){
        roomSession.beginTransaction();
        Query selectQuery = roomSession.createQuery("from Room WHERE capacity=:desiredCapacity AND isAvailable=true");
        selectQuery.setParameter("desiredCapacity", desiredCapacity);
        List<Room> availableRooms = selectQuery.list();
        roomSession.getTransaction().commit();
        if(availableRooms.isEmpty()){
            LOG.error("Rooms not available with capacity of " + desiredCapacity);
            throw new RoomException("Rooms not available with capacity of " + desiredCapacity, 400);
        }
        LOG.info("Rooms available with the desired capacity returned");
        return availableRooms;
    }

    private Room retrieveRoomById (String roomId){
        Room roomRetrieved;
        try{
            roomSession.beginTransaction();
            Query selectQuery = roomSession.createQuery("from Room WHERE Id=:roomId");
            selectQuery.setParameter("roomId", roomId);
            roomRetrieved = (Room) selectQuery.uniqueResult();
            LOG.info("Room retrieved", keyValue("roomRetrieved", roomRetrieved));
            return roomRetrieved;
        } catch (EntityNotFoundException e){
            LOG.error("Room not found", keyValue("roomIdInError", roomId));
            throw new RoomException("Room with id " + roomId + " was not found", 404);
        }
    }

}
