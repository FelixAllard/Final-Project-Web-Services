package org.champqcsoft.apigateway.mapperlayer.transactions;

import org.champqcsoft.apigateway.presentationlayer.transactions.PurchaseReceiptController;
import org.champqcsoft.apigateway.presentationlayer.transactions.PurchaseReceiptResponseModel;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface PurchaseReceiptResponseMapper {
    PurchaseReceiptResponseModel entityToResponseModel(PurchaseReceiptResponseModel purchaseReceipt);

    @AfterMapping
    default void addLinks(@MappingTarget PurchaseReceiptResponseModel model){
        Link selfLink = linkTo(methodOn(PurchaseReceiptController.class)
                        .getPurchaseReceiptByPurchaseReceiptId(model.getPurchaseReceiptId()))
                .withSelfRel();
        model.add(selfLink);

        Link productsLink =
                linkTo(methodOn(PurchaseReceiptController.class)
                        .getPurchaseReceipts())
                        .withRel("all purchaseReceipts");
        model.add(productsLink);

    }
}
