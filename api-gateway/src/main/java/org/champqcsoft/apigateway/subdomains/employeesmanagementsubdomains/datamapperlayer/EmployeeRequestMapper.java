package org.champqcsoft.apigateway.subdomains.employeesmanagementsubdomains.datamapperlayer;


import org.champqcsoft.apigateway.commons.identifiers.EmployeeIdentifier;
import org.champqcsoft.apigateway.subdomains.employeesmanagementsubdomains.dataaccesslayer.DaysNonAvailable;
import org.champqcsoft.apigateway.subdomains.employeesmanagementsubdomains.dataaccesslayer.Employee;
import org.champqcsoft.apigateway.subdomains.employeesmanagementsubdomains.dataaccesslayer.Salary;
import org.champqcsoft.apigateway.subdomains.employeesmanagementsubdomains.presentationlayer.EmployeeRequestModel;
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