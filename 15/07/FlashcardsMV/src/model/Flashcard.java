package model;

record Flashcard(String question, String answer) {
    public static Flashcard tryParse(String rawText) {
        String[] qa = rawText.split("~");
        if (qa.length == 2) {
            return new Flashcard(qa[0], qa[1]);
        } else {
            throw new IllegalArgumentException("Cannot create Flashcard from record.");
        }
    }
}
