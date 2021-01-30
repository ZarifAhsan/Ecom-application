package com.example.ecom_application.data;

import java.util.ArrayList;

public class Order {
    private String UserID;
    private int TotalCost;
    private ArrayList<String> PIDs;

    private Order() {}

    public Order(ArrayList<String> PIDs, String UserID, int TotalCost) {
        this.PIDs = PIDs;
        this.UserID = UserID;
        this.TotalCost = TotalCost;
    }

    public ArrayList<String> getPIDs() {
        return PIDs;
    }

    public void setPIDs(ArrayList<String> PIDs) {
        this.PIDs = PIDs;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public int getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(int totalCost) {
        TotalCost = totalCost;
    }
}
