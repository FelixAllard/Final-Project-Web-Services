package org.champqcsoft.apigateway.subdomains.transactionsmanagementsubdomain.datamapperlayer;

import org.champqcsoft.apigateway.commons.enums.Price;
import org.champqcsoft.apigateway.commons.identifiers.ClientIdentifier;
import org.champqcsoft.apigateway.commons.identifiers.EmployeeIdentifier;
import org.champqcsoft.apigateway.commons.identifiers.ProductIdentifier;
import org.champqcsoft.apigateway.commons.identifiers.PurchaseReceiptIdentifier;
import org.champqcsoft.apigateway.subdomains.transactionsmanagementsubdomain.dataaccesslayer.CurrentDate;
import org.champqcsoft.apigateway.subdomains.transactionsmanagementsubdomain.dataaccesslayer.PurchaseReceipt;
import org.champqcsoft.apigateway.subdomains.transactionsmanagementsubdomain.dataaccesslayer.StoreAddress;
import org.champqcsoft.apigateway.subdomains.transactionsmanagementsubdomain.presentationlayer.PurchaseReceiptRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseReceiptRequestMapper {

    @Mapping(target = "id", ignore = true)
    PurchaseReceipt requestModelToEntity(
            PurchaseReceiptRequestModel purchaseReceiptRequestModel,
            PurchaseReceiptIdentifier purchaseReceiptIdentifier,
            CurrentDate currentDate,
            Price price,
            StoreAddress storeAddress,
            ClientIdentifier clientIdentifier,
            ProductIdentifier productIdentifier,
            EmployeeIdentifier employeeIdentifier
    );
}
