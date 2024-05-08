package org.champqcsoft.apigateway.domainclientlayer.employees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
    private String employeeId;

    private String name;
    private int age;
    private String currentEmploymentStatus;
    private Boolean fullDay;
    private int startDay;
    private int startMonth;
    private int startYear;
    private int endDay;
    private int endMonth;
    private int endYear;
    private String reason;
    private String daysOfTheWeek;
    private double valueEmployee;
    private String currencyEmployee;
    private String paymentMethod;
}
