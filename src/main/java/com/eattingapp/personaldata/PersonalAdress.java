package com.eattingapp.personaldata;

import javax.persistence.Id;

public class PersonalAdress {

    @Id
    private long ID;

    private String Username;
    private String Name;
    private String Surname;
    private String Telephone;
    private String Postcode;
    private String Adress;

    public PersonalAdress(long ID, String username, String name, String surname, String telephone, String postcode, String adress) {
        this.ID = ID;
        Username = username;
        Name = name;
        Surname = surname;
        Telephone = telephone;
        Postcode = postcode;
        Adress = adress;
    }
    public PersonalAdress(String username) {
        Username = username;
        Name = "brak";
        Surname = "brak";
        Telephone = "brak";
        Postcode = "brak";
        Adress = "brak";
    }
    public PersonalAdress() {
        Username = "brak";
        Name = "brak";
        Surname = "brak";
        Telephone = "brak";
        Postcode = "brak";
        Adress = "brak";
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }


}
