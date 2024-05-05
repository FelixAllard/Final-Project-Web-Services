package org.champqcsoft.customerservice.presentationlayer;

import org.champqcsoft.customerservice.buisnesslayer.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }
    @GetMapping()
    public ResponseEntity<List<ClientResponseModel>> getClients(){
        return ResponseEntity.ok().body(clientService.getAllClients());
    }
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseModel> getClientByClientId(@PathVariable String clientId){
        return ResponseEntity.ok().body(clientService.getClientByClientIdentifier_clientId(clientId));
    }
    @PostMapping
    public ResponseEntity<ClientResponseModel> createClient(@RequestBody ClientRequestModel clientRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientRequestModel));
    }
    @PutMapping("/{clientId}")
    public ResponseEntity<ClientResponseModel> updateClient(@RequestBody ClientRequestModel clientRequestModel, @PathVariable String clientId){
        return ResponseEntity.ok().body(clientService.updateClient(clientRequestModel, clientId));
    }
    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable String clientId){
        clientService.removeClient(clientId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
