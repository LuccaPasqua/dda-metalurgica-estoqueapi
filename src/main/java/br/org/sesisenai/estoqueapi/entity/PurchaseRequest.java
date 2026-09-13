package br.org.sesisenai.estoqueapi.entity;

import br.org.sesisenai.estoqueapi.enums.StatusPurchaseRequest;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "purchase_request")
public class PurchaseRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @OneToOne(optional = true)
    @JoinColumn(name="alert_id", nullable = true)
    private Alert alert;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User createdBy;

    @Column(nullable = false, precision = 10, scale=2)
    private BigDecimal requestedQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPurchaseRequest status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public PurchaseRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public StatusPurchaseRequest getStatus() {
        return status;
    }

    public void setStatus(StatusPurchaseRequest status) {
        this.status = status;
    }

    public BigDecimal getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(BigDecimal requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
