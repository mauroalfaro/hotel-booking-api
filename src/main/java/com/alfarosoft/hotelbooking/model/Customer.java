package com.alfarosoft.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Customer")
public class Customer {
    @JsonProperty("dni")
    @Id
    private String dni;

    @JsonProperty("cuil")
    @Column(name = "cuil")
    private String cuil;

    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @JsonProperty("surname")
    @Column(name = "surname")
    private String surname;

    @JsonProperty("lastName")
    @Column(name = "lastName")
    private String lastName;

    @JsonProperty("birthDate")
    @Column(name = "birthDate")
    private Date birthDate;

    @JsonProperty("dateRegistered")
    @Column(name = "dateRegistered")
    private Date dateRegistered;

    @JsonProperty("phone")
    @Column(name = "phone")
    private String phone;

    @JsonProperty("alternativePhone")
    @Column(name = "alternativePhone")
    private String alternativePhone;

    @JsonProperty("paymentAccountId")
    @Column(name = "paymentAccountId")
    private String paymentAccountId;

    public Customer(String dni, String cuil, String name, String surname, String lastName, Date birthDate, Date dateRegistered, String phone, String alternativePhone, String paymentAccountId) {
        this.dni = dni;
        this.cuil = cuil;
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.dateRegistered = dateRegistered;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.paymentAccountId = paymentAccountId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternativePhone() {
        return alternativePhone;
    }

    public void setAlternativePhone(String alternativePhone) {
        this.alternativePhone = alternativePhone;
    }

    public String getPaymentAccountId() {
        return paymentAccountId;
    }

    public void setPaymentAccountId(String paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(dni, customer.dni) &&
                Objects.equals(cuil, customer.cuil) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(birthDate, customer.birthDate) &&
                Objects.equals(dateRegistered, customer.dateRegistered) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(alternativePhone, customer.alternativePhone) &&
                Objects.equals(paymentAccountId, customer.paymentAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, cuil, name, surname, lastName, birthDate, dateRegistered, phone, alternativePhone, paymentAccountId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "dni='" + dni + '\'' +
                ", cuil='" + cuil + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", dateRegistered=" + dateRegistered +
                ", phone='" + phone + '\'' +
                ", alternativePhone='" + alternativePhone + '\'' +
                ", paymentAccountId='" + paymentAccountId + '\'' +
                '}';
    }
}
