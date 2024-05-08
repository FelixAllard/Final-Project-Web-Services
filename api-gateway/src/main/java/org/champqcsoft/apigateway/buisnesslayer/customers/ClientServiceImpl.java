package org.champqcsoft.apigateway.buisnesslayer.customers;

import jakarta.transaction.Transactional;
import org.champqcsoft.apigateway.commons.enums.Currency;
import org.champqcsoft.apigateway.commons.enums.Price;
import org.champqcsoft.apigateway.commons.identifiers.ClientIdentifier;
import org.champqcsoft.apigateway.domainclientlayer.customers.CustomerServiceClient;
import org.champqcsoft.apigateway.mapperlayer.customers.ClientResponseMapper;
import org.champqcsoft.apigateway.presentationlayer.customers.ClientResponseModel;
import org.champqcsoft.apigateway.presentationlayer.customers.ClientRequestModel;
import org.champqcsoft.apigateway.presentationlayer.customers.ClientResponseModel;
import org.champqcsoft.apigateway.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private final CustomerServiceClient customerServiceClient;
    private final ClientResponseMapper clientResponseMapper;


    public ClientServiceImpl(CustomerServiceClient customerServiceClient, ClientResponseMapper clientResponseMapper) {
        this.customerServiceClient = customerServiceClient;
        this.clientResponseMapper = clientResponseMapper;
    }

    @Override
    public List<ClientResponseModel> getAllClients() {

        return clientResponseMapper.entityListToResponseModelList(customerServiceClient.getAllClients());
    }

    @Override
    public ClientResponseModel getClientByClientIdentifier_clientId(String clientId) {
        return clientResponseMapper.entityToResponseModel(customerServiceClient.findClientByClientIdentifier_ClientId(clientId));

    }

    @Override
    public ClientResponseModel createClient(ClientRequestModel clientRequestModel) {
        return clientResponseMapper.entityToResponseModel(customerServiceClient.createCustomer(clientRequestModel));
    }

    @Override
    public ClientResponseModel updateClient(ClientRequestModel clientRequestModel, String clientId) {
        return clientResponseMapper.entityToResponseModel(customerServiceClient.updateClientByClient_Id(clientRequestModel, clientId));
    }

    @Override
    public void removeClient(String clientId) {
        customerServiceClient.deleteClientByClient_Id(clientId);
    }
}