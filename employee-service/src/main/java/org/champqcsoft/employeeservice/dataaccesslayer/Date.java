package org.champqcsoft.employeeservice.dataaccesslayer;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Date {

    private int startDay;

    private int startMonth;

    private int startYear;
    private int endDay;

    private int endMonth;

    private int endYear;
}
