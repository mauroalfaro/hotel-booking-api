package com.alfarosoft.hotelbooking.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public abstract class Card {

    @JsonProperty("encryptedCardNumber")
    private String encryptedCardNumber;

    @JsonProperty("expirationDate")
    private Date expirationDate;

    @JsonProperty("cardEntity")
    private String cardEntity;

    @JsonProperty("cardHolderName")
    private String cardHolderName;

    @JsonProperty("bank")
    private String bank;

    public Card(String encryptedCardNumber, Date expirationDate, String cardEntity, String cardHolderName, String bank) {
        this.encryptedCardNumber = encryptedCardNumber;
        this.expirationDate = expirationDate;
        this.cardEntity = cardEntity;
        this.cardHolderName = cardHolderName;
        this.bank = bank;
    }

    public String getEncryptedCardNumber() {
        return encryptedCardNumber;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(String cardEntity) {
        this.cardEntity = cardEntity;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(encryptedCardNumber, card.encryptedCardNumber) &&
                Objects.equals(expirationDate, card.expirationDate) &&
                Objects.equals(cardEntity, card.cardEntity) &&
                Objects.equals(cardHolderName, card.cardHolderName) &&
                Objects.equals(bank, card.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encryptedCardNumber, expirationDate, cardEntity, cardHolderName, bank);
    }

    @Override
    public String toString() {
        return "Card{" +
                "encryptedCardNumber='" + encryptedCardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", cardEntity='" + cardEntity + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", bank='" + bank + '\'' +
                '}';
    }
}
