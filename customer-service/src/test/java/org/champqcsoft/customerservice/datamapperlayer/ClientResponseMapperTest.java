package org.champqcsoft.customerservice.datamapperlayer;
import org.champqcsoft.customerservice.commons.enums.Currency;
import org.champqcsoft.customerservice.commons.enums.Price;
import org.champqcsoft.customerservice.commons.identifiers.ClientIdentifier;
import org.champqcsoft.customerservice.dataaccesslayer.*;
import org.champqcsoft.customerservice.presentationlayer.ClientResponseModel;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClientResponseMapperTest {

    private final ClientResponseMapper mapper = Mappers.getMapper(ClientResponseMapper.class);

    @Test
    public void testEntityToResponseModel() {
        Client client = createClient(); // Create a sample Client object
        ClientResponseModel responseModel = mapper.entityToResponseModel(client);

        // Assert the mapping is correct
        assertEquals(client.getClientIdentifier().getClientId(), responseModel.getClientId());
        assertEquals(client.getName(), responseModel.getName());
        assertEquals(client.getContact().getEmail(), responseModel.getEmail());
        // Add more assertions for other properties

        // Assert links are added
        assertNotNull(responseModel.getLinks());
        responseModel.getLinks().hasSize(2); // Check if two links are added
    }

    @Test
    public void testEntityListToResponseModelList() {
        List<Client> clients = Arrays.asList(
                new Client(new Contact("xilef992@gmail.com", "555555555"), new ClientIdentifier(),
                        new Address("6601 Louis-Hemon", "Montreal", "Quebec", "h2g2l1", "Canada"),
                        new Membership(26.64, 200, MembershipStatus.Active, new Price(64, Currency.CAD))),
                new Client(new Contact("xilef992@gmail.com", "555555555"), new ClientIdentifier(),
                        new Address("6601 Louis-Hemon", "Montreal", "Quebec", "h2g2l1", "Canada"),
                        new Membership(26.64, 200, MembershipStatus.Active, new Price(64, Currency.CAD)))
        );

        List<ClientResponseModel> responseModels = mapper.entityListToResponseModelList(clients);

        // Assert that the number of response models matches the number of clients
        assertEquals(clients.size(), responseModels.size());

        // Assert each mapping and link addition in the response models
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            ClientResponseModel responseModel = responseModels.get(i);

            assertEquals(client.getClientIdentifier().getClientId(), responseModel.getClientId());
            assertEquals(client.getName(), responseModel.getName());
            assertEquals(client.getContact().getEmail(), responseModel.getEmail());
            // Add more assertions for other properties

            assertNotNull(responseModel.getLinks());
            responseModel.getLinks().hasSize(2); // Check if two links are added for each model
        }
    }

    // Helper method to create a sample Client object
    private Client createClient() {
        return new Client(new Contact("xilef992@gmail.com", "555555555"), new ClientIdentifier(),
                new Address("6601 Louis-Hemon", "Montreal", "Quebec", "h2g2l1", "Canada"),
                new Membership(26.64, 200, MembershipStatus.Active, new Price(64, Currency.CAD)));
    }
}

