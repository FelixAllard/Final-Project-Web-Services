package org.champqcsoft.transactionservice.datamapperlayer;

import org.champqcsoft.transactionservice.dataaccesslayer.PurchaseReceipt;
import org.champqcsoft.transactionservice.domainclientlayer.customers.ClientsModel;

import org.champqcsoft.transactionservice.domainclientlayer.employees.EmployeeModel;

import org.champqcsoft.transactionservice.domainclientlayer.products.ProductModel;
import org.champqcsoft.transactionservice.presentationlayer.PurchaseReceiptController;
import org.champqcsoft.transactionservice.presentationlayer.PurchaseReceiptResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface PurchaseReceiptResponseMapper {
    @Mapping(expression = "java(purchaseReceipt.getPurchaseReceiptIdentifier().getPurchaseReceiptId())", target = "purchaseReceiptId")
    @Mapping(expression = "java(purchaseReceipt.getAmount())", target = "amount")
    @Mapping(expression = "java(purchaseReceipt.getCurrentDate().getTransactionHour())", target = "transactionHour")
    @Mapping(expression = "java(purchaseReceipt.getCurrentDate().getDate().getDay())", target = "day")
    @Mapping(expression = "java(purchaseReceipt.getCurrentDate().getDate().getMonth())", target = "month")
    @Mapping(expression = "java(purchaseReceipt.getCurrentDate().getDate().getYear())", target = "year")
    @Mapping(expression = "java(purchaseReceipt.getPrice().getValue())", target = "value")
    @Mapping(expression = "java(purchaseReceipt.getPrice().getCurrency().name())", target = "currency")
    @Mapping(expression = "java(purchaseReceipt.getStoreAddress().getStreet())", target = "street")
    @Mapping(expression = "java(purchaseReceipt.getStoreAddress().getCity())", target = "city")
    @Mapping(expression = "java(purchaseReceipt.getStoreAddress().getState())", target = "state")
    @Mapping(expression = "java(purchaseReceipt.getStoreAddress().getPostalCode())", target = "postalCode")
    @Mapping(expression = "java(purchaseReceipt.getStoreAddress().getCountry())", target = "country")
    @Mapping(expression = "java(client.getClientIdentifier().getClientId())", target = "clientId")
    @Mapping(expression = "java(client.getName())", target = "clientName")
    @Mapping(expression = "java(product.getProductIdentifier().getProductId())", target = "productId")
    @Mapping(expression = "java(product.getName())", target = "productName")
    @Mapping(expression = "java(employee.getEmployeeIdentifier().getEmployeeId())", target = "employeeId")
    @Mapping(expression = "java(employee.getName())", target = "employeeName")



    PurchaseReceiptResponseModel entityToResponseModel(
            PurchaseReceipt purchaseReceipt,
            ClientsModel client,
            EmployeeModel employee,
            ProductModel product

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
