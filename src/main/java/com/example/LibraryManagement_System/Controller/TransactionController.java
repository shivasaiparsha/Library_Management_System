package com.example.LibraryManagement_System.Controller;

import com.example.LibraryManagement_System.RequestDto.issueBookReqDTO;
import com.example.LibraryManagement_System.Service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapping")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/issuebook")
    public ResponseEntity<String>  issueBookStudent(@RequestBody issueBookReqDTO bookissueReqDto){

        try{
            String message = transactionService.issuebooktostudent(bookissueReqDto);
            return  new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch(Exception e)
        {
            log.error("unable to issue book to student");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
