package org.champqcsoft.productservice.datamapperlayer;


import org.champqcsoft.productservice.commons.enums.Price;
import org.champqcsoft.productservice.commons.identifiers.ProductIdentifier;
import org.champqcsoft.productservice.dataaccesslayer.CategoryArticle;
import org.champqcsoft.productservice.dataaccesslayer.Image;
import org.champqcsoft.productservice.dataaccesslayer.PalletImportedFrom;
import org.champqcsoft.productservice.dataaccesslayer.Product;
import org.champqcsoft.productservice.presentationlayer.ProductRequestModel;
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
