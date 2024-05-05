package org.champqcsoft.customerservice.presentationlayer;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
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