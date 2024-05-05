package org.champqcsoft.employeeservice.dataaccesslayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.champqcsoft.employeeservice.commons.enums.CurrentEmploymentStatus;
import org.champqcsoft.employeeservice.commons.identifiers.EmployeeIdentifier;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int age;
    @Embedded
    private EmployeeIdentifier employeeIdentifier;

    @Enumerated(EnumType.STRING)
    private CurrentEmploymentStatus currentEmploymentStatus;
    @Embedded
    private DaysNonAvailable daysNonAvailable;
    @Embedded
    private Salary salary;

    public Employee(
            @NotNull CurrentEmploymentStatus currentEmploymentStatus,
            @NotNull DaysNonAvailable daysNonAvailable,
            @NotNull Salary salary
    ){
        this.currentEmploymentStatus = currentEmploymentStatus;
        this.daysNonAvailable = daysNonAvailable;
        this.salary = salary;
    }
}
