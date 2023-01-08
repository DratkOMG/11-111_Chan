package com.example.gameplay;

import com.example.constans.BasicKittens;
import com.example.models.Card;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeckOfCards {
    private List<Card> deck;
    private Image backOfCard;

    public Card dealTopCard() {
        if (deck.size() > 0) {
            return deck.remove(deck.size() - 1);
        } else {
            return null;
        }
    }

    public Card dealBotCard() {
        if (deck.size() > 0) {
            return deck.remove(0);
        } else {
            return null;
        }
    }

    public Card checkOneTopCard() {
        if (deck.size() > 0) {
            return deck.get(deck.size() - 1);
        } else {
            return null;
        }
    }
    public Card checkOneBotCard() {
        if (deck.size() > 0) {
            return deck.get(0);
        } else {
            return null;
        }
    }
    public void addCard(Card card) {
        this.deck.add(card);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public int size() {
        return deck.size();
    }

    public void addCards(BasicKittens basicKittens) {
        this.deck.addAll(basicKittens.getCardOfBasicKittens());
    }

    public Card removeDefuseCard() {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getFaceName().equals("Defuse")) {
                return deck.remove(i);
            }
        }
        return null;
    }

    public static DeckOfCards deckFromString(String deckData) throws FileNotFoundException {
        String[] cardData = deckData.split(";");
        List<Card> deck = new ArrayList<>();
        for (String data : cardData) {
            Card card = Card.fromString(data);
            deck.add(card);
        }
        return DeckOfCards.builder()
                .deck(deck)
                .backOfCard(new Image(new FileInputStream("src/main/resources/DoneCards/Back Of Card.png")))
                .build();
    }

    public static String deckToString(List<Card> deck) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : deck) {
            stringBuilder.append(card.toString());
            stringBuilder.append(";");
        }

        return stringBuilder.toString();
    }

    public Card getDefuseCard() {
        for (Card card : deck) {
            if (card.getFaceName().equals("Defuse")) {
                return card;
            }
        }
        return null;
    }

    public List<Card> removeRandom4Card() {
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            if (!deck.get(i).getFaceName().equals("Exploding Kittens") && !deck.get(i).getFaceName().equals("Defuse")) {
                cardList.add(deck.remove(i));
                if (cardList.size() == 4) {
                    break;
                }
            }
        }
        Collections.shuffle(deck);
        return cardList;
    }

    public Card getTopCard() {
        return deck.get(deck.size() - 1);
    }

    public Card getBotCard() {
        return deck.get(0);
    }
}
