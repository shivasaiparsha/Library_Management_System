package com.example.LibraryManagement_System.Controller;

import com.example.LibraryManagement_System.Library_Card;
import com.example.LibraryManagement_System.Service.LibraryCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libraryCard")
@Slf4j
public class LibraryCardController {

    @Autowired
    private LibraryCardService libraryCardService;
  @PostMapping("/addCard")
  public ResponseEntity<String> addcard(@RequestBody Library_Card card)
  {
      try {
          libraryCardService.addcardToDb(card);
          return new ResponseEntity<>("Card added successfully", HttpStatus.OK);
      }
      catch (Exception e)
      {
          log.error("Invalid card parameters sent");
          return new ResponseEntity<>("Invalid Card Details {}"+e.getMessage(), HttpStatus.BAD_REQUEST);
      }

  }

  @PutMapping("/issueCardToStudent")
    public ResponseEntity<String> associateCardToStudent(@RequestParam("cardId") Integer cid, @RequestParam("stId") Integer sid)
  {
       try{

           libraryCardService.issueCardToStudent(cid, sid);

           return new ResponseEntity<>("Library card successfully associated with student", HttpStatus.OK);
       }
       catch (Exception e){
           log.error("library card not mapped with student {}",e.getMessage());

           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
  }

}
