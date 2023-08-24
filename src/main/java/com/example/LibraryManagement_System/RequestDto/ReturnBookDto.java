package com.example.LibraryManagement_System.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBookDto {
    private Integer bookId;
    private Integer cardId;
}
