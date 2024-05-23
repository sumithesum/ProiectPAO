package Momentan;

import lombok.Getter;

public class User implements UserI {
    @Getter
    private String username;
    @Getter
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



    @Override
    public void wishlistGame(String name) {

    }

    @Override
    public void buyGame(String name) {

    }

    @Override
    public void rateGame(String name, int rating) {

    }

    @Override
    public void commentGame(String name, String comment) {

    }
}

