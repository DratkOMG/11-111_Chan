package ru.itis.datbookshop.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long userId;
    private String email;
    private String userName;
    private Integer age;
    private String sex;
    private Long phoneNumber;
    private String city;
    private Integer balance;
}
