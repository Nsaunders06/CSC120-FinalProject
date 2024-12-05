import java.util.ArrayList;

public class Person {
//attributes 
int locationX; 
int locationY; 
//Arraylist <items> inventory; 
String roomLocation; 
//inventory to hold items
private ArrayList<Item> inventory;

/**
 * Constructor 
 */
public Person(String roomLocation){
    this.locationX = -2; 
    this.locationY = -2; 
    this.roomLocation = roomLocation; 
    this.inventory = new ArrayList<Item>(); 
}

//This should connect to a scanner eventually (probably in a file called Game.java or something)
public boolean open(int locationX, int locationY, String room){
    if (room.equals("Outside") && locationX == 2 && locationY == 2) {
        System.out.println("The door opens!");
        return true;
    }
    //Could add more else statements if there are more things that need to be opened
    System.out.println("You cannot open anything here.");
    return false; 
} // Come back and fix this once more stuff is in place 

public void grab(Item item){ 
    if (item != null) {
        inventory.add(item);
        System.out.println("You picked up: " + item.getName());
    } else {
        System.out.println("There's nothing to grab here.");
    }
}

public void viewInventory() { 
    System.out.println("Inventory:");
    for (Item item : inventory) {
        System.out.println("- " + item.getName());
    }
}

public String viewLocation(){
    return getLocationX() + "," + getLocationY() + ")";
}

public void move(int deltaX, int deltaY) {

}
/*Getters and setters
getLocationX and getLocationY (int)
getRoomLocation (String)
setRoomLocation (void)
*/
public int getLocationX() {
    return locationX;
}

public int getLocationY() {
    return locationY;
}
public String getRoomLocation() {
    return roomLocation;
}

public void setRoomLocation(String newRoomLocation) {
    if (newRoomLocation.equalsIgnoreCase("Garden") || newRoomLocation.equalsIgnoreCase("Outside") || newRoomLocation.equalsIgnoreCase("Inside")) {
        roomLocation = newRoomLocation;
    } else {
        throw new RuntimeException("Invalid Room Location!");
    }
}

public void lookAround() {
    if(this.getLocationX()== -2){
        System.out.println("You have a wall to your left");
    }
    if (this.getLocationX() == 2){
        System.out.println("you have a wall to your right");
    }
    if (this.getLocationY() == 2){
        System.out.println("you have a wall in front of you");
    }
    if (this.getLocationY() == -2){ 
        System.out.println("you have a wall behind you");
    }
    if (this.getRoomLocation().equals("Outside")){
        System.out.println("You also see a golden object"); //Come back and fix 
    }
    if (this.getRoomLocation().equals("Garden")){
        System.out.println("You see a chocolate river");
    }
    if (this.getRoomLocation().equals("Inside")){
        System.out.println("You see in front of you an easel and some sort of pen?");
    }
}

}