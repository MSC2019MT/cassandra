package com.cassandra.beans;

import com.cassandra.entities.Items;

public class RestaurantItemsBean {
    private Long restaurantId;
    private Items items;
    private Float priceNet;
    private Float pricegross;
    private Float vat;
    private String itemName;
    private Float quantities;
    private Float itemWiseTotal;

    public RestaurantItemsBean(Long restaurantId, Items items, Float priceNet, Float pricegross, Float vat) {
        this.restaurantId = restaurantId;
        this.items = items;
        this.priceNet = priceNet;
        this.pricegross = pricegross;
        this.vat = vat;
    }

    public RestaurantItemsBean(Float priceNet, Float pricegross, Float vat, String itemName, Float quantities) {
        this.priceNet = priceNet;
        this.pricegross = pricegross;
        this.vat = vat;
        this.itemName = itemName;
        this.quantities = quantities;
    }

    public Float getItemWiseTotal() {
        return itemWiseTotal;
    }

    public void setItemWiseTotal(Float itemWiseTotal) {
        this.itemWiseTotal = itemWiseTotal;
    }

    public Float getQuantities() {
        return quantities;
    }

    public void setQuantities(Float quantities) {
        this.quantities = quantities;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Float getPriceNet() {
        return priceNet;
    }

    public void setPriceNet(Float priceNet) {
        this.priceNet = priceNet;
    }

    public Float getPricegross() {
        return pricegross;
    }

    public void setPricegross(Float pricegross) {
        this.pricegross = pricegross;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }
}
