package org.champqcsoft.apigateway.subdomains.transactionsmanagementsubdomain.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReceiptRequestModel {
    private String purchaseReceiptId;

    private String clientId;
    private String productId;
    private String employeeId;

    private double amount;
    private int transactionHour;
    private int day;
    private int month;
    private int year;
    private double value;
    private String currency;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
