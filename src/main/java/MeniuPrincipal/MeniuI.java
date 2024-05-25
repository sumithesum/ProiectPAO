package MeniuPrincipal;


import Momentan.Admin;
import Momentan.User;

import javax.swing.plaf.PanelUI;

public interface MeniuI {
    public User LoginMeniu();
    public void AdminMenu(Admin currentUser);
    public void UserMenu(User currentUser);

}
