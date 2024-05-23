package Update;

public abstract class Update implements UpdatesI{
    public abstract void promoteUser(String name);
    public abstract void demoteUser(String name);
    public abstract void deleteUser(String name);
}
