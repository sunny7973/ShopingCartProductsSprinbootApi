package com.hackerrank.eshopping.product.dashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DynamicUpdate
public class Product {
    @Id
    @Column(updatable = false, nullable = false)
    private Long id;
    @Column(updatable = false)
    private String name;
    @Column(updatable = false)
    private String category;
    @JsonProperty("retail_price")
    private Double retailPrice;
    @JsonProperty("discounted_price")
    private Double discountedPrice;
    private Boolean availability;
    @JsonIgnore
    @Formula(value="CEIL(((retail_price - discounted_price) / retail_price) * 100)")
    private Integer percentage;

    public Product() {
    }

    public Product(Long id, String name, String category, Double retailPrice, Double discountedPrice, Boolean availability) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.retailPrice = retailPrice;
        this.discountedPrice = discountedPrice;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
