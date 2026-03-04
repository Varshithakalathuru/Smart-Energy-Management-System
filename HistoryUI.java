import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryUI extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public HistoryUI() {

        setTitle("Simulation History");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Capacity");
        model.addColumn("Max Charge");
        model.addColumn("Max Discharge");
        model.addColumn("Cost With");
        model.addColumn("Cost Without");
        model.addColumn("Savings");
        model.addColumn("Date");

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadData();

        setVisible(true);
    }

    private void loadData() {

        List<String[]> records = FileStorage.readRecords();

        for (String[] row : records) {
            if (row.length >= 7) {
                model.addRow(row);
            }
        }
    }
}