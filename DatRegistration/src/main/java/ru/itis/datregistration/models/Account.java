package ru.itis.datregistration.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String email;
    private String password;
    private int yearOfBirth;
}
