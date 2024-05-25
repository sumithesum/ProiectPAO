package Clase;

import lombok.Getter;

/**
 * The Review class represents a review of a game by a user, including the game's name,
 * the user's name, the rating given by the user, and the user's comment.
 */
public class Review {

    @Getter
    private String gameName;

    @Getter
    private String userName;

    @Getter
    private String rating;

    @Getter
    private String comment;

    /**
     * Constructs a Review object with the specified game name, user name, rating, and comment.
     *
     * @param gameName the name of the game being reviewed
     * @param userName the name of the user writing the review
     * @param rating the rating given by the user
     * @param comment the comment provided by the user
     */
    public Review(String gameName, String userName, String rating, String comment) {
        this.gameName = gameName;
        this.userName = userName;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Prints the review details to the standard output.
     */
    public void printReview() {
        System.out.println("Game: " + gameName + "\nUser: " + userName + "\nRating: " + rating + "\nComment: " + comment + "\n");
    }
}
