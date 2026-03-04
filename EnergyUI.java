import javax.swing.*;
import java.awt.*;

public class EnergyUI extends JFrame {

    private JTextField capacityField;
    private JTextField chargeField;
    private JTextField dischargeField;
    private JTextArea outputArea;

    public EnergyUI() {

        setTitle("Smart Energy Management System");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        getContentPane().setBackground(new Color(230, 240, 255));

        // ===== INPUT CARD =====
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 12, 12));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Battery Configuration"),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        inputPanel.add(new JLabel("Battery Capacity:"));
        capacityField = new JTextField();
        inputPanel.add(capacityField);

        inputPanel.add(new JLabel("Max Charge per Hour:"));
        chargeField = new JTextField();
        inputPanel.add(chargeField);

        inputPanel.add(new JLabel("Max Discharge per Hour:"));
        dischargeField = new JTextField();
        inputPanel.add(dischargeField);

        JButton runButton = new JButton("Run Simulation");
        JButton resetButton = new JButton("Reset");
        JButton historyButton = new JButton("View History");

        runButton.setBackground(new Color(0, 123, 255));
        runButton.setForeground(Color.WHITE);
        runButton.setFont(new Font("Arial", Font.BOLD, 14));
        runButton.setFocusPainted(false);

        resetButton.setBackground(new Color(255, 87, 87));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setFocusPainted(false);

        historyButton.setBackground(new Color(0, 200, 150));
        historyButton.setForeground(Color.WHITE);
        historyButton.setFont(new Font("Arial", Font.BOLD, 14));
        historyButton.setFocusPainted(false);

        inputPanel.add(runButton);
        inputPanel.add(resetButton);
        inputPanel.add(historyButton);

        add(inputPanel, BorderLayout.NORTH);

        // ===== OUTPUT CARD =====
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setBackground(new Color(245, 245, 245));
        outputArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Simulation Result"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // ===== BUTTON ACTIONS =====
        runButton.addActionListener(e -> runSimulation());

        resetButton.addActionListener(e -> {
            capacityField.setText("");
            chargeField.setText("");
            dischargeField.setText("");
            outputArea.setText("");
        });

        historyButton.addActionListener(e -> new HistoryUI());

        setVisible(true);
    }

    private void runSimulation() {

        try {

            double capacity = Double.parseDouble(capacityField.getText());
            double maxCharge = Double.parseDouble(chargeField.getText());
            double maxDischarge = Double.parseDouble(dischargeField.getText());

            Battery battery = new Battery(capacity, maxCharge, maxDischarge);
            EnergyManager manager = new EnergyManager(battery);

            double costWithBattery = manager.runSimulation();
            double costWithoutBattery = CostReport.calculateWithoutBattery();

            double savings = costWithoutBattery - costWithBattery;

            // Save to File (History)
            FileStorage.saveRecord(
                    capacity,
                    maxCharge,
                    maxDischarge,
                    costWithBattery,
                    costWithoutBattery,
                    savings
            );

            PDFReport.generateReport(costWithBattery,
                    costWithoutBattery,
                    savings);

            EnergyGraph.showLineGraph(
                    manager.getHourlyCostWithBattery(),
                    CostReport.getHourlyCostWithoutBattery()
            );

            double savingsPercent = (savings / costWithoutBattery) * 100;

            outputArea.setText("===== Simulation Completed =====\n\n");
            outputArea.append("Total Cost WITH Battery    : ₹ " + costWithBattery + "\n");
            outputArea.append("Total Cost WITHOUT Battery : ₹ " + costWithoutBattery + "\n");
            outputArea.append("Total Savings              : ₹ " + savings + "\n");
            outputArea.append("Savings Percentage         : "
                    + String.format("%.2f", savingsPercent) + " %\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new EnergyUI();
    }
}