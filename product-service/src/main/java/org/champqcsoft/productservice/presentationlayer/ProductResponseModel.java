package org.champqcsoft.productservice.presentationlayer;


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

    private String name;
    private String description;
    private int palletId;
    private  String manufacturer;
    private int day;
    private int month;
    private int year;
    private String productAvailability;
    private double value;
    private String currency;
    private String categoryName;
    private String categoryDescription;
    private String url;
    private String altText;
}
