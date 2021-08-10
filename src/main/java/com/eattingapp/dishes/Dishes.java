package com.eattingapp.dishes;


import javax.persistence.Id;

public class Dishes {

    @Id
    private long ID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private String Description;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private int Price;

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Dishes(long id, String Name, String Description, int price) {
        this.ID=id;
        this.Name=Name;
        this.Description=Description;
        this.Price=price;

    }
    public Dishes() {
        this.Name="";
        this.Description="";
        this.Price=0;

    }
    public String toString(){
        return Name + " " + Description+" "+ Price;
    }
    public boolean equals(Dishes dishes){
        return dishes.getPrice()==this.Price && dishes.getDescription().equals(this.Description) && getDescription().equals(this.Name);
    }
}
