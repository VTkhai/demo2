package com.example.demo2.product_supplier.resource;

import com.example.demo2.product_supplier.controller.SupplierController;
import com.example.demo2.product_supplier.response.SupplierResponse;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.RepresentationModel;
@Getter
@Setter
public class SupplierResource extends RepresentationModel<SupplierResource>  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSup;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "address cannot be empty")
    private String address;

    public SupplierResource(SupplierResponse supplierResponse) {
        this.idSup = supplierResponse.getIdSup();
        this.name = supplierResponse.getName();
        this.address = supplierResponse.getAddress();


        // Thêm liên kết hypermedia
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SupplierController.class)
                .getSupplierDetails(idSup)).withSelfRel());

        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SupplierController.class)
                .updateSupplier(idSup, null)).withRel("update"));

        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SupplierController.class)
                .deleteSupplier(idSup)).withRel("delete"));
    }
}
