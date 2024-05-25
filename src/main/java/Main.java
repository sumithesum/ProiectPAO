import Database.Database;
import MeniuPrincipal.Meniu;
import Momentan.Admin;
import Momentan.User;

public class Main {
    public static void main(String[] args) {
        Database.creareTabele();
        Meniu meniu = new Meniu();
        User loginUser = meniu.LoginMeniu();
        if (loginUser == null)
            return;
        if (loginUser.isAdmin()) {
             Admin currentUser = new Admin(loginUser.getUsername(), loginUser.getPassword());
            meniu.AdminMenu(currentUser);
        } else {
            meniu.UserMenu(loginUser);
        }

    }
}
