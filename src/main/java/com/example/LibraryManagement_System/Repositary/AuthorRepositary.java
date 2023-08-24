package com.example.LibraryManagement_System.Repositary;

import com.example.LibraryManagement_System.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepositary extends JpaRepository<Author, Integer> {
}
