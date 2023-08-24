package com.example.LibraryManagement_System.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TotalAmount {

@Autowired
private TotalAmountService totalAmountService
    @GetMapping("totalFIneIn2023")
    public RequestMapping<String> totalFineCollected()
    {
         try{
             totalAmountService.findAmount();
         }
         catch(Exception e){
             log.error("")
         }
    }
}
