package org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.datamapperlayer;


import org.champqcsoft.apigateway.commons.identifiers.ClientIdentifier;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.dataaccesslayer.Address;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.dataaccesslayer.Client;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.dataaccesslayer.Contact;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.dataaccesslayer.Membership;
import org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.presentationlayer.ClientRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientRequestMapper {
    @Mapping(target = "id", ignore = true)
    Client requestModelToEntity(ClientRequestModel clientRequestModel,
                                ClientIdentifier clientIdentifier,
                                Contact contact,
                                Address address,
                                Membership membership

    );
}
