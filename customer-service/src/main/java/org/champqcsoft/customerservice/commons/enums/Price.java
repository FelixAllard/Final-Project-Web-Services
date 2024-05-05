package org.champqcsoft.customerservice.commons.enums;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Price {
    @Column(name = "\"value\"")
    private double value;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
