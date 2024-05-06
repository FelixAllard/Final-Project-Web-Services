package org.champqcsoft.transactionservice.datamapperlayer;

import org.champqcsoft.transactionservice.commons.enums.Price;
import org.champqcsoft.transactionservice.commons.identifiers.ClientIdentifier;
import org.champqcsoft.transactionservice.commons.identifiers.EmployeeIdentifier;
import org.champqcsoft.transactionservice.commons.identifiers.ProductIdentifier;
import org.champqcsoft.transactionservice.commons.identifiers.PurchaseReceiptIdentifier;
import org.champqcsoft.transactionservice.dataaccesslayer.CurrentDate;
import org.champqcsoft.transactionservice.dataaccesslayer.PurchaseReceipt;
import org.champqcsoft.transactionservice.dataaccesslayer.StoreAddress;
import org.champqcsoft.transactionservice.presentationlayer.PurchaseReceiptRequestModel;
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
