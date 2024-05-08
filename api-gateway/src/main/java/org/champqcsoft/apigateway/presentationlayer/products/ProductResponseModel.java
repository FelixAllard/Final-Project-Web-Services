package org.champqcsoft.apigateway.presentationlayer.products;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseModel extends RepresentationModel<ProductResponseModel> {
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
