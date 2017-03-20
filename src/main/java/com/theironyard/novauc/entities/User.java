package com.theironyard.novauc.entities;

import javax.persistence.*;

/**
 * Created by octavio on 3/17/17.
 */
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String cellphone;

    @Column(nullable = false)
    String servicebranch;

    public  User(){

    }

    public User(String name, String address, String email, String cellphone, String servicebranch) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.cellphone = cellphone;
        this.servicebranch = servicebranch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getServicebranch() {
        return servicebranch;
    }

    public void setServicebranch(String servicebranch) {
        this.servicebranch = servicebranch;
    }
}
