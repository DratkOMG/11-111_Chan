package com.example.constans;

import com.example.models.Card;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BasicKittens {
    public static final List<Card> CARDS_OF_BASIC_KITTENS = new ArrayList<>();
    private static final String Exploding_Kittens = "Exploding Kittens";
    private static final String Skip = "Skip";
    private static final String Attack = "Attack";
    private static final String Shuffle = "Shuffle";
    private static final String Favor = "Favor";
    private static final String Nope = "Nope";
    private static final String Alter_The_Future = "Alter The Future";
    private static final String Alter_The_Future_x2 = "Alter The Future x2";
    private static final String Alter_The_Future_x3 = "Alter The Future x3";
    private static final String Alter_The_Future_x5 = "Alter The Future x5";
    private static final String See_The_Future_x1 = "See The Future x1";
    private static final String See_The_Future_x3 = "See The Future x3";
    private static final String See_The_Future_x5 = "See The Future x5";
    private static final String Reverse = "Reverse";
    private static final String SELF_SLAP = "Self Slap";
    private static final String SUPER_SKIP = "Super Skip";
    private static final String DRAW_FROM_THE_BOTTOM = "Draw From The Bottom";
    private static final String SWAP_TOP_AND_BOTTOM = "Swap Top And Bottom";
    private static final String Defuse = "Defuse";
    private static final String Cattermelon = "Cattermelon";
    private static final String Tacocat = "Tacocat";
    private static final String Rainbow_ralphing_Cat = "Rainbow-ralphing Cat";
    private static final String Beard_Cat = "Beard Cat";
    private static final String Hairy_Potato_Cat = "Hairy Potato Cat";


    static {
        for (int i = 0; i < 4; i++) {
            try {

                CARDS_OF_BASIC_KITTENS.add(Card.builder()
                        .faceName(Skip)
                        .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Skip + ".png")))
                        .cardInformation("Immediately end your turn without drawing a card")
                        .haveFunction(true)
                        .build());
////
                CARDS_OF_BASIC_KITTENS.add(Card.builder()
                        .faceName(Attack)
                        .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Attack + ".png")))
                        .cardInformation("End your turn(s) without drawing and force the next player to take two turns in a row")
                        .haveFunction(true)
                        .build());
//
                CARDS_OF_BASIC_KITTENS.add(Card.builder()
                        .faceName(Shuffle)
                        .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Shuffle + ".png")))
                        .cardInformation("Shuffle the Draw Pile without viewing the cards until told to stop")
                        .haveFunction(true)
                        .build());

                CARDS_OF_BASIC_KITTENS.add(Card.builder()
                        .faceName(Favor)
                        .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Favor + ".png")))
                        .cardInformation("Force any other player to give you 1 card from their hand. They choose which card to give you")
                        .haveFunction(true)
                        .build());

                CARDS_OF_BASIC_KITTENS.add(Card.builder()
                        .faceName(SWAP_TOP_AND_BOTTOM)
                        .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + SWAP_TOP_AND_BOTTOM + ".png")))
                        .cardInformation("Swap the top and bottom cards of the draw pile")
                        .haveFunction(true)
                        .build());

                CARDS_OF_BASIC_KITTENS.add(Card.builder()
                        .faceName(Reverse)
                        .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Reverse + ".png")))
                        .cardInformation("Reverse the order of play and end your turn without drawing a card. " +
                                "If there are only 2 players, this card acts like a Skip Card. " +
                                "If you play this card after you've been attacked, the order of play is reversed. " +
                                "But you've only ended 1 of your 2 turns.")
                        .haveFunction(true)
                        .build());

                CARDS_OF_BASIC_KITTENS.add(Card.builder()
                        .faceName(DRAW_FROM_THE_BOTTOM)
                        .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + DRAW_FROM_THE_BOTTOM + ".png")))
                        .cardInformation("End your turn by drawing the bottom card from the Draw Pile.")
                        .haveFunction(true)
                        .build());
