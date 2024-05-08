package org.champqcsoft.apigateway.domainclientlayer.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.champqcsoft.apigateway.presentationlayer.customers.ClientRequestModel;
import org.champqcsoft.apigateway.presentationlayer.customers.ClientResponseModel;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@Component
public class CustomerServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String CUSTOMERS_SERVICE_BASE_URL;

    private CustomerServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                                     @Value("${app.customers-service.host}") String customerServiceHost,
                                     @Value("${app.customers-service.port}") String customerServicePort){
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.CUSTOMERS_SERVICE_BASE_URL = "http://" + customerServiceHost + ":" + customerServicePort + "/api/v1/clients";
    }
    public List<ClientResponseModel> getAllClients(){
        try{
            String url = CUSTOMERS_SERVICE_BASE_URL;
            ClientResponseModel[] customerResponseModel = restTemplate.getForObject(url, ClientResponseModel[].class);
            return Arrays.asList(customerResponseModel);
        }
        catch (HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }
    public ClientResponseModel findClientByClientIdentifier_ClientId(String customerId){
        try{
            String url = CUSTOMERS_SERVICE_BASE_URL + "/" + customerId;

            ClientResponseModel customerModel = restTemplate.getForObject(url, ClientResponseModel.class);

            return customerModel;
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
    public ClientResponseModel createCustomer(ClientRequestModel customerRequestModel){
        try{
            String url = CUSTOMERS_SERVICE_BASE_URL;

            ClientResponseModel customerResponseModel = restTemplate.postForObject(url, customerRequestModel, ClientResponseModel.class);

            return customerResponseModel;
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }
    public ClientResponseModel updateClientByClient_Id(ClientRequestModel clientRequestModel, String customerId){
        try{
            String url = CUSTOMERS_SERVICE_BASE_URL + "/" + customerId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<ClientRequestModel> requestEntity = new HttpEntity<>(clientRequestModel, headers);
            ResponseEntity<ClientResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ClientResponseModel.class);

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

    public void deleteClientByClient_Id(String customerId){
        try{
            String url = CUSTOMERS_SERVICE_BASE_URL + "/" + customerId;

            restTemplate.delete(url);
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
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
