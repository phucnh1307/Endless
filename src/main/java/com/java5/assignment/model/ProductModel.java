package com.java5.assignment.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @NotBlank(message = "Please enter product name")
    private String productName;
    @NotBlank(message = "Please enter product description")
    private String productDescription;
    private Integer productCategory;
    private Integer productBrand;
    private boolean productStatus;
}