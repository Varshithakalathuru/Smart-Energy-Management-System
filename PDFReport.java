import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class PDFReport {

    public static void generateReport(double withBattery,
                                      double withoutBattery,
                                      double savings) {

        try {

            double savingsPercent = (savings / withoutBattery) * 100;

            FileWriter writer = new FileWriter("EnergyReport.pdf");

            writer.write("SMART ENERGY MANAGEMENT SYSTEM REPORT\n");
            writer.write("======================================\n\n");

            writer.write("Generated On: " + LocalDateTime.now() + "\n\n");

            writer.write("Student Name: Varshitha\n");
            writer.write("Project: Smart Energy Management System\n\n");

            writer.write("Total Cost WITH Battery    : ₹ " + withBattery + "\n");
            writer.write("Total Cost WITHOUT Battery : ₹ " + withoutBattery + "\n");
            writer.write("Total Savings              : ₹ " + savings + "\n");
            writer.write("Savings Percentage         : " + String.format("%.2f", savingsPercent) + " %\n");

            writer.close();

            System.out.println("PDF Report Generated Successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}