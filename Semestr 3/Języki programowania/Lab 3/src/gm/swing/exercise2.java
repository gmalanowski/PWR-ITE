package gm.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

public class exercise2 extends JFrame {

    // Menu bar for the application
    private final JMenuBar menuBar = new JMenuBar();
    // Text area for the notepad
    private final JTextArea textArea = new JTextArea();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    exercise2 window = new exercise2();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor to set the title of the JFrame
    public exercise2() throws HeadlessException {
        this("Notepad");
    }

    // Constructor with title parameter
    public exercise2(String title) throws HeadlessException {
        super(title);
        buildFrame();
    }

    // Method to build the JFrame
    protected void buildFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Add the text area with a scroll pane
        add(new JScrollPane(textArea));

        // Build the menu bar
        buildMenu();
    }

    // Method to build the menu bar
    private void buildMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem closeApp = new JMenuItem("Close");

        // Set mnemonics for menu items
        newFile.setMnemonic(KeyEvent.VK_N);
        openFile.setMnemonic(KeyEvent.VK_O);
        saveFile.setMnemonic(KeyEvent.VK_S);

        // Set accelerators for menu items
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));

        // Add menu items to the file menu
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.addSeparator();
        fileMenu.add(closeApp);

        // Add file menu to the menu bar
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Add action listeners for menu items
        newFile.addActionListener(e -> textArea.setText(""));

        openFile.addActionListener(e -> {
            doOpenFile();
        });

        saveFile.addActionListener(e -> {
            doSaveFile();
        });

        closeApp.addActionListener(e -> System.exit(0));
    }

    // Method to open a file and read its content into the text area
    private void doOpenFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to open file.");
            }
        }
    }

    // Method to save the content of the text area to a file
    private void doSaveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to save file.");
            }
        }
    }
}