package com.example.ecom_application.data;

public class Products {

    private String Name, Image_Url;

    private Long Price;

    public Products() {}

    public Products(String Image_Url, String Name, Long Price) {
        this.Image_Url = Image_Url;
        this.Name = Name;
        this.Price = Price;
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
}
