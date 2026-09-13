package br.org.sesisenai.estoqueapi.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name="manufacturer")
public class Manufacturer {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long IdManufactured;

    @Column(nullable = false)
    private String nameManufacturer;

    @Column(nullable = true)
    private String documentManufacturer;

    @Column(nullable = true)
    private String contactManufacturer;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAtManufacturer;

    public Long getIdManufactured() {
        return IdManufactured;
    }

    public void setIdManufactured(Long idManufactured) {
        IdManufactured = idManufactured;
    }

    public String getNameManufacturer() {
        return nameManufacturer;
    }

    public void setNameManufacturer(String nameManufacturer) {
        this.nameManufacturer = nameManufacturer;
    }

    public String getDocumentManufacturer() {
        return documentManufacturer;
    }

    public void setDocumentManufacturer(String documentManufacturer) {
        this.documentManufacturer = documentManufacturer;
    }

    public String getContactManufacturer() {
        return contactManufacturer;
    }

    public void setContactManufacturer(String contactManufacturer) {
        this.contactManufacturer = contactManufacturer;
    }

    public Date getCreatedAtManufacturer() {
        return createdAtManufacturer;
    }

    public void setCreatedAtManufacturer(Date createdAtManufacturer) {
        this.createdAtManufacturer = createdAtManufacturer;
    }
}
