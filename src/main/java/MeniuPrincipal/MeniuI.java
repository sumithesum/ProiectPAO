package MeniuPrincipal;


import Clase.Admin;
import Clase.User;

public interface MeniuI {
    public User LoginMeniu();
    public void AdminMenu(Admin currentUser);
    public void UserMenu(User currentUser);

}
