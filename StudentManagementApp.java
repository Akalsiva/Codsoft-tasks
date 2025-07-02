import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class StudentManagementApp extends JFrame {

    private ArrayList<Student> students = new ArrayList<>();
    private JTextField nameField, rollField, gradeField, searchField;
    private JTextArea displayArea;
    private final String FILE_NAME = "students.txt";

    public StudentManagementApp() {
        setTitle("Student Management System");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Info"));

        nameField = new JTextField();
        rollField = new JTextField();
        gradeField = new JTextField();
        searchField = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll No:"));
        inputPanel.add(rollField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(new JLabel("Search Roll No:"));
        inputPanel.add(searchField);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display All");
        JButton clearButton = new JButton("Clear Fields");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(clearButton);
        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        loadFromFile();
        addButton.addActionListener(e -> addStudent());
        removeButton.addActionListener(e -> removeStudent());
        searchButton.addActionListener(e -> searchStudent());
        displayButton.addActionListener(e -> displayStudents());
        clearButton.addActionListener(e -> clearFields());

        setVisible(true);
    }
    class Student {
        private String name;
        private String rollNo;
        private String grade;

        public Student(String name, String rollNo, String grade) {
            this.name = name;
            this.rollNo = rollNo;
            this.grade = grade;
        }

        public String getRollNo() {
            return rollNo;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Roll No: " + rollNo + ", Grade: " + grade;
        }

        public String toFileString() {
            return name + "," + rollNo + "," + grade;
        }
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        String grade = gradeField.getText().trim();

        if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (Student s : students) {
            if (s.getRollNo().equalsIgnoreCase(roll)) {
                JOptionPane.showMessageDialog(this, "Roll number already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        students.add(new Student(name, roll, grade));
        saveToFile();
        JOptionPane.showMessageDialog(this, "Student added successfully!");
        clearFields();
    }

    private void removeStudent() {
        String roll = searchField.getText().trim();
        boolean removed = students.removeIf(s -> s.getRollNo().equalsIgnoreCase(roll));
        if (removed) {
            saveToFile();
            JOptionPane.showMessageDialog(this, "Student removed.");
        } else {
            JOptionPane.showMessageDialog(this, "Student not found.");
        }
    }

    private void searchStudent() {
        String roll = searchField.getText().trim();
        for (Student s : students) {
            if (s.getRollNo().equalsIgnoreCase(roll)) {
                displayArea.setText("Student Found:\n" + s);
                return;
            }
        }
        displayArea.setText("Student not found.");
    }

    private void displayStudents() {
        if (students.isEmpty()) {
            displayArea.setText("No students to display.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Student s : students) {
                sb.append(s).append("\n");
            }
            displayArea.setText(sb.toString());
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        gradeField.setText("");
        searchField.setText("");
        displayArea.setText("");
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.write(s.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to file.");
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            // File may not exist yet
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementApp::new);
    }
}