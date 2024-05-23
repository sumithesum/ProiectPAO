package Momentan;

public interface UserI {
    public abstract void wishlistGame(String name);
    public abstract void buyGame(String name);
    public abstract void rateGame(String name, int rating);
    public abstract void commentGame(String name, String comment);

}
