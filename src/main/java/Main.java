import Database.Database;
import MeniuPrincipal.Meniu;
import Clase.Admin;
import Clase.User;

/**
 * The Main class contains the main method that starts the program.
 */
public class Main {
    /**
     * The main method that starts the program.
     *
     * @param args the command line arguments
     */
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
