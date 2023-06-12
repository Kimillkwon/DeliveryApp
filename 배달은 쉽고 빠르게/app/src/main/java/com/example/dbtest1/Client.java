package com.example.dbtest1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Client extends User{
    @PrimaryKey(autoGenerate = true)
    private int index;
    private String name;
    private String mail;
    private String accNum;
    private String address;


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "NAME: "+name+"     Email: "+mail+"\n"+
                "ACCOUNT NUM: "+name+"     ADDRESS: "+mail;
    }
}
