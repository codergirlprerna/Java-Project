import java.util.*;

public class GuessTheWord {
    private String wordToGuess;
    private StringBuilder currentGuess;
    private int allowedAttempts;
    private Scanner scanner;

    private String[] words = {
        "JAVA", "PROGRAMMING", "DEVELOPER", "COMPUTER", "KEYBOARD", "MOUSE"
    };

    public GuessTheWord() {
        scanner = new Scanner(System.in);
    }

    private void selectWord() {
        Random random = new Random();
        wordToGuess = words[random.nextInt(words.length)];
        currentGuess = new StringBuilder("_".repeat(wordToGuess.length()));
    }

    private void setDifficulty(String level) {
        switch (level.toLowerCase()) {
            case "easy":
                allowedAttempts = 10;
                break;
            case "medium":
                allowedAttempts = 7;
                break;
            case "hard":
                allowedAttempts = 5;
                break;
            default:
                System.out.println("Invalid difficulty level. Setting to easy.");
                allowedAttempts = 10;
        }
    }

    public void playGame() {
        System.out.println("Welcome to Guess the Word!");
        System.out.print("Choose difficulty (easy, medium, hard): ");
        String difficulty = scanner.nextLine();
        setDifficulty(difficulty);
        selectWord();

        while (allowedAttempts > 0) {
            System.out.println("Current Guess: " + currentGuess);
            System.out.println("Attempts remaining: " + allowedAttempts);
            System.out.print("Guess a letter or the whole word: ");
            String guess = scanner.nextLine().toUpperCase().trim(); // Clean input

            // Debugging output
            System.out.println("Word to guess: " + wordToGuess); // Show the word to guess
            System.out.println("User guess: " + guess); // Show the user's guess

            if (guess.length() == 1) { // Single letter guess
                if (wordToGuess.contains(guess)) {
                    for (int i = 0; i < wordToGuess.length(); i++) {
                        if (wordToGuess.charAt(i) == guess.charAt(0)) {
                            currentGuess.setCharAt(i, guess.charAt(0));
                        }
                    }
                    System.out.println("Correct guess: " + guess); // Indicate correct guess
                } else {
                    allowedAttempts--;
                    System.out.println("Wrong guess!");
                }
            } else if (guess.equals(wordToGuess)) { // Whole word guess
                currentGuess = new StringBuilder(wordToGuess); // Reveal the word
                break;
            } else {
                allowedAttempts--;
                System.out.println("Wrong guess!");
            }

            if (currentGuess.toString().equals(wordToGuess)) {
                System.out.println("Congratulations! You guessed the word: " + wordToGuess);
                return; // Exit the method after a correct guess
            }
        }

        System.out.println("Game Over! The word was: " + wordToGuess);
    }

    public static void main(String[] args) {
        GuessTheWord game = new GuessTheWord();
        game.playGame();
    }
}
