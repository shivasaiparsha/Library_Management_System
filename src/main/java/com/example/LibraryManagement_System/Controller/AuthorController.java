package com.example.LibraryManagement_System.Controller;

import com.example.LibraryManagement_System.Author;
import com.example.LibraryManagement_System.Repositary.AuthorRepositary;
import com.example.LibraryManagement_System.RequestDto.UpdatePenNameRequestDto;
import com.example.LibraryManagement_System.Service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@Slf4j
public class AuthorController {


    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity<String> addAuthorTodb(@RequestBody Author author)
    {
        try{

            authorService.addAuthor(author);
            return new ResponseEntity<>("author added successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("invalid author");
           return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateNameAndPenName")

    public ResponseEntity<String> updateNameAndPenName(@RequestBody UpdatePenNameRequestDto updatePenNameRequestDto)
    {
          try{

              authorService.updatePenNameAndAuthor(updatePenNameRequestDto);
              return new ResponseEntity<>("author name and penName added successfully",HttpStatus.OK);
          }
          catch (Exception e) {

           log.error("author id is invvalid {}",e.getMessage());
           return new ResponseEntity<>("invalid details found",HttpStatus.BAD_REQUEST);
       }
    }

}
