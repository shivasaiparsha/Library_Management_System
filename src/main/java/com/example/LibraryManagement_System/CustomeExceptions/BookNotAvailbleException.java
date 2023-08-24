package com.example.LibraryManagement_System.CustomeExceptions;

public class BookNotAvailbleException extends Exception{

    public BookNotAvailbleException(String message)
    {
        super(message);
    }
}
