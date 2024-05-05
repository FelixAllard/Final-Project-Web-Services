package org.champqcsoft.customerservice.dataaccesslayer;

import org.champqcsoft.customerservice.commons.enums.Currency;
import org.champqcsoft.customerservice.commons.enums.Price;
import org.champqcsoft.customerservice.commons.identifiers.ClientIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CustomerRepositoryIntegrationTest {
    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUpDb() { clientRepository.deleteAll(); }

    @Test
    public void whenClientExists_ReturnClientByClientId(){
        //arrange
        Client client1;
        client1 = new Client(new Contact("xilef992@gmail.com","555555555") ,new ClientIdentifier(),
                new Address("6601 Louis-Hemon","Montreal","Quebec","h2g2l1","Canada"),
                new Membership(26.64,200,MembershipStatus.Active,new Price(64, Currency.CAD))
                );
        clientRepository.save(client1);

        //act
        Client client1Found = clientRepository.findClientByClientIdentifier_ClientId(client1.getClientIdentifier().getClientId());

        //assert
        assertNotNull(client1Found);
        assertEquals(client1Found.getContact(), client1.getContact());
        assertEquals(client1Found.getClientIdentifier(), client1.getClientIdentifier());
        assertEquals(client1Found.getAddress(), client1.getAddress());
        assertEquals(client1Found.getMembership(), client1.getMembership());

    }
}
