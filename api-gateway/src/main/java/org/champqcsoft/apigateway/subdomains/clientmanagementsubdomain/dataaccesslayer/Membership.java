package org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.dataaccesslayer;

import org.champqcsoft.apigateway.commons.enums.Price;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Membership {
    private double totalSpent;
    private int numberOfPoints;

    @Enumerated(EnumType.STRING)
    private MembershipStatus membershipStatus;

    @Embedded
    private Price price;
}
