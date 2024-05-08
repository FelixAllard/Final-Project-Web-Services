package org.champqcsoft.apigateway.buisnesslayer.transactions;

import org.champqcsoft.apigateway.domainclientlayer.customers.CustomerServiceClient;
import org.champqcsoft.apigateway.domainclientlayer.employees.EmployeeServiceClient;
import org.champqcsoft.apigateway.domainclientlayer.products.ProductServiceClient;
import org.champqcsoft.apigateway.domainclientlayer.transactions.TransactionServiceClient;

import org.champqcsoft.apigateway.mapperlayer.transactions.PurchaseReceiptResponseMapper;
import org.champqcsoft.apigateway.presentationlayer.transactions.PurchaseReceiptRequestModel;
import org.champqcsoft.apigateway.presentationlayer.transactions.PurchaseReceiptResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseReceiptServiceImpl implements PurchaseReceiptService {
    private final TransactionServiceClient transactionServiceClient;
    private final PurchaseReceiptResponseMapper transactionResponseMapper;


    public PurchaseReceiptServiceImpl(TransactionServiceClient transactionServiceClient,
                                      PurchaseReceiptResponseMapper orderResponseMapper) {
        this.transactionServiceClient = transactionServiceClient;

        this.transactionResponseMapper = orderResponseMapper;
    }



    @Override
    public List<PurchaseReceiptResponseModel> getAllPurchaseReceipts() {
        List<PurchaseReceiptResponseModel> purchaseReceiptResponseModels = new ArrayList<>();
        List<PurchaseReceiptResponseModel> purchaseReceipts = transactionServiceClient.getAllTransactions();

        purchaseReceipts.forEach(purchaseReceipt -> {
            purchaseReceiptResponseModels.add(transactionResponseMapper.entityToResponseModel(purchaseReceipt
                    )
            );
        });
        return purchaseReceiptResponseModels;
    }

    @Override
    public PurchaseReceiptResponseModel getPurchaseReceiptByPurchaseReceiptIdentifier_purchaseReceiptId(String purchaseReceiptId) {
        PurchaseReceiptResponseModel purchaseReceiptResponseModel = transactionServiceClient.getTransactionByTransactionId(purchaseReceiptId);


        return transactionResponseMapper.entityToResponseModel(purchaseReceiptResponseModel
        );
    }

    @Override
    public PurchaseReceiptResponseModel createPurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel) {
        PurchaseReceiptResponseModel purchaseReceiptResponseModel = transactionServiceClient.createTransaction(purchaseReceiptRequestModel);



        return transactionResponseMapper.entityToResponseModel(
                purchaseReceiptResponseModel
        );
    }

    @Override
    public PurchaseReceiptResponseModel updatePurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel, String purchaseReceiptId) {
        PurchaseReceiptResponseModel purchaseReceiptResponseModel = transactionServiceClient.updateTransactionByTransaction_Id(
                purchaseReceiptRequestModel,
                purchaseReceiptId
        );
        return transactionResponseMapper.entityToResponseModel(
                purchaseReceiptResponseModel
        );
    }

    @Override
    public void removePurchaseReceipt(String purchaseReceiptId) {
        transactionServiceClient.deleteTransactionByTransaction_Id(purchaseReceiptId);

    }
}
