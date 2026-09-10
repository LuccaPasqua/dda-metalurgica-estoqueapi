package br.org.sesisenai.estoqueapi.entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long IdProduct;

    private Long IdManufacturedId;

    @Column
    private String nameProduct;

    private String descriptionProduct;

    private String codeProduct;

    private String unitMesurmentProduct;

    private Decimal quantityProduct;

    private Date created_at;



}
