package com.alfarosoft.hotelbooking.service;

import com.alfarosoft.hotelbooking.model.enums.CardType;
import com.alfarosoft.hotelbooking.model.payment.Card;
import com.alfarosoft.hotelbooking.model.payment.CreditCard;
import com.alfarosoft.hotelbooking.model.payment.PaymentAccount;
import com.alfarosoft.hotelbooking.utils.CardIdEncrypter;
import com.alfarosoft.hotelbooking.database.HibernateSessionFactory;
import org.hibernate.Session;

public class CardEncrypterService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session cardEncrypterSession;
    private CardIdEncrypter cardIdEncrypter;

    public CardEncrypterService(HibernateSessionFactory hibernateSessionFactory, CardIdEncrypter cardIdEncrypter) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        cardEncrypterSession = hibernateSessionFactory.buildSession();
        this.cardIdEncrypter = cardIdEncrypter;
    }

    public void encryptCardNumber (PaymentAccount paymentAccount, String cardNumber, CardType cardType){
        String encryptedCardNumber = cardIdEncrypter.encryptCardNumber(cardNumber);
        if (!cardNumber.equals(encryptedCardNumber)){
            if(cardType.toString().equals("CreditCard")){
                paymentAccount.getCreditCard().setEncryptedCardNumber(encryptedCardNumber);
            }
            else {
                paymentAccount.getDebitCard().setEncryptedCardNumber(encryptedCardNumber);
            }
        }
    }

}
