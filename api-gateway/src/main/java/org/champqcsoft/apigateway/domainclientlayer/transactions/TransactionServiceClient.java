package org.champqcsoft.apigateway.domainclientlayer.transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.champqcsoft.apigateway.presentationlayer.transactions.PurchaseReceiptRequestModel;
import org.champqcsoft.apigateway.presentationlayer.transactions.PurchaseReceiptResponseModel;
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

import static org.hibernate.query.sqm.tree.SqmNode.log;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
@Slf4j
public class TransactionServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String TRANSACTION_SERVICE_BASE_URL;

    public TransactionServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                              @Value("${app.transactions-service.host}") String transactionServiceHost,
                              @Value("${app.transactions-service.port}") String transactionServicePort) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.TRANSACTION_SERVICE_BASE_URL = "http://" + transactionServiceHost + ":" + transactionServicePort + "/api/v1/transactions";
    }

    public List<PurchaseReceiptResponseModel> getAllTransactions() {
        try {
            String url = TRANSACTION_SERVICE_BASE_URL;

            PurchaseReceiptResponseModel[] transactionResponseModel = restTemplate.getForObject(url, PurchaseReceiptResponseModel[].class);

            return Arrays.asList(transactionResponseModel);
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public PurchaseReceiptResponseModel getTransactionByTransactionId(String transactionId) {
        try {
            String url = TRANSACTION_SERVICE_BASE_URL + "/" + transactionId;

            PurchaseReceiptResponseModel transactionResponseModel = restTemplate.getForObject(url, PurchaseReceiptResponseModel.class);

            return transactionResponseModel;
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public PurchaseReceiptResponseModel createTransaction(PurchaseReceiptRequestModel transactionRequestModel) {
        try {
            String url = TRANSACTION_SERVICE_BASE_URL;

            PurchaseReceiptResponseModel transactionResponseModel = restTemplate.postForObject(url, transactionRequestModel, PurchaseReceiptResponseModel.class);

            return transactionResponseModel;
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public PurchaseReceiptResponseModel updateTransactionByTransaction_Id(PurchaseReceiptRequestModel transactionRequestModel, String transactionId) {
        try {
            String url = TRANSACTION_SERVICE_BASE_URL + "/" + transactionId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<PurchaseReceiptRequestModel> requestEntity = new HttpEntity<>(transactionRequestModel, headers);
            ResponseEntity<PurchaseReceiptResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, PurchaseReceiptResponseModel.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Update failed with HTTP status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public void deleteTransactionByTransaction_Id(String transactionId) {
        try {
            String url = TRANSACTION_SERVICE_BASE_URL + "/" + transactionId;

            restTemplate.delete(url);
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        if (ex.getStatusCode() == NOT_FOUND) {
            return new NotFoundException(getErrorMessage(ex));
        }
        if (ex.getStatusCode() == UNPROCESSABLE_ENTITY) {
            return new InvalidInputException(getErrorMessage(ex));
        }
        log.warn("Got an unexpected HTTP error: {}, will rethrow it", new HttpStatusCode[]{ex.getStatusCode()});
        log.warn("Error body: {}", new String[]{ex.getResponseBodyAsString()});
        return ex;
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
}

