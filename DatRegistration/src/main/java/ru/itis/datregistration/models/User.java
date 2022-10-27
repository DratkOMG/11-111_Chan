package ru.itis.datregistration.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String email;
    private String userName;
    private Integer age;
    private Long phoneNumber;

    @Override
    public String toString() {
        return  "email = " + email +
                ", userName = " + userName +
                ", age = " + age +
                ", phoneNumber = " + phoneNumber;
    }
}
