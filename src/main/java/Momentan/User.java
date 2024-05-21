package Momentan;

import lombok.Getter;

public class User {
    @Getter
    private String username;
    private String password;
    @Getter
    private boolean admin;

    public User(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.admin = false;
    }
    public User() {
        this.username = null;
        this.password = null;
        this.admin = false;
    }


    public void promoteUser(User user) {
        if (user.getUsername() != null) {
            if (!user.isAdmin() && this.isAdmin()) {
                user.admin = true;
                System.out.println("User " + user.getUsername() + " has been promoted to admin. Inca nu dar trb sa o facem");
            }else if (user.isAdmin() && this.isAdmin()) {
                System.out.println("User " + user.getUsername() + " is already an admin.");
            }else if (!this.isAdmin()){
                System.out.println("You are not an admin , so u cannot make another user an admin.");
            }
        }
    }
}

