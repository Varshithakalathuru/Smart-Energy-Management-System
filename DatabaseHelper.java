import java.sql.*;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:sqlite:EnergyData.db";

    public static void initialize() {

        try (Connection conn = DriverManager.getConnection(DB_URL)) {

            String createTable = "CREATE TABLE IF NOT EXISTS EnergyRecords (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "capacity DOUBLE," +
                    "maxCharge DOUBLE," +
                    "maxDischarge DOUBLE," +
                    "costWith DOUBLE," +
                    "costWithout DOUBLE," +
                    "savings DOUBLE," +
                    "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

            Statement stmt = conn.createStatement();
            stmt.execute(createTable);

            System.out.println("Database Initialized");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveRecord(double capacity,
                                  double maxCharge,
                                  double maxDischarge,
                                  double costWith,
                                  double costWithout,
                                  double savings) {

        String sql = "INSERT INTO EnergyRecords(capacity, maxCharge, maxDischarge, costWith, costWithout, savings) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, capacity);
            pstmt.setDouble(2, maxCharge);
            pstmt.setDouble(3, maxDischarge);
            pstmt.setDouble(4, costWith);
            pstmt.setDouble(5, costWithout);
            pstmt.setDouble(6, savings);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}