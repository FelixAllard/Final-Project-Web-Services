package org.champqcsoft.apigateway.subdomains.employeesmanagementsubdomains.dataaccesslayer;


import org.champqcsoft.apigateway.commons.enums.DaysOfTheWeek;
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
public class DaysNonAvailable {

    private Boolean fullDay;
    @Embedded
    private Date NonAvailability;
    private String reason;
    @Enumerated(EnumType.STRING)
    private DaysOfTheWeek daysOfTheWeek;

}
