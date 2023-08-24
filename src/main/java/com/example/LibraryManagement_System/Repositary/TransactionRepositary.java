package com.example.LibraryManagement_System.Repositary;

import com.example.LibraryManagement_System.Book;
import com.example.LibraryManagement_System.Enum.TransactionStatuses;
import com.example.LibraryManagement_System.Enum.TransactionType;
import com.example.LibraryManagement_System.Library_Card;
import com.example.LibraryManagement_System.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepositary extends JpaRepository<Transaction, Integer> {
     List<Transaction> findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType(Book book, Library_Card card, TransactionStatuses transactionStatus, TransactionType transactionType);



}
