package org.champqcsoft.employeeservice.datamapperlayer;


import org.champqcsoft.employeeservice.commons.identifiers.EmployeeIdentifier;
import org.champqcsoft.employeeservice.dataaccesslayer.DaysNonAvailable;
import org.champqcsoft.employeeservice.dataaccesslayer.Employee;
import org.champqcsoft.employeeservice.dataaccesslayer.Salary;
import org.champqcsoft.employeeservice.presentationlayer.EmployeeRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {
    @Mapping(target = "id", ignore = true)
    Employee requestModelToEntity(EmployeeRequestModel employeeRequestModel,
                                  EmployeeIdentifier employeeIdentifier,
                                  DaysNonAvailable daysNonAvailable,
                                  Salary salary
    );
    //IF ERROR, ADD ENUMERATOR
}