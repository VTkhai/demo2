package com.example.demo2.entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_entity")
@Schema(description = "Product Model ")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id Product", example = "1")
    private Long idPro;

    @Schema(description = "Product name", example = "Bubble gum")
    @NotEmpty(message = "Name cannot be empty")
    private String productName;

    @Schema(description = "Product description", example = "Product light")
    @Column(length = 500) // Giới hạn độ dài của description
    private String description;

    @Schema( description= "Product price", example = "10000,00")
    @Min(value = 0, message = "Price must be non-negative")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "idSup")
    @Schema(description = "Supplier", example = "Someone")
    private Supplier supplierP;

}
