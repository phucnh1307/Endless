package com.java5.assignment.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PurchaseOrderDetails")
public class PurchaseOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PurchaseOrderDetailID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PurchaseOrderID", nullable = false)
    private PurchaseOrder purchaseOrderID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ProductVersionID", nullable = false)
    private ProductVersion productVersionID;

    @NotNull
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "Price", nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

}