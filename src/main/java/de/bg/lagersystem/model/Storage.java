package de.bg.lagersystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "storage")
@Data
public class Storage
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "shelf_number", nullable = false)
    private Integer shelfNumber;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "delivery_time", nullable = false)
    private LocalDateTime deliveryTime;

    @Column(nullable = false)
    private String seller;

    private Integer minStock;

    @Column(name = "stock_alert_sent", nullable = false)
    private boolean stockAlertSent;

    @Transient
    private String useFor;

}
