package gm.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class exercise5 extends JFrame {
    // Button to go to the next question
    private final JButton nextButton = new JButton("Next");
    // Label to display the current question
    private final JLabel questionLabel = new JLabel(questions[currentQuestion]);
    // Panel to hold the answer buttons
    private final JPanel buttonPanel = new JPanel();
    // Button group to group the radio buttons
    private final ButtonGroup buttonGroup = new ButtonGroup();
    // Array of radio buttons for the answer options
    private final JRadioButton[] buttons = new JRadioButton[4];

    // Static variables to keep track of the score and current question index
    private static int score = 0;
    private static int currentQuestion = 0;
    // Flag to indicate if the answer is shown
    private boolean isAnswerShown = false;

    // Array of questions
    private static String[] questions = {
            "The capital of Poland is?",
            "The largest ocean on Earth is?",
            "Which chemical element has the symbol 'O'?"
    };

    // Array of answer options for each question
    private static String[][] options = {
            {"Warsaw", "Cracow", "Gdansk", "Poznan"},
            {"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"},
            {"Oxygen", "Hydrogen", "Nitrogen", "Helium"}
    };

    // Array of correct answer indices for each question
    private static int[] answers = {0, 0, 0}; // Correct answers index

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    exercise5 window = new exercise5();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor to set the title of the JFrame
    public exercise5() throws HeadlessException {
        this("General Knowledge Quiz");
    }

    // Constructor with title parameter
    public exercise5(String title) throws HeadlessException {
        super(title);
        buildFrame();
    }

    // Method to build the JFrame
    protected void buildFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Set layout for the button panel
        buttonPanel.setLayout(new GridLayout(2, 2));

        // Initialize the radio buttons and add them to the button group and panel
        for (int i = 0; i < 4; i++) {
            buttons[i] = new JRadioButton(options[currentQuestion][i]);
            buttonGroup.add(buttons[i]);
            buttonPanel.add(buttons[i]);
        }

        // Add action listener to the next button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onNextButtonClicked();
            }
        });

        // Set layout for the frame and add components
        setLayout(new BorderLayout());
        add(questionLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(nextButton, BorderLayout.SOUTH);
    }

    // Method to handle the next button click event
    private void onNextButtonClicked() {
        int selectedAnswer = -1;

        if (!isAnswerShown) {
            // Check which radio button is selected
            for (int i = 0; i < 4; i++) {
                if (buttons[i].isSelected()) {
                    selectedAnswer = i;
                }
            }

            // Check if the selected answer is correct
            if (selectedAnswer == answers[currentQuestion]) {
                buttons[selectedAnswer].setForeground(Color.GREEN);
                score++;
            } else if (selectedAnswer != -1) {
                buttons[selectedAnswer].setForeground(Color.RED);
                buttons[answers[currentQuestion]].setForeground(Color.GREEN);
            } else {
                buttons[answers[currentQuestion]].setForeground(Color.GREEN);
            }

            isAnswerShown = true;
        } else {
            // Reset the colors of the radio buttons
            for (int i = 0; i < 4; i++) {
                buttons[i].setForeground(Color.BLACK);
            }
            currentQuestion++;
            if (currentQuestion < questions.length) {
                // Update the question and answer options
                questionLabel.setText(questions[currentQuestion]);
                buttonGroup.clearSelection();
                for (int i = 0; i < 4; i++) {
                    buttons[i].setText(options[currentQuestion][i]);
                }
            } else {
                // Show the final score and close the quiz
                JOptionPane.showMessageDialog(this, "End of the quiz! Your score: " + score);
                dispose();
            }
            isAnswerShown = false;
        }
    }
}