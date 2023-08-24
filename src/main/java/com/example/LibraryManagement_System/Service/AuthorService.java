package com.example.LibraryManagement_System.Service;

import com.example.LibraryManagement_System.Author;
import com.example.LibraryManagement_System.Repositary.AuthorRepositary;
import com.example.LibraryManagement_System.RequestDto.UpdatePenNameRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class AuthorService {


    @Autowired
    private AuthorRepositary authorRepositary;

    public void addAuthor(Author author) throws Exception
    {
       if(author.getAuthorId()!=null)
           throw new Exception("authour id not sent as parameter");

       authorRepositary.save(author);
    }

    public void updatePenNameAndAuthor(UpdatePenNameRequestDto updatePenNameRequestDto) throws Exception
    {
        Optional<Author> optionalAuthor=authorRepositary.findById(updatePenNameRequestDto.getAuthorId());

        if(!optionalAuthor.isPresent())
        {
            throw new Exception("Author not present");
        }

        Author author=optionalAuthor.get();
        author.setAuthorName(updatePenNameRequestDto.getNewAuthorName());
        author.setPenName(updatePenNameRequestDto.getPenName());
        authorRepositary.save(author);

    }
}
