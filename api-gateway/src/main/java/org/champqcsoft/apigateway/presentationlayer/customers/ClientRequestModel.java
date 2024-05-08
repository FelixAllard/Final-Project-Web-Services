package org.champqcsoft.apigateway.presentationlayer.customers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestModel {
    private String clientId;

    private String nameClient;
    private String emailClient;
    private String phoneClient;
    private String streetClient;
    private String cityClient;
    private String stateClient;
    private String postalCodeClient;
    private String countryClient;
    private double totalSpentClient;
    private int numberOfPointsClient;
    private String membershipStatusClient;
    private double valueClient;
    private String currencyClient;
}
