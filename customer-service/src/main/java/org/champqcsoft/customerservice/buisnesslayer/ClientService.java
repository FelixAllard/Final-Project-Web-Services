package org.champqcsoft.customerservice.buisnesslayer;



import org.champqcsoft.customerservice.presentationlayer.ClientRequestModel;
import org.champqcsoft.customerservice.presentationlayer.ClientResponseModel;

import java.util.List;

public interface ClientService {
    List<ClientResponseModel> getAllClients();

    ClientResponseModel getClientByClientIdentifier_clientId(String clientId);

    ClientResponseModel createClient(ClientRequestModel clientRequestModel);

    ClientResponseModel updateClient(ClientRequestModel clientRequestModel, String clientId);

    void removeClient(String clientId);

}
