package org.champqcsoft.employeeservice.dataaccesslayer;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.champqcsoft.employeeservice.commons.enums.DaysOfTheWeek;

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
