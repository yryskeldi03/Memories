package com.geek.memories.domain;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Game<Content> {

    private List<Card<Content>> cards = new ArrayList<>();

    public Game(List<Content> contents) {
        for (int i = 0; i < contents.size(); i++) {
            cards.add(new Card<>(i * 2, false, false, contents.get(i)));
            cards.add(new Card<>((i * 2) + 1, false, false, contents.get(i)));
        }
        Collections.shuffle(cards);
    }

    public void choose(Card<Content> card) {
        card.setFaceUp(!card.isFaceUp());
    }

    public void checkPairs(Card<Content> card) {
        int isOpened = -1;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).isFaceUp() && cards.get(i).getContent().equals(card.getContent()) && cards.get(i).getId() != card.getId()) {
                card.setMatch(true);
                cards.get(i).setMatch(true);
            }
            if (cards.get(i).isFaceUp() && cards.get(i).getId() != card.getId() && !card.isMatch() && !cards.get(i).isMatch()) {
                isOpened = i;
            }
        }

        if (isOpened != -1) {
            card.setFaceUp(false);
            cards.get(isOpened).setFaceUp(false);
        }
    }

    public int removeMatchedCards() {
        int isAllMatched = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).isMatch()) {
                cards.get(i).setFaceUp(true);
                isAllMatched++;
            }
        }
        return isAllMatched;
    }

    public List<Card<Content>> getCards() {
        return cards;
    }
}
