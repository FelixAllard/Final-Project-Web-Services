package org.champqcsoft.apigateway.subdomains.transactionsmanagementsubdomain.dataaccesslayer;


import org.champqcsoft.apigateway.commons.enums.Price;
import org.champqcsoft.apigateway.commons.identifiers.ClientIdentifier;
import org.champqcsoft.apigateway.commons.identifiers.EmployeeIdentifier;
import org.champqcsoft.apigateway.commons.identifiers.ProductIdentifier;
import org.champqcsoft.apigateway.commons.identifiers.PurchaseReceiptIdentifier;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_receipts")
@Data
@NoArgsConstructor
public class PurchaseReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //Aggregate
    @Embedded
    private ClientIdentifier clientIdentifier;
    @Embedded
    private ProductIdentifier productIdentifier;
    @Embedded
    private EmployeeIdentifier employeeIdentifier;


    @Embedded
    private PurchaseReceiptIdentifier purchaseReceiptIdentifier;

    private double amount;
    @Embedded
    private CurrentDate currentDate;
    @Embedded
    private Price price;
    @Embedded
    private StoreAddress storeAddress;

    public PurchaseReceipt(
            PurchaseReceiptIdentifier purchaseReceiptIdentifier,
            CurrentDate currentDate,
            Price price,
            StoreAddress storeAddress
    ){
        this.purchaseReceiptIdentifier = purchaseReceiptIdentifier;
        this.currentDate = currentDate;
        this.price = price;
        this.storeAddress=storeAddress;
    }
}
