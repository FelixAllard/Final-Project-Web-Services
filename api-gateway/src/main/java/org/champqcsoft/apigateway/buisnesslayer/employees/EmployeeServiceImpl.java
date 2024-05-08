package org.champqcsoft.apigateway.buisnesslayer.employees;


import jakarta.transaction.Transactional;
import org.champqcsoft.apigateway.commons.enums.Currency;
import org.champqcsoft.apigateway.commons.enums.DaysOfTheWeek;
import org.champqcsoft.apigateway.commons.enums.PaymentMethod;
import org.champqcsoft.apigateway.commons.enums.Price;
import org.champqcsoft.apigateway.commons.identifiers.EmployeeIdentifier;
import org.champqcsoft.apigateway.domainclientlayer.employees.EmployeeServiceClient;
import org.champqcsoft.apigateway.mapperlayer.employees.EmployeeResponseMapper;
import org.champqcsoft.apigateway.presentationlayer.employees.EmployeeRequestModel;
import org.champqcsoft.apigateway.presentationlayer.employees.EmployeeResponseModel;
import org.champqcsoft.apigateway.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeServiceClient employeeServiceEmployee;
    private final EmployeeResponseMapper employeeResponseMapper;
    public EmployeeServiceImpl(EmployeeServiceClient employeeServiceEmployee, EmployeeResponseMapper employeeResponseMapper){
        this.employeeServiceEmployee = employeeServiceEmployee;
        this.employeeResponseMapper = employeeResponseMapper;
    }

    @Override
    public List<EmployeeResponseModel> getAllEmployees() {

        return employeeResponseMapper.entityListToResponseModelList(employeeServiceEmployee.getAllEmployee());
    }

    @Override
    public EmployeeResponseModel getEmployeeByEmployeeIdentifier_employeeId(String employeeId) {
        return employeeResponseMapper.entityToResponseModel(employeeServiceEmployee.findEmployeeByEmployeeIdentifier_EmployeeId(employeeId));

    }

    @Override
    public EmployeeResponseModel createEmployee(EmployeeRequestModel employeeRequestModel) {
        return employeeResponseMapper.entityToResponseModel(employeeServiceEmployee.createEmployee(employeeRequestModel));
    }

    @Override
    public EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId) {
        return employeeResponseMapper.entityToResponseModel(employeeServiceEmployee.updateEmployeeByEmployee_Id(employeeRequestModel, employeeId));
    }

    @Override
    public void removeEmployee(String employeeId) {
        employeeServiceEmployee.deleteEmployeeByEmployee_Id(employeeId);
    }
}
