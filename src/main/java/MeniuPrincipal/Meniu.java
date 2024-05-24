package MeniuPrincipal;

import InputsReaders.InputsGame;
import InputsReaders.OutputUsers;
import InputsReaders.Outputs;
import InputsReaders.UserInputsUsers;
import LoginRegister.LoginRegister;
import Momentan.Game;
import Momentan.User;
import Search.Search;
import Update.UpdatesUser;
import utils.Utils;
import Update.UpdateGame;
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

        while (retry) {
            System.out.println();
            System.out.println();

            System.out.println("Admin Menu");

            System.out.println("1. Search Games");
            System.out.println("2. Add Game");
            System.out.println("3. Comment on Game , rate Game");
            System.out.println("4. Add Game to played games list");
            System.out.println("5. Delete Game");
            System.out.println("6. Update Game");
            System.out.println("7. Promote User");
            System.out.println("8. Demote User");
            System.out.println("9. Add Users using CSV");
            System.out.println("10. Output Users to CSV");
            System.out.println("11. Add Users using JSON");
            System.out.println("12. Output Users to JSON");
            System.out.println("13. Add Users using MyInput");
            System.out.println("14. Output Users to MyOutput");
            System.out.println("15. Exit");

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
                    System.out.println("Enter the review you want to give to the game");
                    String review = scanner.nextLine();
                    System.out.println("Enter the rating you want to give");
                    String rating = scanner.nextLine();
                    UpdateGame.inputReview(game, currentUser, review, rating);
                    break;
                case "4":
                case "add game to played games list":
                    System.out.println("Enter the name of the game you want to add to your played games list");
                    break;
                case "5":
                case "delete game":
                    System.out.println("Enter the name of the game you want to delete");
                    break;
                case "6":
                case "update game":
                    // search games
                    break;
                case "7":
                case "promote user":
                    System.out.println("Enter the name of the user you want to promote");
                    name = scanner.nextLine();
                    updatesUser.promoteUser(name);
                    break;
                case "8":
                case "demote user":
                    System.out.println("Enter the name of the user you want to demote");
                    name = scanner.nextLine();
                    updatesUser.demoteUser(name);
                    break;
                case "9":
                case "add users using csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    inputsUsers.CSVInput(path);
                    break;
                case "10":
                case "output users to csv":
                    System.out.println("Enter the path of the csv file");
                    path = scanner.nextLine();
                    outputs = new OutputUsers(path);
                    outputs.CSVOutput();
                    break;
                case "11":
                case "add users using  json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    inputsUsers.JsonInput(path);
                    break;
                case "12":
                case "output users to json":
                    System.out.println("Enter the path of the json file");
                    path = scanner.nextLine();
                    outputs = new OutputUsers(path);
                    outputs.JsonOutput();
                    break;
                case "13":
                case "add users using myinput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    inputsUsers.MyInput(path);
                    break;
                case "14":
                case "output users to myoutput":
                    System.out.println("Enter the path of the file");
                    path = scanner.nextLine();
                    outputs = new OutputUsers(path);
                    outputs.MyOutput();
                    break;
                case "15":
                case "exit":
                    System.out.println("Goddbye!");
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
