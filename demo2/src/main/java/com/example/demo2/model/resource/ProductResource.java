package com.example.demo2.model.resource;
import com.example.demo2.controller.product.ProductController;
import com.example.demo2.model.response.ProductResponse;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Getter
@Setter
public class ProductResource extends RepresentationModel<ProductResource> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String productName;

    @Column(length = 500)
    private String description;

    @Min(value = 0, message = "Price must be non-negative")
    private Double price;

    // Getters và Setters

    public ProductResource(ProductResponse productResponse) {
        this.id = productResponse.getId();
        this.productName = productResponse.getProductName();
        this.description = productResponse.getDescription();
        this.price = productResponse.getPrice();

        // Thêm liên kết hypermedia
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class)
                .getProduct(id)).withSelfRel());

        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class)
                .updateProduct(id, null)).withRel("update"));

        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class)
                .deleteProduct(id)).withRel("delete"));
    }
}
