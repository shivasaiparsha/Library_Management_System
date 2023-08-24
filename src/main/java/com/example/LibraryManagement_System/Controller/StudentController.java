package com.example.LibraryManagement_System.Controller;

import com.example.LibraryManagement_System.Enum.Department;
import com.example.LibraryManagement_System.Service.StudentService;
import com.example.LibraryManagement_System.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/add")

    public ResponseEntity<String> addStudent(@RequestBody Student student)
    {
        try{
            String s=studentService.addstudent(student);
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
        catch(Exception e){
            log.error("student not added");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/getDepartmentByStudentId")
    public ResponseEntity<Department> findDep(@RequestParam("ID") Integer studentId)
    {
        try {
            Department d = studentService.getDepartmentByID(studentId);
            return new ResponseEntity(d,HttpStatus.OK);
        }
        catch(Exception e){

            log.error("id enterd is invalid/wrong id sent {}",e.getMessage());

          return  new ResponseEntity("entered id is invalid",HttpStatus.BAD_REQUEST);
        }
    }
}
