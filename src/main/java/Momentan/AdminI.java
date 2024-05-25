package Momentan;

public interface AdminI extends UserI {
    public abstract void promoteUser(String name);
    public abstract void demoteUser(String name);
    public abstract void deleteUser(String name);

}
