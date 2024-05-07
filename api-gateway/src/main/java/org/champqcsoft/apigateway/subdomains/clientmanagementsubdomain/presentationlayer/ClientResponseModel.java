package org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseModel extends RepresentationModel<ClientResponseModel> {
    private String clientId;

    private String name;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private double totalSpent;
    private int numberOfPoints;
    private String membershipStatus;
    private double value;
    private String currency;

}