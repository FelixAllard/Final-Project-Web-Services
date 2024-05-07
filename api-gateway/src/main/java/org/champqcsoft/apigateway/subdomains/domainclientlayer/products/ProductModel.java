package org.champqcsoft.apigateway.subdomains.domainclientlayer.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private String productId;

    private String name;
    private String description;
    private int palletId;
    private  String manufacturer;
    private int day;
    private int month;
    private int year;
    private String productAvailability;
    private double valueProduct;
    private String currencyProduct;
    private String categoryName;
    private String categoryDescription;
    private String url;
    private String altText;
}