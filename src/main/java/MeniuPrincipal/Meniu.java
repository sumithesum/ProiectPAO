package MeniuPrincipal;

import Audit.Audit;
import Audit.InputAudit;
import InputsReaders.*;
import LoginRegister.LoginRegister;
import Momentan.Game;
import Momentan.Played;
import Momentan.Review;
import Momentan.User;
import Search.Search;
import Update.UpdatePlayed;
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
        Scanner scanner = new Scanner(in);
        boolean retry = true;
        UserInputsUsers inputsUsers = new UserInputsUsers();
        InputsGame inputsGame = new InputsGame();
        Search search = new Search();
        UpdatesUser updatesUser = new UpdatesUser();
        String path, name;
        Outputs outputs;
        Game game;
        InputPlayed in = new InputPlayed();
        UpdateReview updateReview = new UpdateReview();
        List<Review> reviews1, reviews;
        InputReview inputReview = new InputReview();
        List<Played> playedList;
        while (retry) {
            System.out.println();
            System.out.println();

            System.out.println("Admin Menu:");

            System.out.println("1. Search Games");
            System.out.println("2. Add Game");
            System.out.println("3. Comment on Game , rate Game");
            System.out.println("4. Delete review on Game");
            System.out.println("5. Add Game to played games list");
            System.out.println("6. Remove Game from played games list");
            System.out.println("7. Show games played by a user");
            System.out.println("8. Delete Game");
            System.out.println("9. Search reviews on Game");
            System.out.println("10. Search reviews of a user");
            System.out.println("11. Promote User");
            System.out.println("12. Demote User");
            System.out.println("13. Delete User");
            System.out.println("14. Add Users using CSV");
            System.out.println("15. Output Users to CSV");
            System.out.println("16. Add Users using JSON");
            System.out.println("17. Output Users to JSON");
            System.out.println("18. Add Users using MyInput");
            System.out.println("19. Output Users to MyOutput");
            System.out.println("20. Add review using CSV");
            System.out.println("21. Output review to CSV");
            System.out.println("22. Add review using JSON");
            System.out.println("23. Output review to JSON");
            System.out.println("24. Add review using MyInput");
            System.out.println("25. Output review to MyOutput");
            System.out.println("26. Add game using CSV");
            System.out.println("27. Output game to CSV");
            System.out.println("28. Add game using JSON");
            System.out.println("29. Output game to JSON");
            System.out.println("30. Add game using MyInput");
            System.out.println("31. Output game to MyOutput");
            System.out.println("32. Read audit of a user");
            System.out.println("33. Exit");

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
                    InputAudit.inputAudit("search games " + name, currentUser.getUsername());
                    break;
                case "2":
                case "add game":
                    game = Utils.readGame();
                    if (game != null)
                        inputsGame.GameInput(game);
                    InputAudit.inputAudit("add game " + game.PrintLine(), currentUser.getUsername());
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
                    InputAudit.inputAudit("review on game " + game.getName() + " " + review + " " + rating, currentUser.getUsername());
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
                    InputAudit.inputAudit("delete review on game " + game.getName() + " " + review1.getComment() + " " + review1.getRating(), currentUser.getUsername());
                    break;

                case "5":
                case "add game to played games list":
                    System.out.println("Enter the name of the game you want to add to your played games list");
                    name = scanner.nextLine();
                    game = search.searchGame(name);
                    if (game == null) {
                        System.out.println("Game not found");
                        break;
                    }
                    System.out.println("Enter the name of the user who played the game");
                    String userName1 = scanner.nextLine();
                    if (search.searchUser(userName1) == null) {
                        System.out.println("User not found");
                        break;
                    }
                    in.inputPlayed(game.getName(), userName1);
                    InputAudit.inputAudit("add game to played games list " + game.getName() + " " + userName1, currentUser.getUsername());
                    break;
                case "6":
                case "remove game from played games list":
                    System.out.println("Enter the name of the game you want to remove from your played games list");
                    name = scanner.nextLine();
                    game = search.searchGame(name);
                    if (game == null) {
                        System.out.println("Game not found");
                        break;
                    }
                    System.out.println("Enter the name of the user who played the game");
                    String userName2 = scanner.nextLine();
                    if (search.searchUser(userName2) == null) {
                        System.out.println("User not found");
                        break;
                    }
                    Played played = search.searchPlayed(game.getName(), userName2);
                    if (played == null) {
                        System.out.println("Game not found in played games list of the user");
                        break;
                    }
                    UpdatePlayed.deletePlayed(game.getName(), userName2);
                    System.out.println("Game removed from played games list");
                    InputAudit.inputAudit("remove game from played games list " + game.getName() + " " + userName2, currentUser.getUsername());
                    break;
                case "7":
                case "show games played by a user":
                    System.out.println("Enter the name of the user you want to see the played games list of");
                    name = scanner.nextLine();
                    User user = search.searchUser(name);
                    if (user == null) {
                        System.out.println("User not found");
                        break;
                    }
                    playedList = search.searchPlayedUser(name);
                    if (playedList != null)
                        for (Played played1 : playedList)
                            played1.printPlayed();
                    if (playedList == null)
                        System.out.println("No games found");
                    else if (playedList.isEmpty())
                        System.out.println("No games found");
                    InputAudit.inputAudit("show games played by a user " + name, currentUser.getUsername());
                    break;
                case "8":
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


                     playedList = search.searchPlayedGame(name);
                    if (playedList != null)
                        for (Played played1 : playedList)
                            UpdatePlayed.deletePlayed(played1.getGameName(), played1.getUserName());
                    UpdateGame.delete(name);
                    InputAudit.inputAudit("delete game " + name, currentUser.getUsername());
                    break;
                case "9":
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
                    InputAudit.inputAudit("search reviews on game " + name, currentUser.getUsername());
                break;
                case "10":
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
                    InputAudit.inputAudit("search reviews of a user " + name, currentUser.getUsername());
                    break;
                case "11":
                case "promote user":
                    System.out.println("Enter the name of the user you want to promote");
                    name = scanner.nextLine();
                    updatesUser.promoteUser(name);
                    InputAudit.inputAudit("promote user " + name, currentUser.getUsername());
                    break;
                case "12":
                case "demote user":
                    System.out.println("Enter the name of the user you want to demote");
                    name = scanner.nextLine();
                    updatesUser.demoteUser(name);
                    InputAudit.inputAudit("demote user " + name, currentUser.getUsername());
                    break;
                case "13":
                case "delete user":
                    System.out.println("Enter the name of the user you want to delete");
                    name = scanner.nextLine();
                    reviews1 = search.searchReviewUser(name);
                    if (reviews1 != null)
                        for (Review review2 : reviews1)
                            updateReview.deleteReview(review2);
                    playedList = search.searchPlayedUser(name);
                    if (playedList != null)
                        for (Played played1 : playedList)
                            UpdatePlayed.deletePlayed(played1.getGameName(), played1.getUserName());
                    updatesUser.deleteUser(name);
                    InputAudit.inputAudit("delete user " + name, currentUser.getUsername());
                    break;
                case "14":
                case "add users using csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    inputsUsers.CSVInput(path);
                    InputAudit.inputAudit("add users using csv " + path, currentUser.getUsername());
                    break;
                case "15":
                case "output users to csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    outputs = new OutputUsers(path);
                    outputs.CSVOutput();
                    InputAudit.inputAudit("output users to csv " + path, currentUser.getUsername());
                    break;
                case "16":
                case "add users using  json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    inputsUsers.JsonInput(path);
                    InputAudit.inputAudit("add users using json " + path, currentUser.getUsername());
                    break;
                case "17":
                case "output users to json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    outputs = new OutputUsers(path);
                    outputs.JsonOutput();
                    InputAudit.inputAudit("output users to json " + path, currentUser.getUsername());
                    break;
                case "18":
                case "add users using myinput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    inputsUsers.MyInput(path);
                    InputAudit.inputAudit("add users using myinput " + path, currentUser.getUsername());
                    break;
                case "19":
                case "output users to myoutput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    outputs = new OutputUsers(path);
                    outputs.MyOutput();
                    InputAudit.inputAudit("output users to myoutput " + path, currentUser.getUsername());
                    break;
                case "20":
                case "add review using csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    inputReview.CSVInput(path);
                    InputAudit.inputAudit("add review using csv " + path, currentUser.getUsername());
                    break;
                case "21":
                case "output review to csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    outputs = new OutputReview(path);
                    outputs.CSVOutput();
                    InputAudit.inputAudit("output review to csv " + path, currentUser.getUsername());
                    break;
                case "22":
                case "add review using json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    inputReview.JsonInput(path);
                    InputAudit.inputAudit("add review using json " + path, currentUser.getUsername());
                    break;
                case "23":
                case "output review to json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    outputs = new OutputReview(path);
                    outputs.JsonOutput();
                    InputAudit.inputAudit("output review to json " + path, currentUser.getUsername());
                    break;
                case "24":
                case "add review using myinput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    inputReview.MyInput(path);
                    InputAudit.inputAudit("add review using myinput " + path, currentUser.getUsername());
                    break;
                case "25":
                case "output review to myoutput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    outputs = new OutputReview(path);
                    outputs.MyOutput();
                    InputAudit.inputAudit("output review to myoutput " + path, currentUser.getUsername());
                    break;
                case "26":
                case "add game using csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    inputsGame.CSVInput(path);
                    InputAudit.inputAudit("add game using csv " + path, currentUser.getUsername());
                    break;
                case "27":
                case "output game to csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    outputs = new OutputGame(path);
                    outputs.CSVOutput();
                    InputAudit.inputAudit("output game to csv " + path, currentUser.getUsername());
                    break;
                case "28":
                case "add game using json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    inputsGame.JsonInput(path);
                    InputAudit.inputAudit("add game using json " + path, currentUser.getUsername());
                    break;
                case "29":
                case "output game to json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    outputs = new OutputGame(path);
                    outputs.JsonOutput();
                    InputAudit.inputAudit("output game to json " + path, currentUser.getUsername());
                    break;
                case "30":
                case "add game using myinput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    inputsGame.MyInput(path);
                    InputAudit.inputAudit("add game using myinput " + path, currentUser.getUsername());
                    break;
                case "31":
                case "output game to myoutput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    outputs = new OutputGame(path);
                    outputs.MyOutput();
                    InputAudit.inputAudit("output game to myoutput " + path, currentUser.getUsername());
                    break;
                case "32":
                case "read audit of a user":
                    System.out.println("Enter the name of the user you want to read the audit of");
                    name = scanner.nextLine();
                    InputAudit.inputAudit("read audit of a user " + name, currentUser.getUsername());
                    List<Audit> auditList = InputAudit.readAudit(name);

                    if (auditList != null)
                        for (Audit audit : auditList)
                            audit.printAudit();
                    if (auditList == null)
                        System.out.println("No commands found");
                    else if (auditList.isEmpty())
                        System.out.println("No commands found");
                    break;
                case "33":
                case "exit":
                    System.out.println("Goodbye!");
                    retry = false;
                    InputAudit.inputAudit("exit", currentUser.getUsername());
                    break;
                default:
                    System.out.println("Invalid option");
                    InputAudit.inputAudit("invalid option " + option, currentUser.getUsername());
                    break;
            }
        }
    }

    @Override
    public void UserMenu()  {
        Scanner scanner = new Scanner(in);
        boolean retry = true;
        Search search = new Search();
        String  name;
        Game game;
        InputPlayed in = new InputPlayed();
        UpdateReview updateReview = new UpdateReview();
        List<Review> reviews1, reviews;
        List<Played> playedList;
        while (retry) {
            System.out.println();
            System.out.println();

            System.out.println("Admin Menu:");

            System.out.println("1. Search Games");
            System.out.println("2. Comment on Game , rate Game");
            System.out.println("3. Delete review on Game");
            System.out.println("4. Add Game to played games list");
            System.out.println("5. Remove Game from played games list");
            System.out.println("6. Show games played by a user");
            System.out.println("7. Search reviews on Game");
            System.out.println("8. Search reviews of a user");
            System.out.println("9. Exit");

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
                case "3":
                case "delete review on game":
                    System.out.println("Enter the name of the game you want to delete the review from");
                    name = scanner.nextLine();
                    game = search.searchGame(name);
                    if (game == null) {
                        System.out.println("Game not found");
                        break;
                    }

                    Review review1 = search.searchReviewUserGame(currentUser.getUsername(), game.getName());
                    if (review1 == null) {
                        System.out.println("Review not found");
                        break;
                    }
                    updateReview.deleteReview(review1);
                    UpdateGame.updateRating(game);
                    break;

                case "4":
                case "add game to played games list":
                    System.out.println("Enter the name of the game you want to add to your played games list");
                    name = scanner.nextLine();
                    game = search.searchGame(name);
                    if (game == null) {
                        System.out.println("Game not found");
                        break;
                    }

                    in.inputPlayed(game.getName(), currentUser.getUsername());
                    break;
                case "5":
                case "remove game from played games list":
                    System.out.println("Enter the name of the game you want to remove from your played games list");
                    name = scanner.nextLine();
                    game = search.searchGame(name);
                    if (game == null) {
                        System.out.println("Game not found");
                        break;
                    }

                    Played played = search.searchPlayed(game.getName(), currentUser.getUsername());
                    if (played == null) {
                        System.out.println("Game not found in played games list of the user");
                        break;
                    }
                    UpdatePlayed.deletePlayed(game.getName(), currentUser.getUsername());
                    System.out.println("Game removed from played games list");
                    break;
                case "6":
                case "show games played by a user":
                    System.out.println("Enter the name of the user you want to see the played games list of");
                    name = scanner.nextLine();
                    User user = search.searchUser(name);
                    if (user == null) {
                        System.out.println("User not found");
                        break;
                    }
                    playedList = search.searchPlayedUser(name);
                    if (playedList != null)
                        for (Played played1 : playedList)
                            played1.printPlayed();
                    if (playedList == null)
                        System.out.println("No games found");
                    else if (playedList.isEmpty())
                        System.out.println("No games found");
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
}
