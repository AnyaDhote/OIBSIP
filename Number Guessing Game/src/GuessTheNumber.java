import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lower = 1;
        int upper = 100;
        int generatedNumber = random.nextInt(upper - lower + 1) + lower;
        
        System.out.println("Enter Maximum Attempts : ");
        int maxAttempts = scanner.nextInt(); 
        int currentRound = 1;
        int totalScore = 0;

        System.out.println("Welcome to Guess the Number Game!");

        while (currentRound <= 3) { 
            System.out.println("\nRound " + currentRound);
            int attemptsLeft = maxAttempts;

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess (between " + lower + " and " + upper + "): ");
                int userGuess = scanner.nextInt();

                if (userGuess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    int roundScore = 10 * attemptsLeft; 
                    totalScore += roundScore;
                    System.out.println("Round Score: " + roundScore);
                    break;
                } else if (userGuess < generatedNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }

                attemptsLeft--;
                if (attemptsLeft == 0) {
                    System.out.println("Sorry, you've run out of attempts.\n\nThe correct number was: " + generatedNumber);
                }
            }

            currentRound++;
            generatedNumber = random.nextInt(upper - lower + 1) + lower;
        }

        System.out.println("\nGame Over!");
        System.out.println("Total Score: " + totalScore);

        scanner.close();
    }
}
