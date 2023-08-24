package com.example.LibraryManagement_System.Service;

import com.example.LibraryManagement_System.Author;
import com.example.LibraryManagement_System.Book;
import com.example.LibraryManagement_System.Repositary.AuthorRepositary;
import com.example.LibraryManagement_System.Repositary.BookRepositary;
import com.example.LibraryManagement_System.RequestDto.AddBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

   @Autowired
   private AuthorRepositary authorRepositary;
    public void addBooktodb(AddBookDto addBookDto) throws Exception
    {
        Optional<Author> optionalAuthor=authorRepositary.findById(addBookDto.getAuthorId());
          if(!optionalAuthor.isPresent())
             throw new Exception("Author not found");
          Author author=optionalAuthor.get();

          Book book = new Book(addBookDto.getTitle(),addBookDto.getGenre(),addBookDto.isAvailable(),addBookDto.getPrice(),addBookDto.getPublicationDate());

          book.setAuthor(author);

        List<Book> bookList=author.getBookList();
        bookList.add(book);
        author.setBookList(bookList);

        authorRepositary.save(author);

    }


}
