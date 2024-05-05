package org.champqcsoft.employeeservice.presentationlayer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestModel {
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
    private double value;
    private String currency;
    private String paymentMethod;
}
