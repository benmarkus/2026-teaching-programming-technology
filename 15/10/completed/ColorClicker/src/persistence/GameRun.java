package persistence;

public record GameRun(String playerName, int boardSize, int clicks, long elapsedMillis) {
}
