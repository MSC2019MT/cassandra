package com.cassandra.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c_visitorders")
public class VisitOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visitId", nullable = false)
    private Visits visits;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Orders orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visits getVisits() {
        return visits;
    }

    public void setVisits(Visits visits) {
        this.visits = visits;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
