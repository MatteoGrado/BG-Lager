package de.bg.lagersystem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "storage")
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

    @Transient
    private Integer minStock;

    @Transient
    private String useFor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Integer getShelfNumber() { return shelfNumber; }
    public void setShelfNumber(Integer shelfNumber) { this.shelfNumber = shelfNumber; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public LocalDateTime getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(LocalDateTime deliveryTime) { this.deliveryTime = deliveryTime; }
    public String getSeller() { return seller; }
    public void setSeller(String seller) { this.seller = seller; }
    public Integer getMinStock() { return minStock; }
    public void setMinStock(Integer minStock) { this.minStock = minStock; }
    public String getUseFor() { return useFor; }
    public void setUseFor(String useFor) { this.useFor = useFor; }

}
