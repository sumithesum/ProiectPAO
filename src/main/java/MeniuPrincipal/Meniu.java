package MeniuPrincipal;

import InputsReaders.Inputs;
import LoginRegister.LoginRegister;
import Momentan.User;

import java.nio.file.LinkPermission;
import java.util.Scanner;

public class Meniu implements MeniuI {
    User currentUser = null;

    @Override
    public User LoginMeniu() {
        LoginRegister loginRegister = new LoginRegister();
        Scanner scanner = new Scanner(System.in);
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
                        System.out.println("Would u like to retry? y/n");
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
                        System.out.println("Would u like to retry? y/n");
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
                    System.out.println("Would u like to retry? y/n");
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
        Scanner scanner = new Scanner(System.in);
        boolean retry = true;
        Inputs input = new Inputs();
        while (retry) {

            System.out.println("1. Search Games");
            System.out.println("2. Add Game");
            System.out.println("3. Delete Game");
            System.out.println("4. Update Game");
            System.out.println("5. Promote User");
            System.out.println("6. Demote User");
            System.out.println("7. Add Users using CSV");
            System.out.println("8. Exit");

            String option = scanner.nextLine();

            switch (option.toLowerCase()){
                case "1":
                case "search games":
                    // search games
                    break;
                case "2":
                case "add game":

                    break;
                case "4":
                case "update game":
                    // search games
                    break;
                case "5":
                case "promote user":
                    System.out.println("Enter the name of the user you want to promote");
                    String name = scanner.nextLine();
                    currentUser.promoteUser(new User(name, "password"));
                    break;
                case "6":
                case "demote user":
                    // search games
                    break;
                case "7":
                case "add users using csv":
                    System.out.println("Enter the path of the csv file");
                    String path = scanner.nextLine();
                    input.CSVInputUser(path, "user");
                    break;
                case "8":
                case "exit":
                    System.out.println("Goddbye!");
                    retry = false;
                    break;
            }
        }
    }

    @Override
    public void UserMenu() {

    }
}
