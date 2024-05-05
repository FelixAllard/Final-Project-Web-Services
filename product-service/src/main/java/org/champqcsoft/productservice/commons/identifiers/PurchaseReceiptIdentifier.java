package org.champqcsoft.productservice.commons.identifiers;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class PurchaseReceiptIdentifier {
    private String purchaseReceiptId;
    public PurchaseReceiptIdentifier() { this.purchaseReceiptId = UUID.randomUUID().toString(); }
    public PurchaseReceiptIdentifier(String purchaseReceiptId) { this.purchaseReceiptId = purchaseReceiptId; }
}
