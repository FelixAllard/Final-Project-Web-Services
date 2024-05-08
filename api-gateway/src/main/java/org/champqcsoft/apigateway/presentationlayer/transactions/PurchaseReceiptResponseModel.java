package org.champqcsoft.apigateway.presentationlayer.transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReceiptResponseModel extends RepresentationModel<PurchaseReceiptResponseModel> {
    private String purchaseReceiptId;


    private String clientIdTransaction;
    private String clientNameTransaction;
    private String productIdTransaction;
    private String productNameTransaction;
    private String employeeIdTransaction;
    private String employeeNameTransaction;

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
