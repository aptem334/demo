package com.aptem334.demo.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
public class Accounts {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_ACCOUNTS")
    @SequenceGenerator(name="SEQUENCE_ACCOUNTS", sequenceName="ACCOUNT_SEQ", allocationSize=1)
    private Integer account_number;

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

    public Accounts(Integer account_number, double amount, Date opening_date, Date validity_period, Users owner) {
        this.account_number = account_number;
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

    public Integer getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Integer account_number) {
        this.account_number = account_number;
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