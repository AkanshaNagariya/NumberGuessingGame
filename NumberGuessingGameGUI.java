import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame implements ActionListener {
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea resultArea;
    private int secretNumber;
    private int maxAttempts;
    private int attempts;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 400); // Reduced the size of the GUI window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel infoLabel = new JLabel("Guess the number between 1 and 100:");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Increased font size
        panel.add(infoLabel);

        guessField = new JTextField();
        panel.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        panel.add(guessButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea); // Added a scroll pane for better visibility of messages
        panel.add(scrollPane);

        add(panel);
        setVisible(true);

        // Initialize game parameters
        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;
        maxAttempts = 5;
        attempts = 0;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            int guess;
            try {
                guess = Integer.parseInt(guessField.getText());
                if (guess < 1 || guess > 100) {
                    resultArea.append("Invalid guess! Please enter a number between 1 and 100.\n");
                } else {
                    attempts++;
                    if (guess == secretNumber) {
                        resultArea.append("Congratulations! You've guessed the number in " + attempts + " attempts.\n");
                        guessButton.setEnabled(false);
                    } else if (guess < secretNumber) {
                        resultArea.append("Too low! Try again.\n");
                    } else {
                        resultArea.append("Too high! Try again.\n");
                    }
                    if (attempts >= maxAttempts && guess != secretNumber) {
                        resultArea.append("Sorry, you've run out of attempts. The correct number was: " + secretNumber + "\n");
                        guessButton.setEnabled(false);
                    }
                }
            } catch (NumberFormatException ex) {
                resultArea.append("Invalid input! Please enter a valid number.\n");
            }
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGameGUI());
    }
}
