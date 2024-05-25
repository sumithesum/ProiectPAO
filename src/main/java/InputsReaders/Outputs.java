package InputsReaders;

/**
 * The Outputs abstract class provides a blueprint for writing output data to different formats.
 * Subclasses need to implement methods for writing data to CSV, JSON, and custom output formats.
 */
public abstract class Outputs {

    protected String path;

    /**
     * Constructs an Outputs object with the specified path.
     *
     * @param path the path where the output data will be written
     */
    public Outputs(String path) {
        this.path = path;
    }

    /**
     * Writes output data to a CSV file.
     */
    public abstract void CSVOutput();

    /**
     * Writes output data to a JSON file.
     */
    public abstract void JsonOutput();

    /**
     * Writes output data to a custom format file.
     */
    public abstract void MyOutput();
}
