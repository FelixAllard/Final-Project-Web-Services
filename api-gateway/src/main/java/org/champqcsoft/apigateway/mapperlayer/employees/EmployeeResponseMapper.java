package org.champqcsoft.apigateway.mapperlayer.employees;

import org.champqcsoft.apigateway.presentationlayer.employees.EmployeeResponseModel;
import org.champqcsoft.apigateway.presentationlayer.employees.EmployeeController;

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


    EmployeeResponseModel entityToResponseModel(EmployeeResponseModel employeeResponseModel);

    List<EmployeeResponseModel> entityListToResponseModelList(List<EmployeeResponseModel> employeeResponseModelList);

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
