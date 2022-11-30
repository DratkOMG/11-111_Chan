package ru.itis.datbookshop.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;
    private String title;
    private String image;
    private Integer price;
    private String author;
    private int quantitySold;
    private String description;
    private Long categoryId;
    private Long sellerId;
}
