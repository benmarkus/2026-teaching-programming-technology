package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameRunRepository {
    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS game_runs (
                id              INTEGER PRIMARY KEY AUTOINCREMENT,
                player_name     TEXT NOT NULL,
                board_size      INTEGER NOT NULL,
                clicks          INTEGER NOT NULL,
                elapsed_millis  INTEGER NOT NULL,
                played_at       TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
            )
            """;

    private static final String INSERT_SQL = """
            INSERT INTO game_runs (player_name, board_size, clicks, elapsed_millis)
            VALUES (?, ?, ?, ?)
            """;

    private static final String SELECT_TOP_SQL = """
            SELECT player_name, board_size, clicks, elapsed_millis
            FROM game_runs
            WHERE board_size = ?
            ORDER BY elapsed_millis ASC, clicks ASC, id ASC
            LIMIT ?
            """;

    private final String url;

    public GameRunRepository(String url) throws SQLException {
        this.url = url;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_SQL);
        }
    }

    public void save(GameRun run) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, run.playerName());
            statement.setInt(2, run.boardSize());
            statement.setInt(3, run.clicks());
            statement.setLong(4, run.elapsedMillis());
            statement.executeUpdate();
        }
    }

    public List<GameRun> findTopRuns(int boardSize, int limit) throws SQLException {
        List<GameRun> runs = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TOP_SQL)) {
            statement.setInt(1, boardSize);
            statement.setInt(2, limit);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    runs.add(new GameRun(
                            resultSet.getString("player_name"),
                            resultSet.getInt("board_size"),
                            resultSet.getInt("clicks"),
                            resultSet.getLong("elapsed_millis")));
                }
            }
        }
        return runs;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
