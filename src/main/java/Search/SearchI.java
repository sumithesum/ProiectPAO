package Search;
import Momentan.Game;
import Momentan.Review;
import Momentan.User;

import java.util.List;

public interface SearchI {
     public  User searchUser(String username);
     public Game searchGame(String username);
     public List<Review> searchReview(String gameName);
}
