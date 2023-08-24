package com.example.LibraryManagement_System.Controller;

import com.example.LibraryManagement_System.RequestDto.AddBookDto;
import com.example.LibraryManagement_System.RequestDto.UpdatePenNameRequestDto;
import com.example.LibraryManagement_System.Service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addbook")
    public ResponseEntity<String> addBook(@RequestBody AddBookDto addBookDto)
    {
        try {
            bookService.addBooktodb(addBookDto);
            return new ResponseEntity<>("Book added Successfully", HttpStatus.OK);
        }
        catch (Exception e){

            log.error("Incorrect details found{}",e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
