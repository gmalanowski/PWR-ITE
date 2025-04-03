package gm.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class exercise6 extends JFrame {
    // Text field for inputting weight
    JTextField weightField = new JTextField(10);
    // Text field for inputting height
    JTextField heightField = new JTextField(10);
    // Label to display the BMI result
    JLabel resultLabel = new JLabel("BMI Result: ");
    // Button to trigger the BMI calculation
    JButton calculateButton = new JButton("Calculate");
    // Panel to hold all components with a grid layout
    JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    exercise6 window = new exercise6();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor to set the title of the JFrame
    public exercise6() throws HeadlessException {
        this("BMI Calculator");
    }

    // Constructor with title parameter
    public exercise6(String title) throws HeadlessException {
        super(title);
        buildFrame();
    }

    // Method to build the JFrame
    protected void buildFrame() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add action listener to the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateBMI();
            }
        });

        // Build the panel with all components
        buildPanel();
        // Add the panel to the frame
        add(panel);
    }

    // Method to calculate BMI based on the input weight and height
    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText()) / 100;
            double bmi = weight / (height * height);
            resultLabel.setText(String.format("BMI Result: %.2f", bmi));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter correct values.");
        }
    }

    // Method to build the panel with all components
    private void buildPanel() {
        panel.add(new JLabel("Weight (kg):"));
        panel.add(weightField);
        panel.add(new JLabel("Height (cm):"));
        panel.add(heightField);
        panel.add(calculateButton);
        panel.add(resultLabel);
    }
}