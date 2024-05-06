package org.champqcsoft.transactionservice.buisnesslayer;

import org.champqcsoft.transactionservice.commons.enums.Currency;
import org.champqcsoft.transactionservice.commons.enums.Price;
import org.champqcsoft.transactionservice.commons.identifiers.ClientIdentifier;
import org.champqcsoft.transactionservice.commons.identifiers.EmployeeIdentifier;
import org.champqcsoft.transactionservice.commons.identifiers.ProductIdentifier;
import org.champqcsoft.transactionservice.commons.identifiers.PurchaseReceiptIdentifier;
import com.example.GroceryStoreApp.subdomains.clientmanagementsubdomain.dataaccesslayer.ClientRepository;
import com.example.GroceryStoreApp.subdomains.employeesmanagementsubdomains.dataaccesslayer.EmployeeRepository;
import com.example.GroceryStoreApp.subdomains.productsmanagementsubdomain.dataaccesslayer.ProductRepository;
import org.champqcsoft.transactionservice.dataaccesslayer.*;
import org.champqcsoft.transactionservice.datamapperlayer.PurchaseReceiptRequestMapper;
import org.champqcsoft.transactionservice.datamapperlayer.PurchaseReceiptResponseMapper;
import org.champqcsoft.transactionservice.presentationlayer.PurchaseReceiptRequestModel;
import org.champqcsoft.transactionservice.presentationlayer.PurchaseReceiptResponseModel;
import org.champqcsoft.transactionservice.utils.exceptions.InvalidInputException;
import org.champqcsoft.transactionservice.utils.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseReceiptServiceImpl implements PurchaseReceiptService{
    private final PurchaseReceiptRepository purchaseReceiptRepository;
    //To access database of those 3 repository
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;


    private final PurchaseReceiptResponseMapper purchaseReceiptResponseMapper;
    private final PurchaseReceiptRequestMapper purchaseReceiptRequestMapper;

    public PurchaseReceiptServiceImpl(PurchaseReceiptRepository purchaseReceiptRepository, ClientRepository clientRepository, ProductRepository productRepository, EmployeeRepository employeeRepository,
                                      PurchaseReceiptResponseMapper purchaseReceiptResponseMapper,
                                      PurchaseReceiptRequestMapper purchaseReceiptRequestMapper) {
        this.purchaseReceiptRepository = purchaseReceiptRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.purchaseReceiptResponseMapper = purchaseReceiptResponseMapper;
        this.purchaseReceiptRequestMapper = purchaseReceiptRequestMapper;
    }

    @Override
    public List<PurchaseReceiptResponseModel> getAllPurchaseReceipts() {
        List<PurchaseReceiptResponseModel> purchaseReceiptResponseModels = new ArrayList<>();
        List<PurchaseReceipt> purchaseReceipts = purchaseReceiptRepository.findAll();

        purchaseReceipts.forEach(purchaseReceipt -> {
            if(!clientRepository.existsClientByClientIdentifier_ClientId(purchaseReceipt.getClientIdentifier().getClientId()))
                throw new InvalidInputException("Invalid input for clientId " + purchaseReceipt.getClientIdentifier().getClientId());
            if(!productRepository.existsProductByProductIdentifier_ProductId(purchaseReceipt.getProductIdentifier().getProductId()))
                throw new InvalidInputException("Invalid input for productId " + purchaseReceipt.getProductIdentifier().getProductId());
            if(!employeeRepository.existsEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceipt.getEmployeeIdentifier().getEmployeeId()))
                throw new InvalidInputException("Invalid input for employeeId " + purchaseReceipt.getEmployeeIdentifier().getEmployeeId());

            purchaseReceiptResponseModels.add(purchaseReceiptResponseMapper.entityToResponseModel(purchaseReceipt,
                    clientRepository.findClientByClientIdentifier_ClientId(purchaseReceipt.getClientIdentifier().getClientId()),
                    productRepository.findProductByProductIdentifier_ProductId(purchaseReceipt.getProductIdentifier().getProductId()),
                    employeeRepository.findEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceipt.getEmployeeIdentifier().getEmployeeId())));
        });

        return purchaseReceiptResponseModels;
    }

    @Override
    public PurchaseReceiptResponseModel getPurchaseReceiptByPurchaseReceiptIdentifier_purchaseReceiptId(String purchaseReceiptId) {
        if(!purchaseReceiptRepository.existsPurchaseReceiptByPurchaseReceiptIdentifier_PurchaseReceiptId(purchaseReceiptId))
            throw new NotFoundException("Unknown purchaseReceiptId " + purchaseReceiptId);
        PurchaseReceipt purchaseReceipt = purchaseReceiptRepository.findPurchaseReceiptByPurchaseReceiptIdentifier_PurchaseReceiptId(purchaseReceiptId);
        if(!clientRepository.existsClientByClientIdentifier_ClientId(purchaseReceipt.getClientIdentifier().getClientId()))
            throw new InvalidInputException("Invalid input for clientId " + purchaseReceipt.getClientIdentifier().getClientId());
        if(!productRepository.existsProductByProductIdentifier_ProductId(purchaseReceipt.getProductIdentifier().getProductId()))
            throw new InvalidInputException("Invalid input for productId " + purchaseReceipt.getProductIdentifier().getProductId());
        if(!employeeRepository.existsEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceipt.getEmployeeIdentifier().getEmployeeId()))
            throw new InvalidInputException("Invalid input for employeeId " + purchaseReceipt.getEmployeeIdentifier().getEmployeeId());

        return purchaseReceiptResponseMapper.entityToResponseModel(purchaseReceipt,
                clientRepository.findClientByClientIdentifier_ClientId(purchaseReceipt.getClientIdentifier().getClientId()),
                productRepository.findProductByProductIdentifier_ProductId(purchaseReceipt.getProductIdentifier().getProductId()),
                employeeRepository.findEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceipt.getEmployeeIdentifier().getEmployeeId()));


    }

    @Override
    public PurchaseReceiptResponseModel createPurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel) {
        //We don't depend on other stuff
        if(!clientRepository.existsClientByClientIdentifier_ClientId(purchaseReceiptRequestModel.getClientId()))
            throw new InvalidInputException("Invalid input for clientId " + purchaseReceiptRequestModel.getClientId());
        if(!productRepository.existsProductByProductIdentifier_ProductId(purchaseReceiptRequestModel.getProductId()))
            throw new InvalidInputException("Invalid input for productId " + purchaseReceiptRequestModel.getProductId());
        if(!employeeRepository.existsEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceiptRequestModel.getEmployeeId()))
            throw new InvalidInputException("Invalid input for employeeId " + purchaseReceiptRequestModel.getEmployeeId());
        CurrentDate currentDate = new CurrentDate(
                purchaseReceiptRequestModel.getTransactionHour(),
                new Date(
                        purchaseReceiptRequestModel.getDay(),
                        purchaseReceiptRequestModel.getMonth(),
                        purchaseReceiptRequestModel.getYear()
                )
        );
        Price price = new Price(
                purchaseReceiptRequestModel.getValue(),
                Currency.valueOf(purchaseReceiptRequestModel.getCurrency())

        );
        StoreAddress storeAddress = new StoreAddress(
                purchaseReceiptRequestModel.getStreet(),
                purchaseReceiptRequestModel.getCity(),
                purchaseReceiptRequestModel.getState(),
                purchaseReceiptRequestModel.getPostalCode(),
                purchaseReceiptRequestModel.getCountry()
        );
        PurchaseReceipt purchaseReceipt = purchaseReceiptRequestMapper.requestModelToEntity(
                purchaseReceiptRequestModel,
                new PurchaseReceiptIdentifier(),
                currentDate,
                price,
                storeAddress,
                new ClientIdentifier(
                        purchaseReceiptRequestModel.getClientId()
                ),
                new ProductIdentifier(
                        purchaseReceiptRequestModel.getProductId()
                ),
                new EmployeeIdentifier(
                        purchaseReceiptRequestModel.getEmployeeId()
                )
        );

        clientRepository.findClientByClientIdentifier_ClientId(purchaseReceipt.getClientIdentifier().getClientId()).getMembership().setNumberOfPoints(
                clientRepository.findClientByClientIdentifier_ClientId(purchaseReceipt.getClientIdentifier().getClientId()).getMembership().getNumberOfPoints()+1
        );

        return purchaseReceiptResponseMapper.entityToResponseModel(purchaseReceiptRepository.save(purchaseReceipt),
                clientRepository.findClientByClientIdentifier_ClientId(purchaseReceipt.getClientIdentifier().getClientId()),
                productRepository.findProductByProductIdentifier_ProductId(purchaseReceipt.getProductIdentifier().getProductId()),
                employeeRepository.findEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceipt.getEmployeeIdentifier().getEmployeeId()));
    }

    @Override
    public PurchaseReceiptResponseModel updatePurchaseReceipt(PurchaseReceiptRequestModel purchaseReceiptRequestModel, String purchaseReceiptId) {
        if(!purchaseReceiptRepository.existsPurchaseReceiptByPurchaseReceiptIdentifier_PurchaseReceiptId(purchaseReceiptId))
            throw new NotFoundException("Unknown purchaseReceiptId " + purchaseReceiptId);
        if(!clientRepository.existsClientByClientIdentifier_ClientId(purchaseReceiptRequestModel.getClientId()))
            throw new InvalidInputException("Invalid input for clientId " + purchaseReceiptRequestModel.getClientId());
        if(!productRepository.existsProductByProductIdentifier_ProductId(purchaseReceiptRequestModel.getProductId()))
            throw new InvalidInputException("Invalid input for productId " + purchaseReceiptRequestModel.getProductId());
        if(!employeeRepository.existsEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceiptRequestModel.getEmployeeId()))
            throw new InvalidInputException("Invalid input for employeeId " + purchaseReceiptRequestModel.getEmployeeId());


        PurchaseReceipt purchaseReceipt = purchaseReceiptRepository.findPurchaseReceiptByPurchaseReceiptIdentifier_PurchaseReceiptId(purchaseReceiptId);
        CurrentDate currentDate = new CurrentDate(
                purchaseReceiptRequestModel.getTransactionHour(),
                new Date(
                        purchaseReceiptRequestModel.getDay(),
                        purchaseReceiptRequestModel.getMonth(),
                        purchaseReceiptRequestModel.getYear()
                )
        );
        Price price = new Price(
                purchaseReceiptRequestModel.getValue(),
                Currency.valueOf(purchaseReceiptRequestModel.getCurrency())

        );
        StoreAddress storeAddress = new StoreAddress(
                purchaseReceiptRequestModel.getStreet(),
                purchaseReceiptRequestModel.getCity(),
                purchaseReceiptRequestModel.getState(),
                purchaseReceiptRequestModel.getPostalCode(),
                purchaseReceiptRequestModel.getCountry()
        );
        PurchaseReceipt updatedPurchaseReceipt = purchaseReceiptRequestMapper.requestModelToEntity(
                purchaseReceiptRequestModel,
                new PurchaseReceiptIdentifier(),
                currentDate,
                price,
                storeAddress,
                new ClientIdentifier(
                        purchaseReceiptRequestModel.getClientId()
                ),
                new ProductIdentifier(
                        purchaseReceiptRequestModel.getProductId()
                ),
                new EmployeeIdentifier(
                        purchaseReceiptRequestModel.getEmployeeId()
                )
        );
        updatedPurchaseReceipt.setId(purchaseReceipt.getId());
        return purchaseReceiptResponseMapper.entityToResponseModel(purchaseReceiptRepository.save(updatedPurchaseReceipt),
                clientRepository.findClientByClientIdentifier_ClientId(purchaseReceipt.getClientIdentifier().getClientId()),
                productRepository.findProductByProductIdentifier_ProductId(purchaseReceipt.getPurchaseReceiptIdentifier().getPurchaseReceiptId()),
                employeeRepository.findEmployeeByEmployeeIdentifier_EmployeeId(purchaseReceipt.getPurchaseReceiptIdentifier().getPurchaseReceiptId()));
    }

    @Transactional
    @Override
    public void removePurchaseReceipt(String purchaseReceiptId) {
        if(!purchaseReceiptRepository.existsPurchaseReceiptByPurchaseReceiptIdentifier_PurchaseReceiptId(purchaseReceiptId))
            throw new NotFoundException("Unknown purchaseReceiptId " + purchaseReceiptId);
        purchaseReceiptRepository.deletePurchaseReceiptByPurchaseReceiptIdentifier_PurchaseReceiptId(purchaseReceiptId);
    }
}
