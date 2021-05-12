package com.cassandra.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "c_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Timestamp createdDateTime;

    @ManyToOne
    @JoinColumn(name = "visitId", nullable = false)
    private Visit visit;

    @ManyToOne
    @JoinColumn(name = "itemId", nullable = false)
    private FoodItem foodItem;

    private String comment;

    @Column(nullable = false)
    private Float quantities;

    private Float priceGross;

    private Float priceNet;

    private Float vat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getQuantities() {
        return quantities;
    }

    public void setQuantities(Float quantities) {
        this.quantities = quantities;
    }

    public Float getPriceGross() {
        return priceGross;
    }

    public void setPriceGross(Float priceGross) {
        this.priceGross = priceGross;
    }

    public Float getPriceNet() {
        return priceNet;
    }

    public void setPriceNet(Float priceNet) {
        this.priceNet = priceNet;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }
}
