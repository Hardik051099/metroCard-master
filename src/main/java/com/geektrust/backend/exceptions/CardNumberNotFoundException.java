package com.geektrust.backend.exceptions;

public class CardNumberNotFoundException extends RuntimeException{
    public CardNumberNotFoundException()
    {
        super();
    }
    public CardNumberNotFoundException(String msg)
    {
        super(msg);
    }
}