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


}

