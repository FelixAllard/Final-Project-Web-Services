package org.champqcsoft.apigateway.subdomains.clientmanagementsubdomain.dataaccesslayer;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contact {
    private String email;
    private String phone;
}
