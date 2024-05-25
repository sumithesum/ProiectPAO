package Clase;

import lombok.Getter;

/**
 * The Played class represents a record of a game played by a user.
 */
public class Played {

    @Getter
    private String gameName;

    @Getter
    private String userName;

    /**
     * Constructs a Played object with the specified game name and user name.
     *
     * @param gameName the name of the game
     * @param userName the name of the user who played the game
     */
    public Played(String gameName, String userName) {
        this.gameName = gameName;
        this.userName = userName;
    }

    /**
     * Prints the details of the game played to the standard output.
     */
    public void printPlayed() {
        System.out.println("Game: " + gameName + " User: " + userName);
    }
}
