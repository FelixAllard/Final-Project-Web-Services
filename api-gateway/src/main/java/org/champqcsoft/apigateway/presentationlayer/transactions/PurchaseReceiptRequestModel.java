package org.champqcsoft.apigateway.presentationlayer.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReceiptRequestModel {
    private String purchaseReceiptId;

    private String clientIdTransaction;
    private String productIdTransaction;
    private String employeeIdTransaction;

    private double amountTransaction;
    private int transactionHourTransaction;
    private int dayTransaction;
    private int monthTransaction;
    private int yearTransaction;
    private double valueTransaction;
    private String currencyTransaction;
    private String streetTransaction;
    private String cityTransaction;
    private String stateTransaction;
    private String postalCodeTransaction;
    private String countryTransaction;
}
