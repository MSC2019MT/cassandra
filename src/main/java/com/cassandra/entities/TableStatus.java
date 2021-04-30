package com.cassandra.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="c_tablestatus")
public class TableStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="tableId",nullable = false)
    private TableMaster tableMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TableMaster getTableMaster() {
        return tableMaster;
    }

    public void setTableMaster(TableMaster tableMaster) {
        this.tableMaster = tableMaster;
    }
}
