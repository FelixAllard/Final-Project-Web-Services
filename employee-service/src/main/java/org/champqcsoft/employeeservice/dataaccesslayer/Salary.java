package org.champqcsoft.employeeservice.dataaccesslayer;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.champqcsoft.employeeservice.commons.enums.PaymentMethod;
import org.champqcsoft.employeeservice.commons.enums.Price;

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
