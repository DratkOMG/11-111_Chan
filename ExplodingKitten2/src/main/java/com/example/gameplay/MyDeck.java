package com.example.gameplay;

import com.example.models.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyDeck {
    private List<Card> myCardList;


    public void addCard(Card deckOfCards) {
        myCardList.add(deckOfCards);
    }

    public void addAllCard(List<Card> cardList) {
        myCardList.addAll(cardList);
    }

    public void remove(Card cardInMyDeck, int itThis) {
        myCardList.remove(itThis);
    }

    public boolean withoutDefuse() {
        for (Card card : myCardList) {
            if (card.getFaceName().equals("Defuse")) {
                return false;
            }
        }
        return true;
    }

    public static String myDeckToString(MyDeck myDeck) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card: myDeck.getMyCardList()) {
            if (card == null) {
                return null;
            }
            stringBuilder.append(card.toString());
            stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }

    public static MyDeck myDeckFromString(String deckData) throws FileNotFoundException {
        String[] cardData = deckData.split(";");
        List<Card> deck = new ArrayList<>();
        for (String data: cardData) {
            Card card = Card.fromString(data);
            if (card == null) {
                return null;
            }
            deck.add(card);
        }

        return MyDeck.builder()
                .myCardList(deck)
                .build();
    }
}
