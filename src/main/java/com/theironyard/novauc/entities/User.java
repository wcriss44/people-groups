package com.theironyard.novauc.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "the unique but not very random SSN")
    int id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "what is in a name?")
    String name;

    @Column(nullable = false)
    @ApiModelProperty(notes = "Where the sidewalk ends")
    String address;

    @Column(nullable = false)
    @ApiModelProperty(notes = "Depending on the connection, this can feel like snail-mail")
    String email;

    @Column(nullable = false)
    @ApiModelProperty(notes = "More like shell-phone!")
    String cellphone;

    @Column(nullable = false)
    @ApiModelProperty(notes = "Service should be mandatory!")
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
