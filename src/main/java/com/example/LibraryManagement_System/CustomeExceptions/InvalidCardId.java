package com.example.LibraryManagement_System.CustomeExceptions;

public class InvalidCardId extends Exception{

    public InvalidCardId(String message)
    {
        super(message);
    }
}
