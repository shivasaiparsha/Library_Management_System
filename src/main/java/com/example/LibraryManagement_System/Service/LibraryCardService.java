package com.example.LibraryManagement_System.Service;

import com.example.LibraryManagement_System.Library_Card;
import com.example.LibraryManagement_System.Repositary.LibraryCardRepo;
import com.example.LibraryManagement_System.Repositary.StudentRepositary;
import com.example.LibraryManagement_System.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryCardService {

    @Autowired
    private StudentRepositary studentRepositary;
    @Autowired
    private LibraryCardRepo libraryCardRepo;

 public void addcardToDb(Library_Card card) throws Exception{


     libraryCardRepo.save(card);
 }
 public void issueCardToStudent(Integer cid, Integer sid) throws Exception
 {

     if(!studentRepositary.existsById(sid))
     {
         throw new Exception("student id invalid");
     }

     if(!libraryCardRepo.existsById(cid))
     {
         throw new Exception("card id invalid");
     }

     Optional<Student> optional =  studentRepositary.findById(sid);
     Student student=optional.get();

     Optional<Library_Card> optionalLibraryCard=libraryCardRepo.findById(cid);
      Library_Card libraryCard=optionalLibraryCard.get();

       // setting the foriegn key
      libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard); // sice its a bidirectional mapping

      // after setting the librarycard object also we need to set the library card object

        // after setting the foreign key we need to save student
     // save both of them since both were updated
     studentRepositary.save(student);
     // due to the bidrectional mapping we dont need to save the libraryccard repositary
     // its automatically updated

 }
}
