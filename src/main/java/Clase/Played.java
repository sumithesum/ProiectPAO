package Clase;

import lombok.Getter;

public class Played {
    @Getter
    private String gameName;
    @Getter
    private String userName;


    public Played(String gameName, String userName) {
        this.gameName = gameName;
        this.userName = userName;
    }
    public void printPlayed(){
        System.out.println("Game: " + gameName + " User: " + userName);
    }
}
