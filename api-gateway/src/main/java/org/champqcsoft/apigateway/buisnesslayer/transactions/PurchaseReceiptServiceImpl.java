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


    private final CustomerServiceClient customerServiceClient;
    private final EmployeeServiceClient employeeServiceClient;
    private final ProductServiceClient productServiceClient;

    public PurchaseReceiptServiceImpl(TransactionServiceClient transactionServiceClient,
                                      CustomerServiceClient customerServiceClient,
                                        EmployeeServiceClient employeeServiceClient,
                                        ProductServiceClient productServiceClient,
                                      PurchaseReceiptResponseMapper orderResponseMapper) {
        this.transactionServiceClient = transactionServiceClient;
        this.customerServiceClient = customerServiceClient;
        this.employeeServiceClient = employeeServiceClient;
        this.productServiceClient = productServiceClient;

        this.transactionResponseMapper = orderResponseMapper;
    }



    @Override
    public List<PurchaseReceiptResponseModel> getAllPurchaseReceipts() {
        List<PurchaseReceiptResponseModel> purchaseReceiptResponseModels = new ArrayList<>();
        List<PurchaseReceiptResponseModel> purchaseReceipts = transactionServiceClient.getAllTransactions();

        purchaseReceipts.forEach(purchaseReceipt -> {
            purchaseReceiptResponseModels.add(transactionResponseMapper.entityToResponseModel(purchaseReceipt,
                            customerServiceClient.findClientByClientIdentifier_ClientId(purchaseReceipt.getClientIdTransaction()),
                            employeeServiceClient.findEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceipt.getEmployeeIdTransaction()),
                            productServiceClient.findProductByProductIdentifier_ProductId(purchaseReceipt.getProductIdTransaction())
                    )
            );
        });
        return purchaseReceiptResponseModels;
    }

    @Override
    public PurchaseReceiptResponseModel getPurchaseReceiptByPurchaseReceiptIdentifier_purchaseReceiptId(String purchaseReceiptId) {
        PurchaseReceiptResponseModel purchaseReceiptResponseModel = transactionServiceClient.getTransactionByTransactionId(purchaseReceiptId);


        return transactionResponseMapper.entityToResponseModel(purchaseReceiptResponseModel,
                customerServiceClient.findClientByClientIdentifier_ClientId(purchaseReceiptResponseModel.getClientIdTransaction()),
                employeeServiceClient.findEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceiptResponseModel.getEmployeeIdTransaction()),
                productServiceClient.findProductByProductIdentifier_ProductId(purchaseReceiptResponseModel.getProductIdTransaction())
        );
    }

    @Override
    public PurchaseReceiptResponseModel createPurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel) {
        PurchaseReceiptResponseModel purchaseReceiptResponseModel = transactionServiceClient.createTransaction(purchaseReceiptRequestModel);



        return transactionResponseMapper.entityToResponseModel(
                purchaseReceiptResponseModel,
                customerServiceClient.findClientByClientIdentifier_ClientId(purchaseReceiptRequestModel.getClientIdTransaction()),
                employeeServiceClient.findEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceiptRequestModel.getEmployeeIdTransaction()),
                productServiceClient.findProductByProductIdentifier_ProductId(purchaseReceiptRequestModel.getProductIdTransaction())
        );
    }

    @Override
    public PurchaseReceiptResponseModel updatePurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel, String purchaseReceiptId) {
        PurchaseReceiptResponseModel purchaseReceiptResponseModel = transactionServiceClient.updateTransactionByTransaction_Id(
                purchaseReceiptRequestModel,
                purchaseReceiptId
        );
        return transactionResponseMapper.entityToResponseModel(
                purchaseReceiptResponseModel,
                customerServiceClient.findClientByClientIdentifier_ClientId(purchaseReceiptRequestModel.getClientIdTransaction()),
                employeeServiceClient.findEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceiptRequestModel.getEmployeeIdTransaction()),
                productServiceClient.findProductByProductIdentifier_ProductId(purchaseReceiptRequestModel.getProductIdTransaction())
        );
    }

    @Override
    public void removePurchaseReceipt(String purchaseReceiptId) {
        transactionServiceClient.deleteTransactionByTransaction_Id(purchaseReceiptId);

    }
}
