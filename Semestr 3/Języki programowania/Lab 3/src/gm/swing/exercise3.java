package gm.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class exercise3 extends JFrame {

    // Model to hold the list of shopping items
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    // JList to display the shopping items
    private final JList<String> shoppingList = new JList<>(listModel);
    // Text field for inputting new items
    private final JTextField itemInput = new JTextField(20);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    exercise3 window = new exercise3();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor to set the title of the JFrame
    public exercise3() throws HeadlessException {
        this("Shopping List");
    }

    // Constructor with title parameter
    public exercise3(String title) throws HeadlessException {
        super(title);
        buildFrame();
    }

    // Method to build the JFrame
    protected void buildFrame() {
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Button to add items to the list
        JButton addButton = new JButton("Add");
        // Button to remove selected items from the list
        JButton removeButton = new JButton("Remove");

        // Add action listener to the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToList();
            }
        });

        // Add action listener to the remove button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItemFromList();
            }
        });

        // Panel to hold the input field and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.add(itemInput);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        // Add the shopping list to the center of the frame with a scroll pane
        add(new JScrollPane(shoppingList), BorderLayout.CENTER);
        // Add the input panel to the bottom of the frame
        add(inputPanel, BorderLayout.SOUTH);
    }

    // Method to add an item to the list
    private void addItemToList() {
        String item = itemInput.getText().trim();
        if (!item.isEmpty()) {
            listModel.addElement(item);
            itemInput.setText("");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please enter an item to add to the list.");
        }
    }

    // Method to remove the selected item from the list
    private void removeItemFromList() {
        int selectedIndex = shoppingList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        }
    }
}