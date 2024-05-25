package MeniuPrincipal;

import InputsReaders.*;
import LoginRegister.LoginRegister;
import Momentan.Game;
import Momentan.Review;
import Momentan.User;
import Search.Search;
import Update.UpdateReview;
import Update.UpdatesUser;
import utils.Utils;
import Update.UpdateGame;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;

public class Meniu implements MeniuI {

    private User currentUser;

    @Override
    public User LoginMeniu() {
        LoginRegister loginRegister = new LoginRegister();
        Scanner scanner = new Scanner(in);
        boolean retry = true;
        while (retry) {

            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            String option = scanner.nextLine();

            switch (option.toLowerCase()) {
                case "1":
                case "login":
                    System.out.println("Starting login");
                    currentUser = loginRegister.login();
                    if (currentUser != null) {
                        retry = false;
                    } else {
                        System.out.println("Invalid option");
                        System.out.println("Would u like to return> y/n");
                        String retryOption = scanner.nextLine();
                        if (retryOption.toLowerCase().equals("n")) {
                            retry = false;
                        }
                    }
                    break;

                case "2":
                case "register":
                    System.out.println("Register");
                    currentUser = loginRegister.register();

                    if (currentUser != null) {
                        retry = false;
                    } else {
                        System.out.println("Invalid option");
                        System.out.println("Would u like to return> y/n");
                        String retryOption = scanner.nextLine();
                        if (retryOption.toLowerCase().equals("n")) {
                            retry = false;
                        }
                    }
                    break;

                case "3":
                case "exit":
                    System.out.println("Goddbye!");
                    retry = false;
                    break;

                default:
                    System.out.println("Invalid option");
                    System.out.println("Would u like to return> y/n");
                    String retryOption = scanner.nextLine();
                    if (retryOption.toLowerCase().equals("n")) {
                        retry = false;
                    }
                    break;
            }
        }

        return currentUser;
    }

