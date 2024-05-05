package org.champqcsoft.customerservice.utils;


import org.champqcsoft.customerservice.utils.exceptions.ClientNotFoundException;
import org.champqcsoft.customerservice.utils.exceptions.InUseException;
import org.champqcsoft.customerservice.utils.exceptions.InvalidInputException;
import org.champqcsoft.customerservice.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class GlobalControllerExceptionHandlerTest {

    private final GlobalControllerExceptionHandler handler = new GlobalControllerExceptionHandler();

    @Test
    void handleNotFoundException() {
        NotFoundException ex = new NotFoundException("Not found");
        WebRequest request = mock(WebRequest.class);
        HttpStatus expectedStatus = HttpStatus.NOT_FOUND;

        assertEquals(expectedStatus, handler.handleNotFoundException(request, ex).getHttpStatus());
        assertNotNull(handler.handleNotFoundException(request, ex).getMessage());
    }

    @Test
    void handleInUseException() {
        InUseException ex = new InUseException("In use");
        WebRequest request = mock(WebRequest.class);
        HttpStatus expectedStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        assertEquals(expectedStatus, handler.handleInUseException(request, ex).getHttpStatus());
        assertNotNull(handler.handleInUseException(request, ex).getMessage());
    }

    @Test
    void handleInvalidInputException() {
        InvalidInputException ex = new InvalidInputException("Invalid input");
        WebRequest request = mock(WebRequest.class);
        HttpStatus expectedStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        assertEquals(expectedStatus, handler.handleInvalidInputException(request, ex).getHttpStatus());
        assertNotNull(handler.handleInvalidInputException(request, ex).getMessage());
    }

    @Test
    void handleClientNotFoundException() {
        ClientNotFoundException ex = new ClientNotFoundException("Not found");
        WebRequest request = mock(WebRequest.class);
        HttpStatus expectedStatus = HttpStatus.NOT_FOUND;

        assertEquals(expectedStatus, handler.handleNotFoundException(request, ex).getHttpStatus());
        assertNotNull(handler.handleNotFoundException(request, ex).getMessage());
    }
}