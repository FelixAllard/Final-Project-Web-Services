package org.champqcsoft.employeeservice.commons.identifiers;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class ProductIdentifier {
    private String productId;
    //Set the productId to a UUID
    public ProductIdentifier() { this.productId = UUID.randomUUID().toString(); }
    public ProductIdentifier(String productId) { this.productId = productId; }
}
