package com.example.demo2.product_supplier.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @Size(max = 400, min = 0, message = "Max characters is 400") // Giới hạn độ dài của description
    private String description;

    @Schema( description= "Product price", example = "10000,00")
    @Min(value = 0, message = "Price must be non-negative")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sup")
    private Supplier supplier;

}
