package com.campussystem.CompanyPanel;

/**
 * Created by User on 3/7/2018.
 */

public class ComapnyClass {
    String id;
    String Address;
    String OwnerName;

    public ComapnyClass() {
    }

    public ComapnyClass(String id, String address, String ownerName) {
        this.id = id;
        Address = address;
        OwnerName = ownerName;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return Address;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }
}
