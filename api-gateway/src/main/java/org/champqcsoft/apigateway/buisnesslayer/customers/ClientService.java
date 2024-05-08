package org.champqcsoft.apigateway.buisnesslayer.customers;



import org.champqcsoft.apigateway.presentationlayer.customers.ClientRequestModel;
import org.champqcsoft.apigateway.presentationlayer.customers.ClientResponseModel;

import java.util.List;

public interface ClientService {
    List<ClientResponseModel> getAllClients();

    ClientResponseModel getClientByClientIdentifier_clientId(String clientId);

    ClientResponseModel createClient(ClientRequestModel clientRequestModel);

    ClientResponseModel updateClient(ClientRequestModel clientRequestModel, String clientId);

    void removeClient(String clientId);

}
