package org.champqcsoft.customerservice.presentationlayer;


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
