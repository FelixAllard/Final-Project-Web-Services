package org.champqcsoft.employeeservice.datamapperlayer;


import org.champqcsoft.employeeservice.dataaccesslayer.Employee;
import org.champqcsoft.employeeservice.presentationlayer.EmployeeController;
import org.champqcsoft.employeeservice.presentationlayer.EmployeeResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper {

    @Mapping(expression = "java(employee.getEmployeeIdentifier().getEmployeeId())", target = "employeeId")
    @Mapping(expression = "java(employee.getName())", target = "name")
    @Mapping(expression = "java(employee.getAge())", target = "age")
    @Mapping(expression = "java(employee.getCurrentEmploymentStatus().name())", target = "currentEmploymentStatus")
    @Mapping(expression = "java(employee.getDaysNonAvailable().getFullDay())", target = "fullDay")
    @Mapping(expression = "java(employee.getDaysNonAvailable().getNonAvailability().getStartDay())", target = "startDay")
    @Mapping(expression = "java(employee.getDaysNonAvailable().getNonAvailability().getStartMonth())", target = "startMonth")
    @Mapping(expression = "java(employee.getDaysNonAvailable().getNonAvailability().getStartYear())", target = "startYear")
    @Mapping(expression = "java(employee.getDaysNonAvailable().getNonAvailability().getEndDay())", target = "endDay")
    @Mapping(expression = "java(employee.getDaysNonAvailable().getNonAvailability().getEndMonth())", target = "endMonth")
    @Mapping(expression = "java(employee.getDaysNonAvailable().getNonAvailability().getEndYear())", target = "endYear")
    @Mapping(expression = "java(employee.getDaysNonAvailable().getReason())", target = "reason")
    @Mapping(expression = "java(employee.getDaysNonAvailable().getDaysOfTheWeek().name())", target = "daysOfTheWeek")
    @Mapping(expression = "java(employee.getSalary().getSalaryPrice().getValue())", target = "value")
    @Mapping(expression = "java(employee.getSalary().getSalaryPrice().getCurrency().name())", target = "currency")
    @Mapping(expression = "java(employee.getSalary().getPaymentMethod().name())", target = "paymentMethod")
    EmployeeResponseModel entityToResponseModel(Employee employee);

    List<EmployeeResponseModel> entityListToResponseModelList(List<Employee> employees);

    @AfterMapping
    default void addLinks(@MappingTarget EmployeeResponseModel model){
        Link selfLink = linkTo(methodOn(EmployeeController.class)
                .getEmployeeByEmployeeId(model.getEmployeeId()))
                .withSelfRel();
        model.add(selfLink);

        Link employeesLink =
                linkTo(methodOn(EmployeeController.class)
                        .getEmployees())
                        .withRel("all employee");
        model.add(employeesLink);

    }
}
