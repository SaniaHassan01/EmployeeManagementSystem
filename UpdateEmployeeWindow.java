import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateEmployeeWindow extends JFrame {
    private JTextField codeField, nameField, positionField, salaryField, dateField, statusField;
    private JButton searchButton, updateButton;
    private boolean employeeFound = false;
    private List<String> employees = new ArrayList<>();

    public UpdateEmployeeWindow() {
        setTitle("Update Employee");
        setSize(400, 350);
        setLayout(new GridLayout(8, 2, 10, 10));

        // Labels & Input Fields
        add(new JLabel("Employee Code:"));
        codeField = new JTextField();
        add(codeField);

        searchButton = new JButton("Search");
        add(searchButton);
        add(new JLabel()); // Empty space

        add(new JLabel("Employee Name:"));
        nameField = new JTextField();
        nameField.setEditable(false);
        add(nameField);

        add(new JLabel("Employee Position:"));
        positionField = new JTextField();
        positionField.setEditable(false);
        add(positionField);

        add(new JLabel("Employee Salary:"));
        salaryField = new JTextField();
        salaryField.setEditable(false);
        add(salaryField);

        add(new JLabel("Joining Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        dateField.setEditable(false);
        add(dateField);

        add(new JLabel("Current Status (Active/Inactive):"));
        statusField = new JTextField();
        statusField.setEditable(false);
        add(statusField);

        updateButton = new JButton("Update");
        updateButton.setEnabled(false);
        add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        add(cancelButton);

        // Button Actions
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchEmployee();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Search Employee and Load Data into Fields
    private void searchEmployee() {
        String idToSearch = codeField.getText().trim();
        if (idToSearch.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee Code!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        employees.clear();
        employeeFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(idToSearch)) {
                    // Populate fields with employee data
                    nameField.setText(data[1]);
                    positionField.setText(data[2]);
                    salaryField.setText(data[3]);
                    dateField.setText(data[4]);
                    statusField.setText(data[5]);

                    nameField.setEditable(true);
                    positionField.setEditable(true);
                    salaryField.setEditable(true);
                    dateField.setEditable(true);
                    statusField.setEditable(true);

                    updateButton.setEnabled(true);
                    employeeFound = true;
                }
                employees.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (!employeeFound) {
            JOptionPane.showMessageDialog(this, "Employee ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update Employee Details
    private void updateEmployee() {
        String idToUpdate = codeField.getText().trim();
        String updatedName = nameField.getText().trim();
        String updatedPosition = positionField.getText().trim();
        String updatedSalary = salaryField.getText().trim();
        String updatedDate = dateField.getText().trim();
        String updatedStatus = statusField.getText().trim();

        if (updatedName.isEmpty() || updatedPosition.isEmpty() || updatedSalary.isEmpty() || updatedDate.isEmpty() || updatedStatus.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            for (String employee : employees) {
                String[] data = employee.split(",");
                if (data[0].equals(idToUpdate)) {
                    writer.write(idToUpdate + "," + updatedName + "," + updatedPosition + "," + updatedSalary + "," + updatedDate + "," + updatedStatus + "\n");
                } else {
                    writer.write(employee + "\n");
                }
            }
            JOptionPane.showMessageDialog(this, "Employee Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
