import MeniuPrincipal.Meniu;
import Momentan.User;

public class Main {
    public static void main(String[] args) {
        User currentUser  = null;
        Meniu meniu = new Meniu();
        currentUser = meniu.LoginMeniu();
        if (currentUser == null)
            return;
        if (currentUser.isAdmin())
            meniu.AdminMenu();
        else
            meniu.UserMenu();


    }
}
