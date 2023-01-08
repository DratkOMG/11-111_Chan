package com.example.gameplay;

import com.example.models.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Actions {

    public String useCard(Card cardInMyDeck) {
        if (cardInMyDeck.getFaceName().equals("Defuse")) {
            return useDefuse();
        }
        if (cardInMyDeck.getFaceName().equals("Shuffle")) {
            return useShuffle();
        }
        if (cardInMyDeck.getFaceName().equals("See The Future")) {
            return useSeeTheFuture();
        }
        if (cardInMyDeck.getFaceName().equals("Skip")) {
            return useSkip();
        }
        if (cardInMyDeck.getFaceName().equals("Favor")) {
            return useFavor();
        }
        if (cardInMyDeck.getFaceName().equals("Attack")) {
            return useAttack();
        }
        if (cardInMyDeck.getFaceName().equals("Reverse")) {
            return useReverse();
        }
        if (cardInMyDeck.getFaceName().equals("Draw From The Bottom")) {
            return useDrawFromTheBottom();
        }

        return "";
    }

    private String useDrawFromTheBottom() {
        return "Use Draw From The Bottom";
    }

    private String useReverse() {
        return "Use Reverse";
    }

    private String useAttack() {
        return "Use Attack";
    }

    private String useFavor() {
        return "Use Favor";
    }

    private String useSkip() {
        return "Use Skip";
    }

    private String useSeeTheFuture() {
        return "Use See The Future";
    }

    private String useShuffle() {
        return "Use Shuffle";
    }

    private String useDefuse() {
        return "Use Defuse";
    }
}
