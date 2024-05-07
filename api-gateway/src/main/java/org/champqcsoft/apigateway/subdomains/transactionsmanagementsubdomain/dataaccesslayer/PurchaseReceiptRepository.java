package org.champqcsoft.apigateway.subdomains.transactionsmanagementsubdomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseReceiptRepository extends JpaRepository<PurchaseReceipt, Integer> {
    PurchaseReceipt findPurchaseReceiptByPurchaseReceiptIdentifier_PurchaseReceiptId(String purchaseReceiptId);
    void deletePurchaseReceiptByPurchaseReceiptIdentifier_PurchaseReceiptId(String purchaseReceiptId);

    boolean existsPurchaseReceiptByPurchaseReceiptIdentifier_PurchaseReceiptId(String purchaseReceiptId);
}
