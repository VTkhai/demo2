package com.example.demo2.model.response;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSup;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "address cannot be empty")
    private String address;
}

