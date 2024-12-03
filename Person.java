import java.util.ArrayList;

public class Person {
//attributes 
int locationX; 
int locationY; 
//Arraylist <items> inventory; 
String roomLocation; 
String input;

/**
 * Constructor 
 */
public Person(String roomLocation, String input){
    this.locationX = -2; 
    this.locationY = -2; 
    this.roomLocation = roomLocation; 
    this.input = input; 
}

public boolean open(int locationX, int locationY, String room){
    return false; 
} // Come back and fix this once more stuff is in place 

public Item grab(int locationX, int locationY, String room){ 
    // This will fist check if there is an item there and then will add the item to the inventory array list 
}

public void viewInventory (Items inventory){ 
    System.out.println(inventory);
}

public void viewLocation (int locationX, int locationY){
    System.out.println("You are at" + locationX + "," + locationY);
}

public void move (String direction, int amount){ 
    // Move the person in the direction given by the number of units noted 
    // error handling --> if user goes outside the given coordinate plane print error 
}

}