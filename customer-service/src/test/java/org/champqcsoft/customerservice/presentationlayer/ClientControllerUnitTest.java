package org.champqcsoft.customerservice.presentationlayer;

import org.champqcsoft.customerservice.buisnesslayer.ClientService;
import org.champqcsoft.customerservice.commons.enums.Currency;
import org.champqcsoft.customerservice.dataaccesslayer.MembershipStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ClientController.class)
class ClientControllerUnitTest {

    private final String FOUND_CLIENT_ID = "c3333333-3333-3333-333333333333";
    private final String NOT_FOUND_CLIENT_ID = "c3333333-3333-3333-444444444444";
    private final String INVALID_FOUND_CLIENT_ID = "c3333333-3333-3333";

    @Autowired
    ClientController clientController;

    @MockBean
    private ClientService clientService;

    @Test
    public void whenNoClientExists_thenReturnEmptyList(){
        //arrange
        when(clientService.getAllClients()).thenReturn(Collections.emptyList());

        //act
        ResponseEntity<List<ClientResponseModel>> responseEntity = clientController.getClients();

        //assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().isEmpty());

        verify(clientService, times(1)).getAllClients();
    }

    @Test
    public void whenClientExists_thenReturnClient(){
        //arrange
        ClientRequestModel clientRequestModel = buildClientRequestModel();
        ClientResponseModel clientResponseModel = buildClientResponseModel();

        when(clientService.createClient(clientRequestModel)).thenReturn(clientResponseModel);

        //act
        ResponseEntity<ClientResponseModel> responseEntity = clientController.createClient(clientRequestModel);

        //assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(clientResponseModel, responseEntity.getBody());

        verify(clientService, times(1)).createClient(clientRequestModel);
    }

    private ClientRequestModel buildClientRequestModel(){
        return ClientRequestModel.builder()
                .clientId(FOUND_CLIENT_ID)
                .name("Xilef")
                .email("Xilef8281@gmail.com")
                .phone("434234234234")
                .street("Elm Street")
                .city("Pacter")
                .state("Ariz")
                .postalCode("12few")
                .country("Canada")
                .totalSpent(25.65)
                .numberOfPoints(200)
                .membershipStatus(MembershipStatus.Active.name())
                .value(342.34)
                .currency(Currency.CAD.name())
                .build();
    }

    private ClientResponseModel buildClientResponseModel(){
        return ClientResponseModel.builder()
                .clientId(FOUND_CLIENT_ID)
                .name("Xilef")
                .email("Xilef8281@gmail.com")
                .phone("434234234234")
                .street("Elm Street")
                .city("Pacter")
                .state("Ariz")
                .postalCode("12few")
                .country("Canada")
                .totalSpent(25.65)
                .numberOfPoints(200)
                .membershipStatus(MembershipStatus.Active.name())
                .value(342.34)
                .currency(Currency.CAD.name())
                .build();
    }
}