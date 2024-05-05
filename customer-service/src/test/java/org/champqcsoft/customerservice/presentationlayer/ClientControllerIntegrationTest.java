package org.champqcsoft.customerservice.presentationlayer;

import org.champqcsoft.customerservice.commons.enums.Currency;
import org.champqcsoft.customerservice.dataaccesslayer.ClientRepository;
import org.champqcsoft.customerservice.dataaccesslayer.MembershipStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("h2")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ClientControllerIntegrationTest {

    private final String BASE_URI_CLIENTS = "api/v1/clients";
    private final String FOUND_CLIENT_ID = "a5aded7b-71a9-45bc-b218-1b8ef7dd006c";
    private final String FOUND_CLIENT_FIRST_NAME = "Felix";
    private final String NOT_FOUND_CLIENT_ID = "c3333333-3333-3333-444444444444";
    private final String INVALID_CLIENT_ID = "c3333333-3333-3333";

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void whenGetClients_thenReturnAllClients(){

        long sizeDB = clientRepository.count();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+ clientRepository.count());
        //act & assert
        webTestClient.get().uri(BASE_URI_CLIENTS)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ClientResponseModel.class)
                .value((list) -> {
                    assertNotNull(list);
                    assertTrue(list.size() == sizeDB);
                });
    }

    @Test
    public void whenGetClientDoesNotExist_thenReturnNotFound(){
        //act & assert
        webTestClient.get().uri(BASE_URI_CLIENTS + "/" + NOT_FOUND_CLIENT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown clientId " + NOT_FOUND_CLIENT_ID);
    }

    @Test
    public void whenValidClient_thenCreateClient(){
        //arrange
        long sizeDB = clientRepository.count();

        ClientRequestModel clientRequestModel = new ClientRequestModel(
                FOUND_CLIENT_ID,
                "Felix",
                "xilef992@gmail.com",
                "5555555555",
                "Louis Hemon",
                "Montreal",
                "Alberta",
                "12313",
                "Quebec",
                69.69,
                69,
                MembershipStatus.Active.name(),
                69.69,
                Currency.CAD.name()
        );

        webTestClient.post()
                .uri(BASE_URI_CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(clientRequestModel)
                .exchange()
                .expectStatus().isCreated()
                //.expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ClientResponseModel.class)
                .value((customerResponseModel) -> {
                    assertNotNull(customerResponseModel);
                    assertEquals(clientRequestModel.getName(), customerResponseModel.getName());
                    assertEquals(clientRequestModel.getEmail(), customerResponseModel.getEmail());
                    assertEquals(clientRequestModel.getPhone(), customerResponseModel.getPhone());
                    assertEquals(clientRequestModel.getStreet(), customerResponseModel.getStreet());
                    assertEquals(clientRequestModel.getCity(), customerResponseModel.getCity());
                    assertEquals(clientRequestModel.getState(), customerResponseModel.getState());
                    assertEquals(clientRequestModel.getPostalCode(), customerResponseModel.getPostalCode());
                    assertEquals(clientRequestModel.getCountry(), customerResponseModel.getCountry());
                    assertEquals(clientRequestModel.getTotalSpent(), customerResponseModel.getTotalSpent());
                    assertEquals(clientRequestModel.getNumberOfPoints(), customerResponseModel.getNumberOfPoints());
                    assertEquals(clientRequestModel.getMembershipStatus(), customerResponseModel.getMembershipStatus());
                    assertEquals(clientRequestModel.getValue(), customerResponseModel.getValue());
                    assertEquals(clientRequestModel.getCurrency(), customerResponseModel.getCurrency());
                });

        long sizeDBAfter = clientRepository.count();
        assertEquals(sizeDB + 1, sizeDBAfter);
    }

    @Test
    public void whenUpdateClient_thenReturnUpdatedClient() {
        // Arrange
        ClientRequestModel clientToUpdate = new ClientRequestModel(FOUND_CLIENT_ID,
                "Felix",
                "xilef992@gmail.com",
                "5555555555",
                "LouisHemon",
                "Montreal",
                "Alberta",
                "12313",
                "Quebec",
                69.69,
                69,
                MembershipStatus.Active.name(),
                69.69,
                Currency.CAD.name());


        // Act & Assert
        webTestClient.put()
                .uri(BASE_URI_CLIENTS + "/" + FOUND_CLIENT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(clientToUpdate)
                .exchange()
                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ClientResponseModel.class)
                .value((updatedClient) -> {
                    assertNotNull(updatedClient);
                    assertEquals(clientToUpdate.getName(), updatedClient.getName());
                    assertEquals(clientToUpdate.getEmail(), updatedClient.getEmail());
                    assertEquals(clientToUpdate.getPhone(), updatedClient.getPhone());
                    assertEquals(clientToUpdate.getStreet(), updatedClient.getStreet());
                    assertEquals(clientToUpdate.getCity(), updatedClient.getCity());
                    assertEquals(clientToUpdate.getState(), updatedClient.getState());
                    assertEquals(clientToUpdate.getPostalCode(), updatedClient.getPostalCode());
                    assertEquals(clientToUpdate.getCountry(), updatedClient.getCountry());
                    assertEquals(clientToUpdate.getTotalSpent(), updatedClient.getTotalSpent());
                    assertEquals(clientToUpdate.getNumberOfPoints(), updatedClient.getNumberOfPoints());
                    assertEquals(clientToUpdate.getMembershipStatus(), updatedClient.getMembershipStatus());
                    assertEquals(clientToUpdate.getValue(), updatedClient.getValue());
                    assertEquals(clientToUpdate.getCurrency(), updatedClient.getCurrency());
                });
    }

    @Test
    public void whenUpdateNonExistentClient_thenThrowNotFoundException() {
        // Arrange
        String nonExistentClientId = "nonExistentId";
        ClientRequestModel updatedClient = new ClientRequestModel(nonExistentClientId, "UpdateName",
                "UpdateEmail",
                "updatePhone",
                "upateStreet",
                "UpdateCity",
                "UpdateState",
                "UpdatePostalCode",
                "UpdateCountry",
                69.69,
                69,
                MembershipStatus.Closed.name(),
                69.69,
                Currency.CAD.name()
        );

        // Act & Assert
        webTestClient.put()
                .uri(BASE_URI_CLIENTS + "/" + nonExistentClientId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedClient)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown clientId " + nonExistentClientId);
    }

    @Test
    public void whenRemoveNonExistentClient_thenThrowNotFoundException() {
        // Arrange
        String nonExistentClientId = "nonExistentId";

        // Act & Assert
        webTestClient.delete().uri(BASE_URI_CLIENTS + "/" + nonExistentClientId)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown clientId " + nonExistentClientId);
    }


    @Test
    public void whenDeleteClient_thenDeleteClientSuccessfully() {
        // Act
        webTestClient.delete().uri(BASE_URI_CLIENTS + "/" + FOUND_CLIENT_ID)
                .exchange()
                .expectStatus()
                .isNoContent();

        //Assert
        assertFalse(clientRepository.existsClientByClientIdentifier_ClientId(FOUND_CLIENT_ID));
    }

    @Test
    public void whenGetClientById_thenReturnClient() {
        // Act & Assert
        webTestClient.get().uri(BASE_URI_CLIENTS + "/" + FOUND_CLIENT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ClientResponseModel.class)
                .value((client) -> {
                    assertNotNull(client);
                    assertEquals(FOUND_CLIENT_ID, client.getClientId());
                    assertEquals(FOUND_CLIENT_FIRST_NAME, client.getName());
                });
    }


}