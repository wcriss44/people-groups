package com.theironyard.novauc.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PbUser {
    String affiliation;
    String emailAddress;
    String flavor;
    int id;
    String name;
    String phone;

    public PbUser() {
    }

//    public PbUser(String affiliation, String emailAddress, String flavor, int id, String name, String phone) {
//        this.affiliation = affiliation;
//        this.emailAddress = emailAddress;
//        this.flavor = flavor;
//        this.id = id;
//        this.name = name;
//        this.phone = phone;
//    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
