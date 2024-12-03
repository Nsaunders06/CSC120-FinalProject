import java.util.Scanner; 
public class GameLoop {
    public static void main(String[] args) {
        // Attributes 
        boolean stillPlaying = true; // let us know when the loop should end 
        Scanner userInput = new Scanner(System.in); 
        String userResponse = ""; 

        //Welcome message 
        System.out.println("******************");
        System.out.println("WELCOME TO MY GAME");
        System.out.println("******************");

        do{ 
         System.out.println("You are still stuck. Keep going :)");

        } while (stillPlaying);


    }
}
