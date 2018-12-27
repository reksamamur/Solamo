package com.ta.solamo.model;

public class OfferSetGet {
    private int menu_id;
    private String menu_name;
    private String menu_category;
    private String menu_image;
    private int menu_price;

    public OfferSetGet() {
    }

    public OfferSetGet(int menu_id, String menu_name, String menu_category, String menu_image, int menu_price) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_category = menu_category;
        this.menu_image = menu_image;
        this.menu_price = menu_price;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_category() {
        return menu_category;
    }

    public void setMenu_category(String menu_category) {
        this.menu_category = menu_category;
    }

    public String getMenu_image() {
        return menu_image;
    }

    public void setMenu_image(String menu_image) {
        this.menu_image = menu_image;
    }

    public int getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(int menu_price) {
        this.menu_price = menu_price;
    }
}
