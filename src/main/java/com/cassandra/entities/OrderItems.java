package com.cassandra.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c_orderitems")
public class OrderItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "itemId", nullable = false)
    private Items items;

    @Column(nullable = false)
    private Float quantities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Float getQuantities() {
        return quantities;
    }

    public void setQuantities(Float quantities) {
        this.quantities = quantities;
    }
}