//
//                CARDS_OF_BASIC_KITTENS.add(Card.builder()
//                        .faceName(Nope)
//                        .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Nope + ".png")))
//                        .cardInformation("Stops any action except for an Exploding Kitten card or a Defuse card")
//                        .haveFunction(true)
//                        .build());

                CARDS_OF_BASIC_KITTENS.add(Card.builder()
                        .faceName(Defuse)
                        .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Defuse + ".png")))
                        .cardInformation("If you drew an Exploding Kitten, you can play this card instead of dying")
                        .haveFunction(true)
                        .build());

                CARDS_OF_BASIC_KITTENS.add(Card.builder()
                        .faceName(See_The_Future_x3)
                        .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + See_The_Future_x3 + ".png")))
                        .cardInformation("Peek at the top 3 cards from the Draw Pile and put them back in the same order")
                        .haveFunction(true)
                        .build());


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        try {
//            CARDS_OF_BASIC_KITTENS.add(Card.builder()
//                    .faceName(Alter_The_Future)
//                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Alter_The_Future + ".png")))
//                    .cardInformation("Privately view the top 3 cards from the Draw Pile and rearrange them in any order you'd like. " +
//                            "Return them to the top of the Draw Pile face down, then continue with your turn.")
//                    .haveFunction(true)
//                    .build());
//            CARDS_OF_BASIC_KITTENS.add(Card.builder()
//                    .faceName(Alter_The_Future_x2)
//                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Alter_The_Future_x2 + ".png")))
//                    .cardInformation("Privately view the top 2 cards from the Draw Pile and rearrange them in any order you'd like. " +
//                            "Return them to the top of the Draw Pile face down, then continue with your turn.")
//                    .haveFunction(true)
//                    .build());
//
//            CARDS_OF_BASIC_KITTENS.add(Card.builder()
//                    .faceName(Alter_The_Future_x3)
//                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Alter_The_Future_x3 + ".png")))
//                    .cardInformation("Privately view the top 3 cards from the Draw Pile and rearrange them in any order you'd like. " +
//                            "Return them to the top of the Draw Pile face down, then continue with your turn.")
//                    .haveFunction(true)
//                    .build());

//            CARDS_OF_BASIC_KITTENS.add(Card.builder()
//                    .faceName(Alter_The_Future_x5)
//                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Alter_The_Future_x5 + ".png")))
//                    .cardInformation("Privately view the top 3 cards from the Draw Pile and rearrange them in any order you'd like. " +
//                            "Return them to the top of the Draw Pile face down, then continue with your turn.")
//                    .haveFunction(true)
//                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(Cattermelon)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Cattermelon + ".png")))
                    .cardInformation("These cards are powerless on their own, but can be played in Pairs or Special Combos")
                    .haveFunction(false)
                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(Tacocat)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Tacocat + ".png")))
                    .cardInformation("These cards are powerless on their own, but can be played in Pairs or Special Combos")
                    .haveFunction(false)
                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(Rainbow_ralphing_Cat)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Rainbow_ralphing_Cat + ".png")))
                    .cardInformation("These cards are powerless on their own, but can be played in Pairs or Special Combos")
                    .haveFunction(false)
                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(Beard_Cat)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Beard_Cat + ".png")))
                    .cardInformation("These cards are powerless on their own, but can be played in Pairs or Special Combos")
                    .haveFunction(false)
                    .build());
//
            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(Hairy_Potato_Cat)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Hairy_Potato_Cat + ".png")))
                    .cardInformation("These cards are powerless on their own, but can be played in Pairs or Special Combos")
                    .haveFunction(false)
                    .build());
            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(Defuse)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Defuse + ".png")))
                    .cardInformation("If you drew an Exploding Kitten, you can play this card instead of dying")
                    .haveFunction(true)
                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(See_The_Future_x5)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + See_The_Future_x5 + ".png")))
                    .cardInformation("Peek at the top 5 cards from the Draw Pile and put them back in the same order")
                    .haveFunction(true)
                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(See_The_Future_x1)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + See_The_Future_x1 + ".png")))
                    .cardInformation("Peek at the top 1 cards from the Draw Pile and put them back in the same order")
                    .haveFunction(true)
                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(SELF_SLAP)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + SELF_SLAP + ".png")))
                    .cardInformation("Apply a slap to yourself that you can take more 1 turns in a row")
                    .haveFunction(true)
                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(Exploding_Kittens)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Exploding_Kittens + ".png")))
                    .cardInformation("Unless you have a Defuse card, you're dead")
                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(Exploding_Kittens)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Exploding_Kittens + ".png")))
                    .cardInformation("Unless you have a Defuse card, you're dead")
                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(Exploding_Kittens)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + Exploding_Kittens + ".png")))
                    .cardInformation("Unless you have a Defuse card, you're dead")
                    .build());

            CARDS_OF_BASIC_KITTENS.add(Card.builder()
                    .faceName(SUPER_SKIP)
                    .image(new Image(new FileInputStream("src/main/resources/DoneCards/" + SUPER_SKIP + ".png")))
                    .cardInformation("End your turn without drawing a card. If you're supposed to take multiple turn, end them all.")
                    .haveFunction(true)
                    .build());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Card> getCardOfBasicKittens() {
        return CARDS_OF_BASIC_KITTENS;
    }

}
