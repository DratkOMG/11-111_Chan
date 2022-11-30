package ru.itis.datbookshop.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private long accountId;
    private String email;
    private String password;
    private int isAdmin;
    private int isSeller;
}
