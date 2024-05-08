package org.champqcsoft.apigateway.presentationlayer.customers;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseModel extends RepresentationModel<ClientResponseModel> {
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