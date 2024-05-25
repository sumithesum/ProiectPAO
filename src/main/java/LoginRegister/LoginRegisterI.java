package LoginRegister;

import Clase.User;

/**
 * Interface for login and registration functionality.
 *
 */
public interface LoginRegisterI {

    /**
     * Logs in a user.
     *
     * @return the logged in user
     */
    User login();

    /**
     * Registers a new user.
     *
     * @return the registered user
     */
    User register();
}