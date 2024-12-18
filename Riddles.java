
import java.util.Hashtable;
import java.util.Scanner;

public class Riddles {

//attributes 
    private Hashtable<String, String> riddleList;
    Scanner scanner;

// Constructor
    public Riddles() {
        riddleList = new Hashtable<>();
    }

    // Add a riddle to the list
    public void addRiddle(String question, String answer) {
        riddleList.put(question, answer);
    }

    // Ask a riddle and get the user's input
    public String askRiddle(String question) {
        if (!riddleList.containsKey(question)) {
            return null; // Riddle not found
        }
        System.out.println("Riddle: " + question);
        return scanner.nextLine().trim(); // Get user's answer
    }

    // Check if the answer is correct
    public boolean checkAnswer(String question, String userAnswer) {
        String correctAnswer = riddleList.get(question);
        return correctAnswer != null && correctAnswer.equalsIgnoreCase(userAnswer);
    }
}
