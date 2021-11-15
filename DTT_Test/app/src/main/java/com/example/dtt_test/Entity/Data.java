package com.example.dtt_test.Entity;


import java.util.Comparator;

public class Data {

    public Data(int id, String images, int price, int bedrooms, int bathrooms, int size, String description, String city,String zip, int latitude, int longitude, String createdDate) {
        this.id = id;
        this.images = images;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.size = size;
        this.description = description;
        this.city = city;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdDate = createdDate;

    }




    private int id;

    private String images;

    private int price;

    private int bedrooms;

    private int bathrooms;

    private int size;

    private String  zip;

    private String description;

    private String city;

    private int latitude;

    private int longitude;

    private String createdDate;

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public static Comparator<Data> Pricecompare = new Comparator<Data>() {
        @Override
        public int compare(Data price1, Data price2) {
            return price1.getPrice() - price2.getPrice() ;
        }
    };



}
