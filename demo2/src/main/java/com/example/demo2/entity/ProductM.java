package com.example.demo2.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_entity")
@Schema(description = "Product Model ")
public class ProductM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id Product", example = "1")
    private Long idPro;

    @Schema(description = "Product name", example = "Bubble gum")
    private String productName;

    @Schema(description = "Product description", example = "Product light")
    private String description;

    @Schema( description= "Product price", example = "10000,00")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "idSup")
    @Schema(description = "Supplier", example = "Someone")
    private Supplier supplierP;

}