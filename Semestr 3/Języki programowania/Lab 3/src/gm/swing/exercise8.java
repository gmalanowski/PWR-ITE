package gm.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class exercise8 extends JFrame {
    // Text area for inputting and displaying text
    private final JTextArea textArea = new JTextArea();
    // Button to save the text to a file
    private final JButton saveButton = new JButton("Save");
    // Button to load text from a file
    private final JButton loadButton = new JButton("Load");
    // Panel to hold the buttons
    private final JPanel buttonPanel = new JPanel();
    // Scroll pane to make the text area scrollable
    private final JScrollPane scrollPane = new JScrollPane(textArea);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    exercise8 window = new exercise8();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor to set the title of the JFrame
    public exercise8() throws HeadlessException {
        this("Simple Text Editor");
    }

    // Constructor with title parameter
    public exercise8(String title) throws HeadlessException {
        super(title);
        buildFrame();
    }

    // Method to build the JFrame
    protected void buildFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Add action listener to the save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        // Add action listener to the load button
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFile();
            }
        });

        // Build the panel with the buttons
        buildPanel();
        // Add the scroll pane to the center of the frame
        add(scrollPane, BorderLayout.CENTER);
        // Add the button panel to the bottom of the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method to build the panel with the buttons
    private void buildPanel() {
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
    }

    // Method to save the text to a file
    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File saved successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.");
            }
        }
    }

    // Method to load text from a file
    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
                JOptionPane.showMessageDialog(this, "File loaded successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading file.");
            }
        }
    }
}