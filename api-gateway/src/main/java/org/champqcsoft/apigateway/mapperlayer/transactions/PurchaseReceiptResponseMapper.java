package org.champqcsoft.apigateway.mapperlayer.transactions;

import org.champqcsoft.apigateway.mapperlayer.products.ProductResponseMapper;
import org.champqcsoft.apigateway.presentationlayer.customers.ClientResponseModel;
import org.champqcsoft.apigateway.presentationlayer.employees.EmployeeResponseModel;
import org.champqcsoft.apigateway.presentationlayer.products.ProductResponseModel;
import org.champqcsoft.apigateway.presentationlayer.transactions.PurchaseReceiptResponseModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseReceiptResponseMapper {
    PurchaseReceiptResponseModel entityToResponseModel(
            PurchaseReceiptResponseModel purchaseReceipt,
            ClientResponseModel client,
            EmployeeResponseModel employee,
            ProductResponseModel product
    );




    /*@AfterMapping
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

    }*/
}
