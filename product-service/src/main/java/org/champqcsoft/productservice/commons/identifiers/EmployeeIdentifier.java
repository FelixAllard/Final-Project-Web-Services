package org.champqcsoft.productservice.commons.identifiers;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class EmployeeIdentifier {
    private String employeeId;
    //Set the clientId to a UUID
    public EmployeeIdentifier() { this.employeeId = UUID.randomUUID().toString(); }
    public EmployeeIdentifier(String employeeId) { this.employeeId = employeeId; }
}
