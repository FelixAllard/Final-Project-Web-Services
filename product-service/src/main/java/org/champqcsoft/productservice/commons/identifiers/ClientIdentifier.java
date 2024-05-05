package org.champqcsoft.productservice.commons.identifiers;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class ClientIdentifier {
    private String clientId;
    //Set the clientId to a UUID
    public ClientIdentifier() { this.clientId = UUID.randomUUID().toString(); }
    public ClientIdentifier(String clientId) { this.clientId = clientId; }
}
