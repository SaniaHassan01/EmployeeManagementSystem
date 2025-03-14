import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewEmployeeWindow extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public ViewEmployeeWindow() {
        setTitle("View Employees");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Table Column Headers
        String[] columnNames = {"ID", "Name", "Position", "Salary", "Joining Date", "Status"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadEmployees(); // Load employee data into table

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Load Employee Data from File
    private void loadEmployees() {
        model.setRowCount(0); // Clear previous data

        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    model.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading employee data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



