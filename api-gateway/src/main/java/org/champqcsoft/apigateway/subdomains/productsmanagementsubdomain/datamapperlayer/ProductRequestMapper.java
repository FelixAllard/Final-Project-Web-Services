package org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.datamapperlayer;


import org.champqcsoft.apigateway.commons.enums.Price;
import org.champqcsoft.apigateway.commons.identifiers.ProductIdentifier;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.dataaccesslayer.CategoryArticle;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.dataaccesslayer.Image;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.dataaccesslayer.PalletImportedFrom;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.dataaccesslayer.Product;
import org.champqcsoft.apigateway.subdomains.productsmanagementsubdomain.presentationlayer.ProductRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {
    @Mapping(target = "id", ignore = true)
    Product requestModelToEntity(
            ProductRequestModel productRequestModel,
            ProductIdentifier productIdentifier,
            PalletImportedFrom palletImportedFrom,
            Price price,
            CategoryArticle categoryArticle,
            Image image
    );
}
