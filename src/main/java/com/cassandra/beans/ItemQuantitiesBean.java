package com.cassandra.beans;

public class ItemQuantitiesBean extends BaseBean {
    private Long itemId;
    private Float quantities;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Float getQuantities() {
        return quantities;
    }

    public void setQuantities(Float quantities) {
        this.quantities = quantities;
    }
}
