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
        if (userResponse.equals("LOOK AROUND")){
            person.lookAround(); 
        }
        if(userResponse.equals("WHERE AM I?") || userResponse.equals("WHERE AM I")){
            person.viewLocation(); 
            person.getRoomLocation(); 
            System.out.println("You are at " + person.viewLocation() + "in the" + person.getRoomLocation());
        }

        if (userResponse.contains("MOVE")) {
        Scanner scan = new Scanner(userResponse);
        scan.next();
        String direction = scan.next();
        String stringAmt = scan.next();
        int intAmt = Integer.parseInt(stringAmt);
        person.move(direction, intAmt);
        }
        
        System.out.println("You are still stuck. Keep going :)");
        








        } while (stillPlaying);

    
    }
}
