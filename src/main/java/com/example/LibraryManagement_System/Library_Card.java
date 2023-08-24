package com.example.LibraryManagement_System;

import com.example.LibraryManagement_System.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Library_Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    private int noOfBookIssued;

    @OneToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "library card", cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();

}
