package com.example.ecom_application.data;

public class Products {

    private String Name, Image_Url, PID;

    private Long Price;

    public Products() {}

    public Products(String Image_Url, String Name, Long Price, String PID) {
        this.Image_Url = Image_Url;
        this.Name = Name;
        this.Price = Price;
        this.PID = PID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage_Url() {
        return Image_Url;
    }

    public void setImage_Url(String image_Url) {
        Image_Url = image_Url;
    }

    public Long getPrice() {
        return Price;
    }

    public void setPrice(Long price) {
        Price = price;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }
}
