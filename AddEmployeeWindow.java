import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddEmployeeWindow extends JFrame {
    private JTextField codeField, nameField, positionField, salaryField, dateField, statusField;

    public AddEmployeeWindow() {
        setTitle("Add Employee");
        setSize(400, 300);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Labels & Input Fields
        add(new JLabel("Employee Code:"));
        codeField = new JTextField();
        add(codeField);

        add(new JLabel("Employee Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Employee Position:"));
        positionField = new JTextField();
        add(positionField);

        add(new JLabel("Employee Salary:"));
        salaryField = new JTextField();
        add(salaryField);

        add(new JLabel("Joining Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Current Status (Active/Inactive):"));
        statusField = new JTextField();
        add(statusField);

        // Buttons
        JButton addButton = new JButton("Add");
        JButton resetButton = new JButton("Reset");
        add(addButton);
        add(resetButton);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveEmployee();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Save Employee to File
    private void saveEmployee() {
        String code = codeField.getText().trim();
        String name = nameField.getText().trim();
        String position = positionField.getText().trim();
        String salary = salaryField.getText().trim();
        String joiningDate = dateField.getText().trim();
        String status = statusField.getText().trim();

        if (code.isEmpty() || name.isEmpty() || position.isEmpty() || salary.isEmpty() || joiningDate.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true))) {
            writer.write(code + "," + name + "," + position + "," + salary + "," + joiningDate + "," + status + "\n");
            JOptionPane.showMessageDialog(this, "Employee Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving employee!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Clear Input Fields
    private void clearFields() {
        codeField.setText("");
        nameField.setText("");
        positionField.setText("");
        salaryField.setText("");
        dateField.setText("");
        statusField.setText("");
    }
}