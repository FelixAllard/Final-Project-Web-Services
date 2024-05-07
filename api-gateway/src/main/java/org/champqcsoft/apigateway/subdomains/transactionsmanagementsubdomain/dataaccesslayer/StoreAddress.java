package org.champqcsoft.apigateway.subdomains.transactionsmanagementsubdomain.dataaccesslayer;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoreAddress {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
