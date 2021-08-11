package com.geek.memories.ui;

import com.geek.memories.domain.Card;
import com.geek.memories.domain.Game;

import java.util.ArrayList;
import java.util.List;

public class EmojiGame {
    private Game<String> game;

    public EmojiGame() {
        List<String> contents = new ArrayList<>();
        contents.add("\uD83D\uDE01");
        contents.add("\uD83D\uDE0B");
        contents.add("\uD83D\uDE1C");
        game = new Game<>(contents);
    }

    public void choose(Card<String> card) {
        game.choose(card);
    }

    public void checkPairs(Card<String> card) {
        game.checkPairs(card);
    }

    public int removeMatchedCards() {
        return game.removeMatchedCards();

    }

    public List<Card<String>> getCards() {
        return game.getCards();
    }
}
