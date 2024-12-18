
import java.util.ArrayList;
import java.util.Scanner;

public class GameLoop {

    public static void main(String[] args) {
        // Attributes 
        boolean stillPlaying = true; // let us know when the loop should end 
        Scanner userInput = new Scanner(System.in);
        String userResponse = "";
        Person person = new Person("Outside");
        Outside outside = new Outside();
        Inside inside = new Inside();

        //inside.addRiddles()

        ArrayList<Item> gameItems = new ArrayList<>();
        gameItems.add(new Item("Pen", "A mysterious writing utensil", -1, 0, "Inside", false));

        //Welcome message 
        System.out.println("******************");
        System.out.println("WELCOME TO MY GAME");
        System.out.println("******************");
        System.out.println("What would you like to do first?");

        do {
            userResponse = userInput.nextLine().toUpperCase();
            if (userResponse.equals("LOOK AROUND")) {
                person.lookAround();
            }
            if (userResponse.contains("WHERE AM I")) {
                person.viewLocation();
                person.getRoomLocation();
                System.out.println("You are at " + person.viewLocation() + "in the" + person.getRoomLocation());
            }

            if (userResponse.contains("MOVE")) {
                String direction = "forward";
                int intAmt = 1;
                Scanner scan = new Scanner(userResponse);
                scan.next();
                if (scan.hasNext()) {
                    direction = scan.next();
                    if (scan.hasNext()) {
                        String stringAmt = scan.next();
                        intAmt = Integer.parseInt(stringAmt);
                    }
                }
                person.move(direction, intAmt, outside, inside);
            }

            if (userResponse.contains("GRAB") || userResponse.contains("PICK UP")) {
                person.grab(gameItems, outside);
            }

            if (userResponse.contains("VIEW INVENTORY") || userResponse.contains("INVENTORY") || userResponse.contains("WHAT DO I HAVE")) {
                person.viewInventory();
            }

            //Looks if true --> if the door is open then if the location is 2 & 2 set the doors open 
            if (userResponse.contains("OPEN")) {
                person.open(person.getLocationX(), person.getLocationY(), person.getRoomLocation(), outside, inside);
            }

            if (userResponse.contains("ANSWER RIDDLE")) {
                if (person.getRoomLocation().equals("Inside")) {
                    System.out.println("Answering a riddle...");
                    //inside.askRiddle();
                } else {
                    System.out.println("You must be inside to answer riddles.");
                }
            }
            System.out.println("You are still stuck. Keep going :)");

        } while (stillPlaying);

    }
}
