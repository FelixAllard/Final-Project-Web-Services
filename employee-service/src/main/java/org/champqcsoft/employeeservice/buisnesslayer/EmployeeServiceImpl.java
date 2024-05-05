package org.champqcsoft.employeeservice.buisnesslayer;


import jakarta.transaction.Transactional;
import org.champqcsoft.employeeservice.commons.enums.Currency;
import org.champqcsoft.employeeservice.commons.enums.DaysOfTheWeek;
import org.champqcsoft.employeeservice.commons.enums.PaymentMethod;
import org.champqcsoft.employeeservice.commons.enums.Price;
import org.champqcsoft.employeeservice.commons.identifiers.EmployeeIdentifier;
import org.champqcsoft.employeeservice.dataaccesslayer.*;
import org.champqcsoft.employeeservice.datamapperlayer.EmployeeRequestMapper;
import org.champqcsoft.employeeservice.datamapperlayer.EmployeeResponseMapper;
import org.champqcsoft.employeeservice.presentationlayer.EmployeeRequestModel;
import org.champqcsoft.employeeservice.presentationlayer.EmployeeResponseModel;
import org.champqcsoft.employeeservice.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeResponseMapper employeeResponseMapper;
    private final EmployeeRequestMapper employeeRequestMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeResponseMapper employeeResponseMapper, EmployeeRequestMapper employeeRequestMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeResponseMapper = employeeResponseMapper;
        this.employeeRequestMapper = employeeRequestMapper;
    }

    @Override
    public List<EmployeeResponseModel> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeResponseMapper.entityListToResponseModelList(employees);
    }

    @Override
    public EmployeeResponseModel getEmployeeByEmployeeIdentifier_employeeId(String employeeId) {
        if(!employeeRepository.existsEmployeeByEmployeeIdentifier_EmployeeId(employeeId))
            throw new NotFoundException("Unknown employeeId " + employeeId);
        Employee employees = employeeRepository.findEmployeeByEmployeeIdentifier_EmployeeId(employeeId);
        return employeeResponseMapper.entityToResponseModel(employees);

    }

    @Override
    public EmployeeResponseModel createEmployee(EmployeeRequestModel employeeRequestModel) {
        //We don't depend on other stuff
        Date date = new Date(
                employeeRequestModel.getStartDay(),
                employeeRequestModel.getStartMonth(),
                employeeRequestModel.getStartYear(),
                employeeRequestModel.getEndDay(),
                employeeRequestModel.getEndMonth(),
                employeeRequestModel.getEndYear()
        );

        Price price = new Price(
                employeeRequestModel.getValue(),
                Currency.valueOf(employeeRequestModel.getCurrency())
        );
        Salary salary = new Salary(
                price,
                PaymentMethod.valueOf(employeeRequestModel.getPaymentMethod())
        );
        DaysNonAvailable daysNonAvailable = new DaysNonAvailable(
                employeeRequestModel.getFullDay(),
                date,
                employeeRequestModel.getReason(),
                DaysOfTheWeek.valueOf(employeeRequestModel.getDaysOfTheWeek())
        );

        Employee employee = employeeRequestMapper.requestModelToEntity(
                employeeRequestModel,
                new EmployeeIdentifier(),
                daysNonAvailable,
                salary
        );
        return employeeResponseMapper.entityToResponseModel(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId) {
        if(!employeeRepository.existsEmployeeByEmployeeIdentifier_EmployeeId(employeeId))
            throw new NotFoundException("Unknown employeeId " + employeeId);


        Employee employee = employeeRepository.findEmployeeByEmployeeIdentifier_EmployeeId(employeeId);
        Date date = new Date(
                employeeRequestModel.getStartDay(),
                employeeRequestModel.getStartMonth(),
                employeeRequestModel.getStartYear(),
                employeeRequestModel.getEndDay(),
                employeeRequestModel.getEndMonth(),
                employeeRequestModel.getEndYear()
        );

        Price price = new Price(
                employeeRequestModel.getValue(),
                Currency.valueOf(employeeRequestModel.getCurrency())
        );
        Salary salary = new Salary(
                price,
                PaymentMethod.valueOf(employeeRequestModel.getPaymentMethod())
        );
        DaysNonAvailable daysNonAvailable = new DaysNonAvailable(
                employeeRequestModel.getFullDay(),
                date,
                employeeRequestModel.getReason(),
                DaysOfTheWeek.valueOf(employeeRequestModel.getDaysOfTheWeek())
        );
        Employee updatedEmployee = employeeRequestMapper.requestModelToEntity(
                employeeRequestModel,
                employee.getEmployeeIdentifier(),
                daysNonAvailable,
                salary
        );

        updatedEmployee.setId(employee.getId());

        return employeeResponseMapper.entityToResponseModel(employeeRepository.save(updatedEmployee));
    }

    @Transactional
    @Override
    public void removeEmployee(String employeeId) {
        if(!employeeRepository.existsEmployeeByEmployeeIdentifier_EmployeeId(employeeId))
            throw new NotFoundException("Unknown employeeId " + employeeId);
        employeeRepository.deleteEmployeeByEmployeeIdentifier_EmployeeId(employeeId);
    }
}
