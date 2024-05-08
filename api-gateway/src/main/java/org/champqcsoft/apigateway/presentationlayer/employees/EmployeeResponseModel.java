package org.champqcsoft.apigateway.presentationlayer.employees;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseModel extends RepresentationModel<EmployeeResponseModel> {
    private String employeeId;

    private String nameEmployee;
    private int ageEmployee;
    private String currentEmploymentStatusEmployee;
    private Boolean fullDayEmployee;
    private int startDayEmployee;
    private int startMonthEmployee;
    private int startYearEmployee;
    private int endDayEmployee;
    private int endMonthEmployee;
    private int endYearEmployee;
    private String reasonEmployee;
    private String daysOfTheWeekEmployee;
    private double valueEmployee;
    private String currencyEmployee;
    private String paymentMethodEmployee;

}
