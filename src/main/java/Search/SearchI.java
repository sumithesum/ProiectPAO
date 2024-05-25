package Search;
import Clase.Game;
import Clase.Review;
import Clase.User;

import java.util.List;

public interface SearchI {
     public  User searchUser(String username);
     public Game searchGame(String username);
     public List<Review> searchReview(String gameName);
}
