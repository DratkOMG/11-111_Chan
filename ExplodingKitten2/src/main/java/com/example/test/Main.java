package com.example.test;

import com.example.constans.BasicKittens;
import com.example.gameplay.DeckOfCards;
import com.example.gameplay.MyDeck;
import com.example.models.Card;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        BasicKittens basicKittens = new BasicKittens();
//        DeckOfCards deckOfCards = DeckOfCards.builder()
//                .deck(basicKittens.getCardOfBasicKittens())
//                .backOfCard(new Image(new FileInputStream("src/main/resources/DoneCards/Back Of Card.png")))
//                .build();
//
//        List<Integer> integerList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            integerList.add(i);
//        }
//        System.out.println(integerList);
//
//
//        swap(integerList);
//        System.out.println(integerList);

//        System.out.println(DeckOfCards.deckToString(deckOfCards.getDeck()));
//
//        System.out.println("----------------");
//
//        System.out.println(DeckOfCards.deckFromString(DeckOfCards.deckToString(deckOfCards.getDeck())));

//        Card card = Card.builder()
//                .faceName("Tacocat")
//                .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + "Tacocat" + ".png")))
//                .cardInformation("These cards are powerless on their own, but can be played in Pairs or Special Combos")
//                .haveFunction(false)
//                .build();
//
//        System.out.println(Card.fromString(card.toString()));

        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("qwer");
        list.add("asdf");
        System.out.println(list);
        int i = list.size();
        list.add(list.size() - (i - 1), "adda");
        System.out.println(list);
    }

    private static void swap(List<Integer> integerList) {
        int first = integerList.remove(integerList.size() - 1);
        integerList.add(0, first);
    }


}
