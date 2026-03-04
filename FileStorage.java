import java.io.*;
import java.util.*;

public class FileStorage {

    private static final String FILE = "EnergyHistory.txt";

    public static void saveRecord(double capacity,
                                  double maxCharge,
                                  double maxDischarge,
                                  double costWith,
                                  double costWithout,
                                  double savings) {

        try (FileWriter fw = new FileWriter(FILE, true)) {

            fw.write(capacity + "," + maxCharge + "," + maxDischarge + ","
                    + costWith + "," + costWithout + "," + savings + ","
                    + new Date() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> readRecords() {

        List<String[]> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {

            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split(","));
            }

        } catch (Exception e) {
            // file may not exist (first time)
        }

        return list;
    }
}