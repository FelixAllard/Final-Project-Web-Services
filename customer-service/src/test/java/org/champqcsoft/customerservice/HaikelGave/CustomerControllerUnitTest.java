package org.champqcsoft.customerservice.HaikelGave;
import org.champqcsoft.customerservice.buisnesslayer.ClientService;
import org.champqcsoft.customerservice.presentationlayer.ClientController;
import org.champqcsoft.customerservice.presentationlayer.ClientResponseModel;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@SpringBootTest(classes = ClientController.class)
public class CustomerControllerUnitTest {
    ClientService clientService;
    ClientController clientController;
    @Test
    public void whenNoClientExist_ThenReturnEmptyList(){
        // arrange step
        when(clientService.getAllClients()).thenReturn(Collections.emptyList());
        //act Step
        ResponseEntity<List<ClientResponseModel>> customerResponseEntity =
                clientController.getClients();
        assertNotNull(customerResponseEntity);
        assertEquals(customerResponseEntity.getStatusCode(), HttpStatus.OK);
    }
}
