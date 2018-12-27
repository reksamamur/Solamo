package com.ta.solamo;

public class CartModel {
    int cart_id;
    String cart_nameItem;
    int cart_priceItem;
    int cart_quantity;
    String cart_image;

    public CartModel() {
    }

    public CartModel(int cart_id, String cart_nameItem, int cart_priceItem, int cart_quantity, String cart_image) {
        this.cart_id = cart_id;
        this.cart_nameItem = cart_nameItem;
        this.cart_priceItem = cart_priceItem;
        this.cart_quantity = cart_quantity;
        this.cart_image = cart_image;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public String getCart_nameItem() {
        return cart_nameItem;
    }

    public void setCart_nameItem(String cart_nameItem) {
        this.cart_nameItem = cart_nameItem;
    }

    public int getCart_priceItem() {
        return cart_priceItem;
    }

    public void setCart_priceItem(int cart_priceItem) {
        this.cart_priceItem = cart_priceItem;
    }

    public int getCart_quantity() {
        return cart_quantity;
    }

    public void setCart_quantity(int cart_quantity) {
        this.cart_quantity = cart_quantity;
    }

    public String getCart_image() {
        return cart_image;
    }

    public void setCart_image(String cart_image) {
        this.cart_image = cart_image;
    }
}
