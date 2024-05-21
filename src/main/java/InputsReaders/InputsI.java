package InputsReaders;

public interface InputsI {
    public void CSVInputUser(String path,String type);
    public void JsonInputUser(String path,String type);
    public boolean UserInput(String name, String password);
}
