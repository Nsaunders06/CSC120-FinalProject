import java.util.Scanner; 
public class GameLoop {
    public static void main(String[] args) {
        // Attributes 
        boolean stillPlaying = true; // let us know when the loop should end 
        Scanner userInput = new Scanner(System.in); 
        String userResponse = ""; 
        Person person = new Person("Outside"); 
        //Welcome message 
        System.out.println("******************");
        System.out.println("WELCOME TO MY GAME");
        System.out.println("******************");
        System.out.println("What would you like to do first?");
        
        do{ 
        userResponse = userInput.nextLine().toUpperCase();
        if (userResponse.equals("Look Around")){
            person.lookAround(); 
        }

        
        System.out.println("You are still stuck. Keep going :)");
        








        } while (stillPlaying);


    }
}