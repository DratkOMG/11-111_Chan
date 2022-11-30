package ru.itis.datbookshop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private long orderId;
    private Timestamp date;
    private int price;
    private long userId;
    private String userName;
}
