package com.example.LibraryManagement_System;

import com.example.LibraryManagement_System.Enum.TransactionStatuses;
import com.example.LibraryManagement_System.Enum.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.transaction.TransactionStatus;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    private Integer id;

    @CreationTimestamp
    private Date creattimeat;

    @UpdateTimestamp
    private Date updateAt;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatuses transactionStatuses;

    @Enumerated(value=EnumType.STRING)
    private TransactionType transactionType;

    private Integer fineAmount;

    private List<Transaction> transactionList;
    public Transaction(TransactionType transactionType, TransactionStatuses transactionStatuses, Integer fineAmount)
    {
        this.transactionStatuses=transactionStatuses;
        this.transactionType=transactionType;
        this.fineAmount=fineAmount;
    }

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Book book;

    @OneToMany
    @JoinColumn
    @JsonIgnore
    private Library_Card libraryCard;


}
