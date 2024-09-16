package com.example.demo2.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier_entity")
@Schema(description = "Supplier Model ")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id Supplier", example = "12")
    private Long idSup;

    @Schema(description = "Supplier name", example = "Somoneelse")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Schema(description = "Supplier address", example = "78 Le Hong Phong")
    @NotEmpty(message = "address cannot be empty")
    private String address;

    @OneToMany(mappedBy = "supplierP")
    @Schema(description = "Productlist", example = "Something")
    private  List<Product> products;

}
