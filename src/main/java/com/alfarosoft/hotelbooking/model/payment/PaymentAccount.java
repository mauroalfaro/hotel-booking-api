package com.alfarosoft.hotelbooking.model.payment;

import com.alfarosoft.hotelbooking.model.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PaymentAccount {
    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("creditCard")
    private CreditCard creditCard;

    @JsonProperty("debitCard")
    private DebitCard debitCard;

    @JsonProperty("accountStatus")
    private AccountStatus accountStatus;

    public PaymentAccount(String customerId, CreditCard creditCard, DebitCard debitCard, AccountStatus accountStatus) {
        this.customerId = customerId;
        this.creditCard = creditCard;
        this.debitCard = debitCard;
        this.accountStatus = accountStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(DebitCard debitCard) {
        this.debitCard = debitCard;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentAccount that = (PaymentAccount) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(creditCard, that.creditCard) &&
                Objects.equals(debitCard, that.debitCard) &&
                accountStatus == that.accountStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, creditCard, debitCard, accountStatus);
    }

    @Override
    public String toString() {
        return "PaymentAccount{" +
                "customerId='" + customerId + '\'' +
                ", creditCard=" + creditCard +
                ", debitCard=" + debitCard +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
