package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * The Database class provides methods to create database tables and establish connections
 * to a MySQL database.
 */
public class Database {

    /**
     * Creates the necessary tables in the database if they do not already exist.
     * The tables created are: user, game, played, review, and audit.
     */
    public static void creareTabele() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();

            String crearePlayed = "CREATE TABLE IF NOT EXISTS played (\n" +
                    "    Username VARCHAR(255),\n" +
                    "    GameName VARCHAR(255),\n" +
                    "    PRIMARY KEY (Username, GameName),\n" +
                    "    FOREIGN KEY (Username) REFERENCES user(username),\n" +
                    "    FOREIGN KEY (GameName) REFERENCES game(name)\n" +
                    ");";

            String creareUser = "CREATE TABLE IF NOT EXISTS `user` (\n" +
                    "  `username` varchar(100) NOT NULL,\n" +
                    "  `password` varchar(100) NOT NULL,\n" +
                    "  `admin` tinyint DEFAULT '0',\n" +
                    "  PRIMARY KEY (`username`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n";

            String creareGame = "CREATE TABLE IF NOT EXISTS `game` (\n" +
                    "  `name` varchar(45) NOT NULL,\n" +
                    "  `price` int unsigned DEFAULT '20',\n" +
                    "  `tags` varchar(100) DEFAULT NULL,\n" +
                    "  `developer` varchar(45) NOT NULL,\n" +
                    "  `description` varchar(200) DEFAULT 'This game has no description yet.',\n" +
                    "  `rating` decimal(2,0) unsigned DEFAULT NULL,\n" +
                    "  `agerating` int DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`name`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n";

            String creareAudit = "CREATE TABLE IF NOT EXISTS `audit` (\n" +
                    "  `idaudit` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `Comanda` text NOT NULL,\n" +
                    "  `UserName` varchar(100) NOT NULL,\n" +
                    "  PRIMARY KEY (`idaudit`),\n" +
                    "  KEY `username_idx` (`UserName`),\n" +
                    "  CONSTRAINT `username` FOREIGN KEY (`UserName`) REFERENCES `user` (`username`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

            String creareReview = "CREATE TABLE IF NOT EXISTS review (\n" +
                    "    Username VARCHAR(255),\n" +
                    "    GameName VARCHAR(255),\n" +
                    "    comment TEXT,\n" +
                    "    rating DECIMAL(4,2) CHECK (rating >= 0.00 AND rating <= 10.00),\n" +
                    "    PRIMARY KEY (Username, GameName),\n" +
                    "    FOREIGN KEY (Username) REFERENCES user(username),\n" +
                    "    FOREIGN KEY (GameName) REFERENCES game(name)\n" +
                    ");";

            statement.executeUpdate(creareUser);
            statement.executeUpdate(creareGame);
            statement.executeUpdate(crearePlayed);
            statement.executeUpdate(creareReview);
            statement.executeUpdate(creareAudit);

        } catch (Exception e) {
            System.out.println("Error creating tables");
        }
    }

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return a Connection object to the database, or null if a connection could not be established
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
        } catch (Exception e) {
            System.out.println("Error creating connection");
        }
        return null;
    }
}
