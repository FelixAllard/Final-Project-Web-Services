package org.champqcsoft.customerservice.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByClientIdentifier_ClientId(String clientId);

    void deleteClientByClientIdentifier_ClientId(String clientId);

    boolean existsClientByClientIdentifier_ClientId(String clientId);
}
