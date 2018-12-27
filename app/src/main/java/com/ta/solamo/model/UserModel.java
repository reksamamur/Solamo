package com.ta.solamo.model;

import java.util.ArrayList;

public class UserModel {

    ArrayList arrl_Menus;
    int user_Id;
    String user_Name;
    String user_Table;
    int user_totalPrice;

    public UserModel(ArrayList arrl_Menus, int user_Id, String user_Name, String user_Table, int user_totalPrice) {
        this.arrl_Menus = arrl_Menus;
        this.user_Id = user_Id;
        this.user_Name = user_Name;
        this.user_Table = user_Table;
        this.user_totalPrice = user_totalPrice;
    }

    public ArrayList getArrl_Menus() {
        return arrl_Menus;
    }

    public void setArrl_Menus(ArrayList arrl_Menus) {
        this.arrl_Menus = arrl_Menus;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Table() {
        return user_Table;
    }

    public void setUser_Table(String user_Table) {
        this.user_Table = user_Table;
    }

    public int getUser_totalPrice() {
        return user_totalPrice;
    }

    public void setUser_totalPrice(int user_totalPrice) {
        this.user_totalPrice = user_totalPrice;
    }
}
