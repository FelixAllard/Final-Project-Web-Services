package org.champqcsoft.customerservice.datamapperlayer;


import org.champqcsoft.customerservice.commons.identifiers.ClientIdentifier;
import org.champqcsoft.customerservice.dataaccesslayer.Address;
import org.champqcsoft.customerservice.dataaccesslayer.Client;
import org.champqcsoft.customerservice.dataaccesslayer.Contact;
import org.champqcsoft.customerservice.dataaccesslayer.Membership;
import org.champqcsoft.customerservice.presentationlayer.ClientRequestModel;
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
