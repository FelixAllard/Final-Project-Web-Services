package org.champqcsoft.transactionservice.buisnesslayer;



import com.example.GroceryStoreApp.subdomains.transactionsmanagementsubdomain.presentationlayer.PurchaseReceiptRequestModel;
import com.example.GroceryStoreApp.subdomains.transactionsmanagementsubdomain.presentationlayer.PurchaseReceiptResponseModel;

import java.util.List;

public interface PurchaseReceiptService {
    List<PurchaseReceiptResponseModel> getAllPurchaseReceipts();

    PurchaseReceiptResponseModel getPurchaseReceiptByPurchaseReceiptIdentifier_purchaseReceiptId(String purchaseReceiptId);

    PurchaseReceiptResponseModel createPurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel);

    PurchaseReceiptResponseModel updatePurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel, String purchaseReceiptId);

    void removePurchaseReceipt(String purchaseReceiptId);
}
