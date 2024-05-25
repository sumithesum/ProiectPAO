package InputsReaders;

/**
 * The Inputs abstract class provides a blueprint for reading different types of input files.
 * Subclasses need to implement methods for reading CSV, JSON, and custom input formats.
 */
public abstract class Inputs {

    /**
     * Reads input from a CSV file at the specified path.
     *
     * @param path the path to the CSV file
     */
    public abstract void CSVInput(String path);

    /**
     * Reads input from a JSON file at the specified path.
     *
     * @param path the path to the JSON file
     */
    public abstract void JsonInput(String path);

    /**
     * Reads input from a custom input format file at the specified path.
     *
     * @param path the path to the custom format file
     */
    public abstract void MyInput(String path);
}
