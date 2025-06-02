package com.example.warehouse.entity;

import com.example.warehouse.enums.InBoundStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "in_bound_shipment")
public class InBoundShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "shipment_id", nullable = false, updatable = false)
    private String shipmentId;

    @Column(name = "product_title", nullable = false, updatable = false)
    private String productTile;

    @Column(name = "product_weight")
    private double productWeight;

    @Column(name = "product_length")
    private double productLength;

    @Column(name = "product_height")
    private double productHeight;

    @Column(name = "product_width")
    private double productWidth;

    @Column(name = "material_type", nullable = false)
    private String materialType;

    @Column(name = "care_instruction", nullable = false, updatable = false, length = 2000)
    private String careInstruction;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "product_price", nullable = false)
    private double productPrice;

    @Column(name = "seller_id", nullable = false, updatable = false)
    private String sellerId;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "status",nullable = false)
    private InBoundStatus status;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse warehouse;
}
