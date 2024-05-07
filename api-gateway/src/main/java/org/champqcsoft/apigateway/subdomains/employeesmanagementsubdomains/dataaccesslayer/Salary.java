package org.champqcsoft.apigateway.subdomains.employeesmanagementsubdomains.dataaccesslayer;


import org.champqcsoft.apigateway.commons.enums.PaymentMethod;
import org.champqcsoft.apigateway.commons.enums.Price;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Salary {
    @Embedded
    private Price SalaryPrice;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;


}
