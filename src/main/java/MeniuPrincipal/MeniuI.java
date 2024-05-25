package MeniuPrincipal;


import Clase.Admin;
import Clase.User;

/**
 * Interface for the main menu.

 */

public interface MeniuI {
    /**
     * Displays the login menu.
     *
     * @return the logged in user
     */
    public User LoginMeniu();
    /**
     * Displays the Admin menu.
     *
     */
    public void AdminMenu(Admin currentUser);
   /**
     * Displays the User menu.
     *
     */
    public void UserMenu(User currentUser);

}
