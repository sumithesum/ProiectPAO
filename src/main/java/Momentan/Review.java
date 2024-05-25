package Momentan;

import lombok.Getter;

import javax.swing.plaf.PanelUI;

public class Review {
    @Getter
    private String gameName;
    @Getter
    private String userName;
    @Getter
    private String rating;
    @Getter
    private String comment;

    public Review(String gameName, String userName, String rating, String comment) {
        this.gameName = gameName;
        this.userName = userName;
        this.rating = rating;
        this.comment = comment;
    }
    public void printReview(){
        System.out.println("Game: " + gameName + "\nUser: " + userName + "\nRating: " + rating + "\nComment: " + comment + "\n");
    }


}
