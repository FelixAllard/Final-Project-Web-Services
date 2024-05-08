package org.champqcsoft.apigateway.mapperlayer.customers;


import org.champqcsoft.apigateway.presentationlayer.customers.ClientController;
import org.champqcsoft.apigateway.presentationlayer.customers.ClientResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface ClientResponseMapper {

    ClientResponseModel entityToResponseModel(ClientResponseModel client);

    List<ClientResponseModel> entityListToResponseModelList(List<ClientResponseModel> clients);

    @AfterMapping
    default void addLinks(@MappingTarget ClientResponseModel model){
        Link selfLink = linkTo(methodOn(ClientController.class)
                .getClientByClientId(model.getClientId()))
                .withSelfRel();
        model.add(selfLink);

        Link clientsLink =
                linkTo(methodOn(ClientController.class)
                        .getClients())
                        .withRel("all clients");
        model.add(clientsLink);
    }
}
