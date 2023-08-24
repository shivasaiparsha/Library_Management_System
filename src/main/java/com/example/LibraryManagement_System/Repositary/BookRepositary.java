package com.example.LibraryManagement_System.Repositary;

import com.example.LibraryManagement_System.Book;
import com.example.LibraryManagement_System.Enum.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepositary extends JpaRepository<Book,Integer> {
    List<Book> findBooksByGenre(Genre genre);
}
