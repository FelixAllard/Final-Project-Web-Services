package org.champqcsoft.apigateway.presentationlayer.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestModel {
    private String productId;

    private String nameProduct;
    private String descriptionProduct;
    private int palletIdProduct;
    private  String manufacturerProduct;
    private int dayProduct;
    private int monthProduct;
    private int yearProduct;
    private String productAvailabilityProduct;
    private double valueProduct;
    private String currencyProduct;
    private String categoryNameProduct;
    private String categoryDescriptionProduct;
    private String urlProduct;
    private String altTextProduct;
}
