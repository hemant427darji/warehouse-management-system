package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "rack")
public class Rack {

    @ManyToOne
    @JoinColumn(name = "racked_block_id")
    private ReckedBlock reckedBlock;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rack_id", nullable = false, updatable = false)
    private String rackId;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @Column(name = "height", nullable = false, updatable = false)
    private double height;

    @Column(name = "width", nullable = false, updatable = false)
    private double width;

    @Column(name = "breath", nullable = false, updatable = false)
    private double breath;
}
