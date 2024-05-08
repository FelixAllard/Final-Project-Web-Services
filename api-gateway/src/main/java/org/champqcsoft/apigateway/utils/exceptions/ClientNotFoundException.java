package org.champqcsoft.apigateway.utils.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(){

    }
    public ClientNotFoundException(String message){super(message);}
    public ClientNotFoundException(Throwable cause){super(cause);}
    public ClientNotFoundException(String message, Throwable cause){super(message,cause);}
}
