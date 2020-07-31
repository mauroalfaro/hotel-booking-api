package com.alfarosoft.hotelbooking.model.payment;

import java.util.Date;

public class DebitCard extends Card{
    public DebitCard(String encryptedCardNumber, Date expirationDate, String cardEntity, String cardHolderName, String bank) {
        super(encryptedCardNumber, expirationDate, cardEntity, cardHolderName, bank);
    }
}
