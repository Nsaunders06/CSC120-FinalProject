
import java.util.ArrayList;
import java.util.Scanner;

public class GameLoop {
/**
 * Makes game run! 
 * @param args
 */
    public static void main(String[] args) {
        // Attributes 
        boolean stillPlaying = true; // let us know when the loop should end 
        Scanner userInput = new Scanner(System.in);
        String userResponse = "";
        //Person person = new Person("Outside");
        Person person = new Person("Outside");
        Outside outside = new Outside();
        Inside inside = new Inside();
        Garden garden = new Garden();

        ArrayList<Item> gameItems = new ArrayList<>();
        gameItems.add(new Item("Pen", "A mysterious writing utensil", inside.getPenLocationX(), inside.getPenLocationY(), "Inside"));

        //Welcome message 
        System.out.println("******************************************************");
        System.out.println("          WELCOME TO THE CHOCO-VENTURE OF A LIFETIME!");
        System.out.println("******************************************************");
        System.out.println("\nYou’ve entered a world of pure imagination, where sugar sparkles, ");
        System.out.println("chocolate rivers flow, and surprises await around every corner. But beware, ");
        System.out.println("dear adventurer: this confectionery wonderland is as dangerous as it is delightful.\n");

        System.out.println("You find yourself standing Outside, under the candy-coated sun. ");
        System.out.println("Five chocolate bars are scattered around—hidden among sugar-coated shrubs, ");
        System.out.println("behind syrupy stones, and maybe even under your nose. ");
        System.out.println("One of these bars holds the key to your wildest dreams: a *Golden Ticket*.\n");

        System.out.println("Find the Golden Ticket and enter Inside, a room of curious mysteries. ");
        System.out.println("Seek out the enchanted pen and sign the magical contract—but only ");
        System.out.println("if you’re brave enough to embrace the unknown.\n");

        System.out.println("If your courage carries you further, you'll step into the Garden, ");
        System.out.println("a sugary utopia full of spectacular sights and treacherous traps. ");

        System.out.println("Will you float to sweet victory, or fall victim");
        System.out.println("to the sticky temptations of this tantalizing world?\n");

        System.out.println("The rules are simple, the stakes are high, and the candy... oh, the candy is glorious! ");
        System.out.println("Are you ready to embark on this delectable adventure? Type 'HELP' at any time ");
        System.out.println("to uncover the secrets of what you can do.\n");

        System.out.println("******************************************************");
        System.out.println("LET THE CHOCO-VENTURE BEGIN!");

        do {
            System.out.println("You are still stuck. Keep going :)");

            userResponse = userInput.nextLine().toUpperCase();

            if (userResponse.equals("HELP")) {
                person.displayHelp();
            }

            if (userResponse.equals("LOOK AROUND")) {
                person.lookAround(outside, inside, garden);
            }
            if (userResponse.contains("WHERE AM I")) {
                person.viewLocation();
                person.getRoomLocation();
                System.out.println("You are at " + person.viewLocation() + "in the " + person.getRoomLocation());
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
                person.move(direction, intAmt, outside, inside, garden);
            }

            if (userResponse.contains("GRAB") || userResponse.contains("PICK UP")) {
                person.grab(gameItems, outside, inside);
            }

            if (userResponse.contains("VIEW INVENTORY") || userResponse.contains("INVENTORY") || userResponse.contains("WHAT DO I HAVE")) {
                person.viewInventory();
            }

            //Looks if true --> if the door is open then if the location is 2 & 2 set the doors open 
            if (userResponse.contains("OPEN")) {
                person.open(person.getLocationX(), person.getLocationY(), person.getRoomLocation(), outside, inside, garden);
            }

            if (userResponse.contains("SIGN")) {
                person.signContract(inside);
            }

            if (userResponse.contains("CLOSE")) {
                person.close(person.getLocationX(), person.getLocationY(), outside, inside, garden, person.getRoomLocation());
            }

            if (userResponse.contains("CHEW")) {
                stillPlaying = !person.chew(garden);
            }

            if (userResponse.contains("SHRINK")) {
                stillPlaying = !person.shrink(garden);
            }

            if (userResponse.contains("BOARD") || userResponse.contains("GET ON")) {
                stillPlaying = !person.boardElevator(garden);
            }

            if (person.getRoomLocation().equals("Garden")) {
                garden.isAtBridge(person.getLocationX(), person.getLocationY());
            }

            if (stillPlaying) {
                stillPlaying = !person.chocolateRiver(garden);
            }

        } while (stillPlaying);
        
    }
}
