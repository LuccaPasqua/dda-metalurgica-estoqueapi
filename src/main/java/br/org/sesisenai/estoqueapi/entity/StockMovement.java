package br.org.sesisenai.estoqueapi.entity;

import br.org.sesisenai.estoqueapi.enums.MotiveStockMovement;
import br.org.sesisenai.estoqueapi.enums.TypeStockMovement;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Entity
@Table(name="stock_movement")
@EntityListeners(AuditingEntityListener.class)
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="supplier_id", nullable=true)
    private Supplier supplier;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="purchase_request_id", nullable=true)
    private PurchaseRequest purchaseRequest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeStockMovement type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MotiveStockMovement motive;

    @Column(nullable = true)
    private String reference;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public StockMovement() {
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public TypeStockMovement getType() {
        return type;
    }

    public void setType(TypeStockMovement type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public MotiveStockMovement getMotive() {
        return motive;
    }

    public void setMotive(MotiveStockMovement motive) {
        this.motive = motive;
    }

    public PurchaseRequest getPurchaseRequest() {
        return purchaseRequest;
    }

    public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
