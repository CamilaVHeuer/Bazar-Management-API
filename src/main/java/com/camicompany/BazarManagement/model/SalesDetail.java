package com.camicompany.BazarManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalesDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleDetailId;
    private Integer quantity;
    private Double unitPrice;
    private Double subTotal;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonBackReference
    private Sale sale;

}
