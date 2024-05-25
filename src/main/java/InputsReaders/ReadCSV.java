package InputsReaders;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The ReadCSV class provides a method to read data from a CSV file and return it as a list of string arrays.
 */
public class ReadCSV {

    /**
     * Reads data from a CSV file at the specified path and returns it as a list of string arrays.
     * Each array represents a row in the CSV file.
     *
     * @param path the path to the CSV file
     * @return a list of string arrays representing the rows in the CSV file
     * @throws FileNotFoundException if the file at the specified path is not found
     */
    public static List<String[]> readCSV(String path) throws FileNotFoundException {
        try {
            FileReader filereader = new FileReader(path);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecords;
            List<String[]> allRecords = new ArrayList<>();

            while ((nextRecords = csvReader.readNext()) != null) {
                allRecords.add(nextRecords);
            }

            return allRecords;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
