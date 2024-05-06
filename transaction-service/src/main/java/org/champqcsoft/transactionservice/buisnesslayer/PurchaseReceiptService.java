package org.champqcsoft.transactionservice.buisnesslayer;



import org.champqcsoft.transactionservice.presentationlayer.PurchaseReceiptRequestModel;
import org.champqcsoft.transactionservice.presentationlayer.PurchaseReceiptResponseModel;

import java.util.List;

public interface PurchaseReceiptService {
    List<PurchaseReceiptResponseModel> getAllPurchaseReceipts();

    PurchaseReceiptResponseModel getPurchaseReceiptByPurchaseReceiptIdentifier_purchaseReceiptId(String purchaseReceiptId);

    PurchaseReceiptResponseModel createPurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel);

    PurchaseReceiptResponseModel updatePurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel, String purchaseReceiptId);

    void removePurchaseReceipt(String purchaseReceiptId);
}