    @Override
    public void AdminMenu() {
        LoginRegister loginRegister = new LoginRegister();
        Scanner scanner = new Scanner(in);
        boolean retry = true;
        UserInputsUsers inputsUsers = new UserInputsUsers();
        InputsGame inputsGame = new InputsGame();
        Search search = new Search();
        UpdatesUser updatesUser = new UpdatesUser();
        String path, name;
        Outputs outputs;
        Game game;
        UpdateReview updateReview = new UpdateReview();
        List<Review> reviews1, reviews;
        InputReview inputReview = new InputReview();
        while (retry) {
            System.out.println();
            System.out.println();

            System.out.println("Admin Menu");

            System.out.println("1. Search Games");
            System.out.println("2. Add Game");
            System.out.println("3. Comment on Game , rate Game");
            System.out.println("4. Delete review on Game");
            System.out.println("5. Add Game to played games list");
            System.out.println("6. Delete Game");
            System.out.println("7. Search reviews on Game");
            System.out.println("8. Search reviews of a user");
            System.out.println("9. Promote User");
            System.out.println("10. Demote User");
            System.out.println("11. Delete User");
            System.out.println("12. Add Users using CSV");
            System.out.println("13. Output Users to CSV");
            System.out.println("14. Add Users using JSON");
            System.out.println("15. Output Users to JSON");
            System.out.println("16. Add Users using MyInput");
            System.out.println("17. Output Users to MyOutput");
            System.out.println("18. Add review using CSV");
            System.out.println("19. Output review to CSV");
            System.out.println("20. Add review using JSON");
            System.out.println("21. Output review to JSON");
            System.out.println("22. Add review using MyInput");
            System.out.println("23. Output review to MyOutput");
            System.out.println("25. Exit");

            String option = scanner.nextLine();

            switch (option.toLowerCase()) {
                case "1":
                case "search games":
                    System.out.println("Enter the name of the game you want to search");
                    name = scanner.nextLine();
                    game = search.searchGame(name);
                    if (game != null) {
                        System.out.println("Game found!!!");
                        game.printInfo();
                    }
                    break;
                case "2":
                case "add game":
                    game = Utils.readGame();
                    if (game != null)
                        inputsGame.GameInput(game);
                    break;
                case "3":
                case "comment on game":
                case "rate game":
                case "review on game":
                    System.out.println("Enter the name of the game you want to rate");
                    name = scanner.nextLine();
                    game = search.searchGame(name);
                    if (game == null) {
                        System.out.println("Game not found");
                        break;
                    }
                    if (search.searchReviewUserGame(currentUser.getUsername(), game.getName()) != null) {
                        System.out.println("You already reviewed this game");
                        break;
                    }
                    System.out.println("Enter the review you want to give to the game");
                    String review = scanner.nextLine();
                    System.out.println("Enter the rating you want to give");
                    String rating = scanner.nextLine();
                    InputReview.inputReview(game, currentUser, review, rating);
                    break;
                case "4":
                case "delete review on game":
                    System.out.println("Enter the name of the game you want to delete the review from");
                    name = scanner.nextLine();
                    game = search.searchGame(name);
                    if (game == null) {
                        System.out.println("Game not found");
                        break;
                    }
                    System.out.println("Enter the name of the user who made the review");
                    String userName = scanner.nextLine();
                    if (search.searchUser(userName) == null) {
                        System.out.println("User not found");
                        break;
                    }
                    Review review1 = search.searchReviewUserGame(userName, game.getName());
                    if (review1 == null) {
                        System.out.println("Review not found");
                        break;
                    }
                    updateReview.deleteReview(review1);
                    UpdateGame.updateRating(game);
                    break;

                case "5":
                case "add game to played games list":
                    System.out.println("Enter the name of the game you want to add to your played games list");
                    break;
                case "6":
                case "delete game":
                    System.out.println("Enter the name of the game you want to delete");
                    name = scanner.nextLine();
                    game = search.searchGame(name);
                    if (game == null) {
                        System.out.println("Game not found");
                        break;
                    }
                    reviews1 = search.searchReview(name);
                    if (reviews1 != null)
                        for (Review review2 : reviews1)
                            updateReview.deleteReview(review2);
                    UpdateGame.delete(name);

                    break;
                case "7":
                case "search reviews on game":
                    System.out.println("Enter the name of the game you want to search reviews for");
                    name = scanner.nextLine();
                    reviews = search.searchReview(name);
                    if (reviews != null)
                        for (Review review2 : reviews) {
                            review2.printReview();
                        }
                    if (reviews == null)
                        System.out.println("No reviews found");
                    else if (reviews.isEmpty())
                        System.out.println("No reviews found");
                {
                }
                break;
                case "8":
                case "search reviews of a user":
                    System.out.println("Enter the name of the user you want to search reviews for");
                    name = scanner.nextLine();
                    reviews1 = search.searchReviewUser(name);
                    if (reviews1 != null)
                        for (Review review2 : reviews1)
                            review2.printReview();
                    if (reviews1 == null)
                        System.out.println("No reviews found");
                    if (reviews1.isEmpty())
                        System.out.println("No reviews found");

                    break;
                case "9":
                case "promote user":
                    System.out.println("Enter the name of the user you want to promote");
                    name = scanner.nextLine();
                    updatesUser.promoteUser(name);
                    break;
                case "10":
                case "demote user":
                    System.out.println("Enter the name of the user you want to demote");
                    name = scanner.nextLine();
                    updatesUser.demoteUser(name);
                    break;
                case "11":
                case "delete user":
                    System.out.println("Enter the name of the user you want to delete");
                    name = scanner.nextLine();
                    reviews1 = search.searchReviewUser(name);
                    if (reviews1 != null)
                        for (Review review2 : reviews1)
                            updateReview.deleteReview(review2);
                    updatesUser.deleteUser(name);
                    break;
                case "12":
                case "add users using csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    inputsUsers.CSVInput(path);
                    break;
                case "13":
                case "output users to csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    outputs = new OutputUsers(path);
                    outputs.CSVOutput();
                    break;
                case "14":
                case "add users using  json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    inputsUsers.JsonInput(path);
                    break;
                case "15":
                case "output users to json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    outputs = new OutputUsers(path);
                    outputs.JsonOutput();
                    break;
                case "16":
                case "add users using myinput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    inputsUsers.MyInput(path);
                    break;
                case "17":
                case "output users to myoutput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    outputs = new OutputUsers(path);
                    outputs.MyOutput();
                    break;
                case "18":
                case "add review using csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    inputReview.CSVInput(path);
                    break;
                case "19":
                case "output review to csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    outputs = new OutputReview(path);
                    outputs.CSVOutput();
                    break;
                case "20":
                case "add review using json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    inputReview.JsonInput(path);
                    break;
                case "21":
                case "output review to json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    outputs = new OutputReview(path);
                    outputs.JsonOutput();
                    break;
                case "22":
                case "add review using myinput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    inputReview.MyInput(path);
                    break;
                case "23":
                case "output review to myoutput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    outputs = new OutputReview(path);
                    outputs.MyOutput();
                    break;
                case "24":
                case "exit":
                    System.out.println("Goodbye!");
                    retry = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    @Override
    public void UserMenu() {

    }
}
