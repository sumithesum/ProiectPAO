package InputsReaders;

public abstract class Outputs {
    String path;
    public Outputs(String path){
        this.path = path;
    }
    public abstract void CSVOutput();
    public abstract void JsonOutput();
    public abstract void MyOutput();
}
