package com.example.models;

import javafx.scene.image.Image;
import lombok.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String faceName;

    private Image image;

    private String cardInformation;

    private Boolean haveFunction;

    public static Card fromString(String str) throws FileNotFoundException {
        String[] parts = str.split(",  ");
        try {
            String faceName = parts[0].split("'")[1];
            String imageStr = parts[1].split("=")[1];
            String cardInformation = parts[2].split("'")[1];
            Boolean haveFunction = Boolean.valueOf(parts[3].split("=")[1].split("}")[0]);

            Image image = new Image(new FileInputStream(imageStr));

            return Card.builder()
                    .faceName(faceName)
                    .image(image)
                    .cardInformation(cardInformation)
                    .haveFunction(haveFunction)
                    .build();
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }

    }


    @Override
    public String toString() {
        return "Card{" +
                "faceName='" + faceName + '\'' +
                ",  image=src/main/resources/DoneCards/" + faceName + ".png" +
                ",  cardInformation='" + cardInformation + '\'' +
                ",  haveFunction=" + haveFunction +
                '}';
    }
}
