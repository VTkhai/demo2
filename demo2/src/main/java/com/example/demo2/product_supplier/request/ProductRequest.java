package com.example.demo2.product_supplier.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ProductRequest {
    @NotEmpty(message = "Name cannot be empty")
    private String productName;

    @Length(max = 255)
    private String description;

    @Min(value = 0, message = "Price must be non-negative")
    private Double price;
}

