import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteEmployeeWindow extends JFrame {
    private JTextField codeField;

    public DeleteEmployeeWindow() {
        setTitle("Delete Employee");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Enter Employee ID:"));
        codeField = new JTextField();
        add(codeField);

        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");
        add(deleteButton);
        add(cancelButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Delete Employee from File
    private void deleteEmployee() {
        String idToDelete = codeField.getText().trim();
        if (idToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee Code!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> employees = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(idToDelete)) {
                    employees.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Employee ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            for (String employee : employees) {
                writer.write(employee + "\n");
            }
            JOptionPane.showMessageDialog(this, "Employee Deleted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            codeField.setText("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

