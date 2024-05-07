package org.champqcsoft.apigateway.subdomains.employeesmanagementsubdomains.buisnesslayer;


import org.champqcsoft.apigateway.subdomains.employeesmanagementsubdomains.presentationlayer.EmployeeRequestModel;
import org.champqcsoft.apigateway.subdomains.employeesmanagementsubdomains.presentationlayer.EmployeeResponseModel;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseModel> getAllEmployees();

    EmployeeResponseModel getEmployeeByEmployeeIdentifier_employeeId(String employeeId);

    EmployeeResponseModel createEmployee(EmployeeRequestModel employeeRequestModel);

    EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId);

    void removeEmployee(String employeeId);
}
