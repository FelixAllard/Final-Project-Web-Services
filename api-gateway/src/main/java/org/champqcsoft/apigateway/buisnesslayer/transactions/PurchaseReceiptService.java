package org.champqcsoft.apigateway.buisnesslayer.transactions;



import org.champqcsoft.apigateway.presentationlayer.transactions. PurchaseReceiptRequestModel;
import org.champqcsoft.apigateway.presentationlayer.transactions. PurchaseReceiptResponseModel;

import java.util.List;

public interface PurchaseReceiptService {
    List<PurchaseReceiptResponseModel> getAllPurchaseReceipts();

    PurchaseReceiptResponseModel getPurchaseReceiptByPurchaseReceiptIdentifier_purchaseReceiptId(String purchaseReceiptId);

    PurchaseReceiptResponseModel createPurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel);

    PurchaseReceiptResponseModel updatePurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel, String purchaseReceiptId);

    void removePurchaseReceipt(String purchaseReceiptId);
}
