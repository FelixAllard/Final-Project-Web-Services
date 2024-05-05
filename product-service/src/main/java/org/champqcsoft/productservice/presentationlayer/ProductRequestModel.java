package org.champqcsoft.productservice.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestModel {
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
