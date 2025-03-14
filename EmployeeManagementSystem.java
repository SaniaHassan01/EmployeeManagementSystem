import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagementSystem extends JFrame {

    public EmployeeManagementSystem() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        getContentPane().setLayout(new BorderLayout());

        // Heading label
        JLabel headingLabel = new JLabel("Employee Management System");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        getContentPane().add(headingLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); 

        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        JButton btnAddEmployee = new JButton("Add Employee");
        buttonPanel.add(btnAddEmployee, gbc);
        btnAddEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddEmployeeWindow addEmployeeWindow = new AddEmployeeWindow();
                addEmployeeWindow.setVisible(true);
            }
        });

        JButton btnViewEmployees = new JButton("View Employees");
        gbc.gridy = 1; // Move to the next row
        buttonPanel.add(btnViewEmployees, gbc);
        btnViewEmployees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewEmployeeWindow viewEmployeeWindow = new ViewEmployeeWindow();
                viewEmployeeWindow.setVisible(true);
            }
        });

        JButton btnUpdateEmployee = new JButton("Update Employee");
        gbc.gridy = 2; // Move to the next row
        buttonPanel.add(btnUpdateEmployee, gbc);
        btnUpdateEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateEmployeeWindow updateEmployeeWindow = new UpdateEmployeeWindow();
                updateEmployeeWindow.setVisible(true);
            }
        });

        JButton btnDeleteEmployee = new JButton("Delete Employee");
        gbc.gridy = 3; // Move to the next row
        buttonPanel.add(btnDeleteEmployee, gbc);
        btnDeleteEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteEmployeeWindow deleteEmployeeWindow = new DeleteEmployeeWindow();
                deleteEmployeeWindow.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeManagementSystem frame = new EmployeeManagementSystem();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}