package InputsReaders;


import com.opencsv.CSVReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
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
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
