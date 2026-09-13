package br.org.sesisenai.estoqueapi.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="product_id")
    private Product product;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantityAtTrigger;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal minStockLevelAtTrigger;

    @Column(nullable = false)
    private boolean resolved;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime triggeredAt;

    public Alert() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getQuantityAtTrigger() {
        return quantityAtTrigger;
    }

    public void setQuantityAtTrigger(BigDecimal quantityAtTrigger) {
        this.quantityAtTrigger = quantityAtTrigger;
    }

    public BigDecimal getMinStockLevelAtTrigger() {
        return minStockLevelAtTrigger;
    }

    public void setMinStockLevelAtTrigger(BigDecimal minStockLevelAtTrigger) {
        this.minStockLevelAtTrigger = minStockLevelAtTrigger;
    }

    public LocalDateTime getTriggeredAt() {
        return triggeredAt;
    }

    public void setTriggeredAt(LocalDateTime triggeredAt) {
        this.triggeredAt = triggeredAt;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
