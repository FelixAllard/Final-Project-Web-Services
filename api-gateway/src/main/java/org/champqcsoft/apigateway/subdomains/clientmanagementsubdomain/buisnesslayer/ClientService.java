package org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.buisnesslayer;


import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.presentationlayer.ClientRequestModel;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.presentationlayer.ClientResponseModel;

import java.util.List;

public interface ClientService {
    List<ClientResponseModel> getAllClients();

    ClientResponseModel getClientByClientIdentifier_clientId(String clientId);

    ClientResponseModel createClient(ClientRequestModel clientRequestModel);

    ClientResponseModel updateClient(ClientRequestModel clientRequestModel, String clientId);

    void removeClient(String clientId);

}
