package org.champqcsoft.transactionservice.presentationlayer;

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


    private String clientId;
    private String clientName;
    private String productId;
    private String productName;
    private String employeeId;
    private String employeeName;

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
