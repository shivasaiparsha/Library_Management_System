package com.example.LibraryManagement_System.RequestDto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePenNameRequestDto {

    private Integer authorId;
    private String newAuthorName;
    private String penName;
}
