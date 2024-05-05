package org.champqcsoft.employeeservice.buisnesslayer;


import org.champqcsoft.employeeservice.presentationlayer.EmployeeRequestModel;
import org.champqcsoft.employeeservice.presentationlayer.EmployeeResponseModel;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseModel> getAllEmployees();

    EmployeeResponseModel getEmployeeByEmployeeIdentifier_employeeId(String employeeId);

    EmployeeResponseModel createEmployee(EmployeeRequestModel employeeRequestModel);

    EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId);

    void removeEmployee(String employeeId);
}
