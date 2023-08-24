package com.example.LibraryManagement_System.CustomeExceptions;

public class BookNotFoundException extends Exception{

    public BookNotFoundException(String message){
        super(message);
    }
}
