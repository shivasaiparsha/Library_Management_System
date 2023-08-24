package com.example.LibraryManagement_System.Repositary;

import com.example.LibraryManagement_System.Library_Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryCardRepo extends JpaRepository<Library_Card, Integer> {
}
