package com.example.LibraryManagement_System;

import com.example.LibraryManagement_System.Enum.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
     @Column(unique = true)
     private String title;
     private boolean isAvailable;
    @Enumerated(value = EnumType.STRING)
     private Genre genre;
     private Date publicationDate;
     private Integer price;

     @ManyToOne
    @JoinColumn
     @JsonIgnore
    private Author author;

   public   Book(String title, Genre genre, boolean isAvailable,Integer price, Date publicationDate)
     {
         this.title=title;
         this.genre=genre;
         this.isAvailable=isAvailable;
         this.price=price;
         this.publicationDate=publicationDate;
     }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @OneToMany(mappedBy ="book", cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();
}
