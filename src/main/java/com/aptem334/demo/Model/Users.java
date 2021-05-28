package com.aptem334.demo.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="ENTITY_SEQ", allocationSize=1)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    public String filename;

    @NotBlank(message = "Phone is mandatory")
    public String phone;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Accounts> account = new ArrayList<>();


    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Date of birth is mandatory")
    private String date_of_birth;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Accounts> getAccount() {
        return account;
    }

    public void setAccount(List<Accounts> account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users() {
    }

    public Users(String name, String email, String phone, List<Accounts> account, String address, String date_of_birth) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.account = account;
        this.address = address;
        this.date_of_birth = date_of_birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}