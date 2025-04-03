package gm.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class exercise4 extends JFrame {

    // Array of units for conversion
    private final String[] units = {"Meters", "Kilometers", "Miles", "Centimeters", "Grams", "Kilograms", "Pounds", "Liters", "Milliliters", "Gallons"};
    // ComboBox for selecting the unit to convert from
    private final JComboBox<String> fromUnit = new JComboBox<>(units);
    // ComboBox for selecting the unit to convert to
    private final JComboBox<String> toUnit = new JComboBox<>(units);
    // TextField for inputting the value to be converted
    private final JTextField inputField = new JTextField(10);
    // Label to display the conversion result
    private final JLabel resultLabel = new JLabel("Result: ");
    // Panel to hold all components
    private final JPanel panel = new JPanel();
    // Button to trigger the conversion
    private final JButton convertButton = new JButton("Convert");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    exercise4 window = new exercise4();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor to set the title of the JFrame
    public exercise4() throws HeadlessException {
        this("Unit Converter");
    }

    // Constructor with title parameter
    public exercise4(String title) throws HeadlessException {
        super(title);
        buildFrame();
    }

    // Method to build the JFrame
    protected void buildFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 110);

        // Add action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConvertButtonPressed();
            }
        });

        // Build the panel with all components
        buildPanel();

        // Add the panel to the frame
        add(panel);
    }

    // Method to build the panel with all components
    private void buildPanel() {
        panel.add(new JLabel("From:"));
        panel.add(fromUnit);
        panel.add(new JLabel("To:"));
        panel.add(toUnit);
        panel.add(inputField);
        panel.add(convertButton);
        panel.add(resultLabel);
    }

    // Method to handle the convert button press event
    private void onConvertButtonPressed() {
        try {
            double value = Double.parseDouble(inputField.getText());
            String from = (String) fromUnit.getSelectedItem();
            String to = (String) toUnit.getSelectedItem();

            if (isValidConversion(from, to)) {
                double result = convertUnits(value, from, to);
                resultLabel.setText("Result: " + result);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid conversion type.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid number.");
        }
    }

    // Method to validate if the conversion is valid
    private boolean isValidConversion(String from, String to) {
        String[] massUnits = {"Grams", "Kilograms", "Pounds"};
        String[] volumeUnits = {"Liters", "Milliliters", "Gallons"};

        boolean fromIsMass = java.util.Arrays.asList(massUnits).contains(from);
        boolean toIsMass = java.util.Arrays.asList(massUnits).contains(to);
        boolean fromIsVolume = java.util.Arrays.asList(volumeUnits).contains(from);
        boolean toIsVolume = java.util.Arrays.asList(volumeUnits).contains(to);

        return (fromIsMass && toIsMass) || (fromIsVolume && toIsVolume) || (!fromIsMass && !fromIsVolume && !toIsMass && !toIsVolume);
    }

    // Method to convert units based on the selected from and to units
    private static double convertUnits(double value, String from, String to) {
        double factor;

        // Length conversions
        if (from.equals("Meters") && to.equals("Kilometers")) factor = 0.001;
        else if (from.equals("Meters") && to.equals("Miles")) factor = 0.000621371;
        else if (from.equals("Meters") && to.equals("Centimeters")) factor = 100.0;
        else if (from.equals("Kilometers") && to.equals("Meters")) factor = 1000.0;
        else if (from.equals("Kilometers") && to.equals("Miles")) factor = 0.621371;
        else if (from.equals("Kilometers") && to.equals("Centimeters")) factor = 100000.0;
        else if (from.equals("Miles") && to.equals("Meters")) factor = 1609.34;
        else if (from.equals("Miles") && to.equals("Kilometers")) factor = 1.60934;
        else if (from.equals("Miles") && to.equals("Centimeters")) factor = 160934.0;
        else if (from.equals("Centimeters") && to.equals("Meters")) factor = 0.01;
        else if (from.equals("Centimeters") && to.equals("Kilometers")) factor = 0.00001;
        else if (from.equals("Centimeters") && to.equals("Miles")) factor = 0.0000062137;

            // Mass conversions
        else if (from.equals("Grams") && to.equals("Kilograms")) factor = 0.001;
        else if (from.equals("Grams") && to.equals("Pounds")) factor = 0.00220462;
        else if (from.equals("Kilograms") && to.equals("Grams")) factor = 1000.0;
        else if (from.equals("Kilograms") && to.equals("Pounds")) factor = 2.20462;
        else if (from.equals("Pounds") && to.equals("Grams")) factor = 453.592;
        else if (from.equals("Pounds") && to.equals("Kilograms")) factor = 0.453592;

            // Volume conversions
        else if (from.equals("Liters") && to.equals("Milliliters")) factor = 1000.0;
        else if (from.equals("Liters") && to.equals("Gallons")) factor = 0.264172;
        else if (from.equals("Milliliters") && to.equals("Liters")) factor = 0.001;
        else if (from.equals("Milliliters") && to.equals("Gallons")) factor = 0.000264172;
        else if (from.equals("Gallons") && to.equals("Liters")) factor = 3.78541;
        else if (from.equals("Gallons") && to.equals("Milliliters")) factor = 3785.41;

        else factor = 1.0;

        return value * factor;
    }
}