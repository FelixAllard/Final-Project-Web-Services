package org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.buisnesslayer;

import org.champqcsoft.apigateway.commons.enums.Currency;
import org.champqcsoft.apigateway.commons.enums.Price;
import org.champqcsoft.apigateway.commons.identifiers.ClientIdentifier;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.dataaccesslayer.*;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.datamapperlayer.ClientRequestMapper;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.datamapperlayer.ClientResponseMapper;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.presentationlayer.ClientRequestModel;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.presentationlayer.ClientResponseModel;
import org.champqcsoft.apigateway.utils.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final ClientResponseMapper clientResponseMapper;
    private final ClientRequestMapper clientRequestMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientResponseMapper clientResponseMapper, ClientRequestMapper clientRequestMapper) {
        this.clientRepository = clientRepository;
        this.clientResponseMapper = clientResponseMapper;
        this.clientRequestMapper = clientRequestMapper;
    }

    @Override
    public List<ClientResponseModel> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clientResponseMapper.entityListToResponseModelList(clients);
    }

    @Override
    public ClientResponseModel getClientByClientIdentifier_clientId(String clientId) {
        if(!clientRepository.existsClientByClientIdentifier_ClientId(clientId))
            throw new NotFoundException("Unknown clientId " + clientId);
        Client clients = clientRepository.findClientByClientIdentifier_ClientId(clientId);
        return clientResponseMapper.entityToResponseModel(clients);

    }

    @Override
    public ClientResponseModel createClient(ClientRequestModel clientRequestModel) {
        //We don't depend on other stuff
        Price price = new Price(
                clientRequestModel.getValue(),
                Currency.valueOf(clientRequestModel.getCurrency())
        );
        Contact contact = new Contact(clientRequestModel.getEmail(),
                clientRequestModel.getPhone()
        );
        Address address = new Address(
                clientRequestModel.getStreet(),
                clientRequestModel.getCity(),
                clientRequestModel.getState(),
                clientRequestModel.getPostalCode(),
                clientRequestModel.getCountry()
                );
        Membership membership = new Membership(
                clientRequestModel.getTotalSpent(),
                clientRequestModel.getNumberOfPoints(),
                MembershipStatus.valueOf(clientRequestModel.getMembershipStatus()),
                price
        );
        Client client = clientRequestMapper.requestModelToEntity(
                clientRequestModel,
                new ClientIdentifier(),
                contact,
                address,
                membership
        );
        return clientResponseMapper.entityToResponseModel(clientRepository.save(client));
    }

    @Override
    public ClientResponseModel updateClient(ClientRequestModel clientRequestModel, String clientId) {
        if(!clientRepository.existsClientByClientIdentifier_ClientId(clientId))
            throw new NotFoundException("Unknown clientId " + clientId);


        Client client = clientRepository.findClientByClientIdentifier_ClientId(clientId);
        Price price = new Price(
                clientRequestModel.getValue(),
                Currency.valueOf(clientRequestModel.getCurrency())
        );
        Contact contact = new Contact(clientRequestModel.getEmail(),
                clientRequestModel.getPhone()
        );
        Address address = new Address(
                clientRequestModel.getStreet(),
                clientRequestModel.getCity(),
                clientRequestModel.getState(),
                clientRequestModel.getPostalCode(),
                clientRequestModel.getCountry()
        );
        Membership membership = new Membership(
                clientRequestModel.getTotalSpent(),
                clientRequestModel.getNumberOfPoints(),
                MembershipStatus.valueOf(clientRequestModel.getMembershipStatus()),
                price
        );
        Client updatedClient = clientRequestMapper.requestModelToEntity(
                clientRequestModel,
                client.getClientIdentifier(),
                contact,
                address,
                membership
        );
        updatedClient.setId(client.getId());

        return clientResponseMapper.entityToResponseModel(clientRepository.save(updatedClient));
    }

    @Transactional
    @Override
    public void removeClient(String clientId) {
        if(!clientRepository.existsClientByClientIdentifier_ClientId(clientId))
            throw new NotFoundException("Unknown clientId " + clientId);
        clientRepository.deleteClientByClientIdentifier_ClientId(clientId);
    }
}