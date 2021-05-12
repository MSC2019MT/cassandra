package com.cassandra.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c_tablemaster")
public class TableMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;

    private String description;

    private Long qrCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQrCode() {
        return qrCode;
    }

    public void setQrCode(Long qrCode) {
        this.qrCode = qrCode;
    }
}
