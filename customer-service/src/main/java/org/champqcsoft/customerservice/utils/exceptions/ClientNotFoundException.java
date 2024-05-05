package org.champqcsoft.customerservice.utils.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(){

    }
    public ClientNotFoundException(String message){super(message);}
    public ClientNotFoundException(Throwable cause){super(cause);}
    public ClientNotFoundException(String message, Throwable cause){super(message,cause);}
}
