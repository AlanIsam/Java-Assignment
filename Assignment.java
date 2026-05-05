// Contributed by ALAN ISAM ANAK RECKY: GUI design & event handling.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Assignment.java
 * Main class. Sets up the window and loads the GUIPanel.
 * Encoding logic is in the Encoded class (done by my teammate).
 */
public class Assignment {

    /**
     * Entry point of the program. Creates the frame and shows it.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        // Run the GUI on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame mainFrame = new JFrame("String Encoder");
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setSize(500, 350);
                mainFrame.setLocationRelativeTo(null);

                GUIPanel panel = new GUIPanel();
                mainFrame.add(panel);
                mainFrame.setVisible(true);
            }
        });
    }

    // =========================================================================
    // INNER CLASS: GUIPanel
    // =========================================================================

    /**
     * GUIPanel holds all the UI components and handles user interaction.
     * I used an inner class to keep everything in one file.
     */
    // Contributed by ALAN ISAM ANAK RECKY: GUI design & event handling.
    private static class GUIPanel extends JPanel {

        // UI components
        private JTextField inputField;
        private JButton submitButton;
        private JTextArea outputArea;

        /**
         * Sets up the layout and components.
         */
        public GUIPanel() {
            // Contributed by ALAN ISAM ANAK RECKY: GUI design & event handling.

            this.setLayout(new BorderLayout(10, 10));

            // Top panel stacked in 2 rows: label on top, input + button below
            JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));

            JLabel instructionLabel = new JLabel("Enter a string (lowercase letters and spaces only):");
            topPanel.add(instructionLabel);

            // Sub-panel to hold the text field and button side by side
            JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            inputField = new JTextField(25);
            inputRow.add(inputField);

            submitButton = new JButton("Encode");
            inputRow.add(submitButton);

            topPanel.add(inputRow);

            this.add(topPanel, BorderLayout.NORTH);

            // Results label and output area
            JLabel outputLabel = new JLabel("  Results:");
            this.add(outputLabel, BorderLayout.CENTER);

            outputArea = new JTextArea(8, 40);
            outputArea.setEditable(false);
            outputArea.setLineWrap(true);

            JScrollPane scrollPane = new JScrollPane(outputArea);
            this.add(scrollPane, BorderLayout.SOUTH);

            // Using anonymous inner class instead of lambda
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleEncodeButtonClick();
                }
            });
        }

        /**
         * Runs when the Encode button is clicked.
         * Validates input then shows the results.
         */
        private void handleEncodeButtonClick() {
            String userInput = inputField.getText();

            // Pass the input to the Encoded class
            Encoded encoder = new Encoded(userInput);

            // Check if input only has lowercase letters and spaces
            if (encoder.checkStringValidity() == false) {
                JOptionPane.showMessageDialog(
                    GUIPanel.this,
                    "Invalid input! Please use only lowercase letters and spaces.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );
                outputArea.setText("");

            } else {
                // Get the values from the Encoded class
                int charCount = encoder.countCharacters();
                int finalShift = encoder.getFinalShift();
                String encodedResult = encoder.applyCipher();

                // Build and display the output
                String output = "--- Encoding Results ---\n";
                output += "Number of non-space characters : " + charCount + "\n";
                output += "Final shift value used          : " + finalShift + "\n";
                output += "Encoded result                  : " + encodedResult + "\n";

                outputArea.setText(output);
            }
        }
    }
}
