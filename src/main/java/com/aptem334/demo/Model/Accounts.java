package com.aptem334.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import java.util.Date;

@Entity
public class Accounts {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_ACCOUNTS")
    @SequenceGenerator(name="SEQUENCE_ACCOUNTS", sequenceName="ACCOUNT_SEQ", allocationSize=1)
    @Column(name = "account_number")
    private Integer accountNumber;

    @DecimalMin(value = "0.00", message = "Amount is Empty")
    private double amount;

    @CreatedDate
    private Date opening_date;

    @Future
    private Date validity_period;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private Users owner;

    public Accounts() {

    }

    public Accounts(Integer accountNumber, double amount, Date opening_date, Date validity_period, Users owner) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.opening_date = opening_date;
        this.validity_period = validity_period;
        this.owner = owner;
    }

    public Users getOwner() {
        return owner;
    }


    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getOpening_date() {
        return opening_date;
    }

    public void setOpening_date(Date opening_date) {
        this.opening_date = opening_date;
    }

    public Date getValidity_period() {
        return validity_period;
    }

    public void setValidity_period(Date validity_period) {
        this.validity_period = validity_period;
    }

}