package com.example.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "staff")
public class Staff extends User{

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse warehouse;
}
