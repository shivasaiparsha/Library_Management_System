package com.example.LibraryManagement_System.Service;

import com.example.LibraryManagement_System.Enum.Department;
import com.example.LibraryManagement_System.Repositary.StudentRepositary;
import com.example.LibraryManagement_System.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService  {

    @Autowired
    private StudentRepositary studentRepositary;
    public String addstudent(Student student) throws Exception{

        if(student.getRollNo()!=null) {
            throw new Exception("Id should not be sent as a parameter");
        }

         studentRepositary.save(student);
        return "student added successfully";
    }

    public Department getDepartmentByID(Integer rollNo) throws Exception{

        Optional<Student> optionalStudent = studentRepositary.findById(rollNo);

        if(!optionalStudent.isPresent())
        {
            throw new Exception("id entered is invalid");
        }

        Student student= optionalStudent.get();
        return student.getDepartment();
    }
}
