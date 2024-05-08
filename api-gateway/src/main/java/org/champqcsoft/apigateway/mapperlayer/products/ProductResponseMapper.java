package org.champqcsoft.apigateway.mapperlayer.products;


import org.champqcsoft.apigateway.presentationlayer.employees.EmployeeResponseModel;
import org.champqcsoft.apigateway.presentationlayer.products.ProductResponseModel;
import org.champqcsoft.apigateway.presentationlayer.products.ProductController;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    ProductResponseModel entityToResponseModel(ProductResponseModel productResponseModel);
    List<ProductResponseModel> entityListToResponseModelList(List<ProductResponseModel> productResponseModelList);

    @AfterMapping
    default void addLinks(@MappingTarget ProductResponseModel model){
        Link selfLink = linkTo(methodOn(ProductController.class)
                .getProductByProductId(model.getProductId()))
                .withSelfRel();
        model.add(selfLink);

        Link productsLink =
                linkTo(methodOn(ProductController.class)
                        .getProducts())
                        .withRel("all products");
        model.add(productsLink);

    }
}
