package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FlashcardsModel {
    private final List<Flashcard> flashcardList;
    private int currentlyShownCardIndex;
    private boolean currentCardShowsAnswer;
    private int score;

    public FlashcardsModel() {
        this.flashcardList = new ArrayList<>();
        this.currentlyShownCardIndex = 0;
        this.currentCardShowsAnswer = false;
        this.score = 0;
    }

    public void selectNext() {
        if (hasCurrentCard()) {
            this.currentlyShownCardIndex = (this.currentlyShownCardIndex + 1);
            this.currentCardShowsAnswer = false;
        }
    }

    public boolean hasCurrentCard() {
        return this.currentlyShownCardIndex < flashcardList.size();
    }

    public int getCardCount() {
        return this.flashcardList.size();
    }

    public boolean hasNoCards() {
        return this.flashcardList.isEmpty();
    }

    public void increaseScore() {
        this.score++;
    }

    public String getCurrentCardsUpsideText() {
        if (currentCardShowsAnswer) {
            return flashcardList.get(currentlyShownCardIndex).answer();
        } else {
            return flashcardList.get(currentlyShownCardIndex).question();
        }
    }

    public void flipCard() {
        this.currentCardShowsAnswer = !this.currentCardShowsAnswer;
    }

    public void readCardsFromFile(File file) throws IOException {
        List<Flashcard> loaded = new ArrayList<>();
        for (String line : Files.readAllLines(file.toPath())) {
            if (!line.isBlank()) {
                loaded.add(Flashcard.tryParse(line));
            }
        }
        if (loaded.isEmpty()) {
            throw new IllegalArgumentException("File contains no flashcards.");
        }
        flashcardList.clear();
        flashcardList.addAll(loaded);
        currentlyShownCardIndex = 0;
        currentCardShowsAnswer = false;
    }

    public int getScore() {
        return this.score;
    }

    public int getCurrentlyShownCardIndex() {
        return this.currentlyShownCardIndex;
    }
}

