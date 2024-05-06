package org.champqcsoft.transactionservice.dataaccesslayer;


import jakarta.persistence.*;
import org.champqcsoft.transactionservice.commons.enums.Price;
import org.champqcsoft.transactionservice.commons.identifiers.ClientIdentifier;
import org.champqcsoft.transactionservice.commons.identifiers.EmployeeIdentifier;
import org.champqcsoft.transactionservice.commons.identifiers.ProductIdentifier;
import org.champqcsoft.transactionservice.commons.identifiers.PurchaseReceiptIdentifier;
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
