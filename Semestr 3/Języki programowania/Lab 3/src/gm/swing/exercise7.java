package gm.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class exercise7 extends JFrame {
    // Text field for inputting the text to be encrypted
    private final JTextField inputField = new JTextField(15);
    // ComboBox for selecting the encryption method
    private final JComboBox<String> methodBox = new JComboBox<>(new String[]{"ROT13", "Caesar"});
    // Button to trigger the encryption
    private final JButton encryptButton = new JButton("Encrypt");
    // Label to display the encryption result
    private final JLabel resultLabel = new JLabel("Result: ");
    // Panel to hold all components with a grid layout
    private final JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    exercise7 window = new exercise7();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor to set the title of the JFrame
    public exercise7() throws HeadlessException {
        this("Text Encryptor");
    }

    // Constructor with title parameter
    public exercise7(String title) throws HeadlessException {
        super(title);
        buildFrame();
    }

    // Method to build the JFrame
    protected void buildFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        // Add action listener to the encrypt button
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encrypt();
            }
        });

        // Build the panel with all components
        buildPanel();
        // Add the panel to the frame
        add(panel);
    }

    // Method to build the panel with all components
    private void buildPanel() {
        panel.add(new JLabel("Text:"));
        panel.add(inputField);
        panel.add(methodBox);
        panel.add(encryptButton);
        panel.add(new JLabel("")); // Empty label to move the result to the next line
        panel.add(resultLabel);
    }

    // Method to handle the encryption process
    private void encrypt() {
        String text = inputField.getText();
        String method = (String) methodBox.getSelectedItem();
        String result = "";

        if ("ROT13".equals(method)) {
            result = rot13(text);
        } else if ("Caesar".equals(method)) {
            result = caesarCipher(text, 3);
        }

        resultLabel.setText("Result: " + result);
    }

    // Method to perform ROT13 encryption
    public static String rot13(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                result.append((char) ('a' + (c - 'a' + 13) % 26));
            } else if (c >= 'A' && c <= 'Z') {
                result.append((char) ('A' + (c - 'A' + 13) % 26));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // Method to perform Caesar cipher encryption with a given shift
    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                result.append((char) ('a' + (c - 'a' + shift) % 26));
            } else if (c >= 'A' && c <= 'Z') {
                result.append((char) ('A' + (c - 'A' + shift) % 26));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}