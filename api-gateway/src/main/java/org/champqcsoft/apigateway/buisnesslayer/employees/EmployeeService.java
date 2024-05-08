package org.champqcsoft.apigateway.buisnesslayer.employees;


import org.champqcsoft.apigateway.presentationlayer.employees.EmployeeRequestModel;
import org.champqcsoft.apigateway.presentationlayer.employees.EmployeeResponseModel;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseModel> getAllEmployees();

    EmployeeResponseModel getEmployeeByEmployeeIdentifier_employeeId(String employeeId);

    EmployeeResponseModel createEmployee(EmployeeRequestModel employeeRequestModel);

    EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId);

    void removeEmployee(String employeeId);
}
