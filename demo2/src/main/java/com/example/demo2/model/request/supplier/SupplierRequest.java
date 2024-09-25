package com.example.demo2.model.request.supplier;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequest {
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "address cannot be empty")
    private String address;
}

