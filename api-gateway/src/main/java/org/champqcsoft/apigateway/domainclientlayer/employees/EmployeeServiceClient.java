package org.champqcsoft.apigateway.domainclientlayer.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.champqcsoft.apigateway.presentationlayer.customers.ClientRequestModel;
import org.champqcsoft.apigateway.presentationlayer.customers.ClientResponseModel;
import org.champqcsoft.apigateway.presentationlayer.employees.EmployeeRequestModel;
import org.champqcsoft.apigateway.presentationlayer.employees.EmployeeResponseModel;
import org.champqcsoft.apigateway.utils.HttpErrorInfo;
import org.champqcsoft.apigateway.utils.exceptions.InvalidInputException;
import org.champqcsoft.apigateway.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@Component
public class EmployeeServiceClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String EMPLOYEES_SERVICE_BASE_URL;

    private EmployeeServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                                  @Value("${app.employees-service.host}") String employeeServiceHost,
                                  @Value("${app.employees-service.port}") String employeeServicePort){
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.EMPLOYEES_SERVICE_BASE_URL = "http://" + employeeServiceHost + ":" + employeeServicePort + "/api/v1/employees";
    }


    public List<EmployeeResponseModel> getAllEmployee(){
        try{
            String url = EMPLOYEES_SERVICE_BASE_URL;
            EmployeeResponseModel[] employeeResponseModels = restTemplate.getForObject(url, EmployeeResponseModel[].class);
            return Arrays.asList(employeeResponseModels);
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }
    public EmployeeResponseModel findEmployeeByEmployeeIdentifier_EmployeeId(String employeeId){
        try{
            String url = EMPLOYEES_SERVICE_BASE_URL + "/" + employeeId;

            EmployeeResponseModel employeeModel = restTemplate.getForObject(url, EmployeeResponseModel.class);

            return employeeModel;
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }


    public EmployeeResponseModel createEmployee(EmployeeRequestModel employeeRequestModel){
        try{
            String url = EMPLOYEES_SERVICE_BASE_URL;

            EmployeeResponseModel employeeResponseModel = restTemplate.postForObject(url, employeeRequestModel, EmployeeResponseModel.class);

            return employeeResponseModel;
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }
    public EmployeeResponseModel updateEmployeeByEmployee_Id(EmployeeRequestModel employeeRequestModel, String employeeId){
        try{
            String url = EMPLOYEES_SERVICE_BASE_URL + "/" + employeeId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<EmployeeRequestModel> requestEntity = new HttpEntity<>(employeeRequestModel, headers);
            ResponseEntity<EmployeeResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, EmployeeResponseModel.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Update failed with HTTP status code: " + responseEntity.getStatusCodeValue());
            }
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public void deleteEmployeeByEmployee_Id(String employeeId){
        try{
            String url = EMPLOYEES_SERVICE_BASE_URL + "/" + employeeId;

            restTemplate.delete(url);
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        //include all possible responses from the client
        if (ex.getStatusCode() == NOT_FOUND) {
            return new NotFoundException(getErrorMessage(ex));
        }
        if (ex.getStatusCode() == UNPROCESSABLE_ENTITY) {
            return new InvalidInputException(getErrorMessage(ex));
        }
        log.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
        log.warn("Error body: {}", ex.getResponseBodyAsString());
        return ex;
    }
    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
}
