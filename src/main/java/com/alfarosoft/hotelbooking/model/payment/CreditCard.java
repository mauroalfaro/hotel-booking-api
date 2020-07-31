package com.alfarosoft.hotelbooking.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public class CreditCard extends Card{

    @JsonProperty("ccv")
    private String ccv;

    public CreditCard(String encryptedCardNumber, Date expirationDate, String cardEntity, String cardHolderName, String bank, String ccv) {
        super(encryptedCardNumber, expirationDate, cardEntity, cardHolderName, bank);
        this.ccv = ccv;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(ccv, that.ccv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ccv);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "ccv='" + ccv + '\'' +
                '}';
    }
}
