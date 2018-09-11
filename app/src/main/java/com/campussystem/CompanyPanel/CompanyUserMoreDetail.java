package com.campussystem.CompanyPanel;

/**
 * Created by User on 3/13/2018.
 */

public class CompanyUserMoreDetail {
    String id;
    String name;
    String email;
    String address;
    String companyType;
    String contacts;
    String genere;
    String password;

    public CompanyUserMoreDetail() {
    }


    public CompanyUserMoreDetail(String id, String name, String email, String address, String companyType, String contacts,String genere,String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.companyType = companyType;
        this.contacts = contacts;
        this.genere=genere;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public String getGenere() {
        return genere;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCompanyType() {
        return companyType;
    }

    public String getContacts() {
        return contacts;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
