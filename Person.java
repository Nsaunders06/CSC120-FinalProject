import java.util.ArrayList;

public class Person {
//attributes 
int locationX; 
int locationY; 
//Arraylist <items> inventory; 
String roomLocation; 
//inventory to hold items
private ArrayList<Item> inventory;
//Inside inside = new Inside();
//Outside outside = new Outside()
/**
 * Constructor 
 */
public Person(String roomLocation){
    this.locationX = -2; 
    this.locationY = -2; 
    this.roomLocation = roomLocation; 
    this.inventory = new ArrayList<Item>(); 
}

//This says if the door is open or not 
public void open(int locationX, int locationY, String room, Outside outside, Inside inside){
    if (room.equals("Outside") && locationX == 2 && locationY == 2) {
        outside.setDoorOpenOutside(true);
        inside.setDoorOpenInsidetoOutside(true);
        System.out.println("The door opens!");
    }
    //Could add more else statements if there are more things that need to be opened
    System.out.println("You cannot open anything here.");
} // Come back and fix this once more stuff is in place 

public void grab(ArrayList<Item> gameItems) { 
    Item foundItem = null;
    for (Item item : gameItems) {
        if (item.getX() == this.getLocationX() && item.getY() == this.getLocationY() && item.getRoom().equals(this.getRoomLocation())) {
            foundItem = item;
            break;
        }
    }

    if (foundItem != null) {
        inventory.add(foundItem);
        gameItems.remove(foundItem); // Remove the item from the world
        System.out.println("You picked up: " + foundItem.getName());
    } else {
        System.out.println("There's nothing to grab here.");
    }
}

public void viewInventory() { 
    if (inventory.size() != 0) {
        System.out.println("Inventory:");
        for (Item item : inventory) {
            System.out.println("- " + item.getName());
        }
    } else {
        System.out.println("There is nothing in your inventory at this time!");
    }
    
}

public String viewLocation(){
    return getLocationX() + "," + getLocationY() + ")";
}
/*
public void move(int deltaX, int deltaY) {
    int newX = locationX + deltaX;
    int newY = locationY + deltaY;
}
*/
public void move(String direction, int delta, Outside outside, Inside inside) {
    int newX = locationX;
    int newY = locationY;
    String newRoom = roomLocation;
    if (direction.toUpperCase().equals("FORWARD") || direction.toUpperCase().equals("UP") || direction.toUpperCase().equals("NORTH")) {
        newY = locationY + delta;
    } else if (direction.toUpperCase().equals("BACK") || direction.toUpperCase().equals("DOWN") || direction.toUpperCase().equals("SOUTH")) {
        newY = locationY - delta; 
    } else if (direction.toUpperCase().equals("LEFT") || direction.toUpperCase().equals("WEST")) {
        newX = locationX - delta;
    } else if (direction.toUpperCase().equals("RIGHT") || direction.toUpperCase().equals("EAST")) {
        newX = locationX + delta;
    } else {
        throw new RuntimeException("This is not a valid direction, please enter one of the following directions:\nForward, Up, North\nBack, Down, South\nLeft, West\nRight, East"); 
    }

   // System.out.println("Check 1 in move");
    // Check for room transition at door locations
if (roomLocation.equals("Outside") && newX > 2 && newY == 2) {
    // Moving from Outside to Inside
    if (!outside.getDoorOpenOutside()) {
        System.out.println("The door is closed. You cannot enter.");
        return;
    } else {
        newRoom = "Inside";
        System.out.println("You move through the door into the Inside.");
    }
} else if (roomLocation.equals("Inside") && newX < -2 && newY < -2) {
    // Moving from Inside to Outside
    if (!inside.getDoorOpenInsidetoOutside()) {
        System.out.println("The door is closed. You cannot exit.");
        return;
    } else {
        newRoom = "Outside";
        System.out.println("You move through the door into the Outside.");
    }
}

    if (newX < -2 || newX > 2 || newY < -2 || newY > 2) {
    System.out.println("You can't move that far in that direction!");
    return;
    }


// Update position and room
setLocationX(newX);
setLocationY(newY);
if (!newRoom.equals(roomLocation)) {
    setRoomLocation(newRoom);
}

System.out.println("You moved to (" + locationX + "," + locationY + ") in the " + roomLocation + ".");

}
//Getters and setters

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

public void setLocationX(int newLocX) {
    locationX = newLocX;
}

public void setLocationY(int newLocY) {
    locationY = newLocY;
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
        System.out.println("You see a golden object"); //Come back and fix 
    }
    if (this.getRoomLocation().equals("Garden")){
        System.out.println("You see a chocolate river");
    }
    if (this.getRoomLocation().equals("Inside")){
        System.out.println("You see in front of you an easel and some sort of pen?");
    }
}
/* 
public void openDoor() {
    //Check if the player is at one of the door locations
    if((x == 2 && y == 2 && getRoomLocation().equals("Outside"))) {
        setDoorOpenOutside(true);
        System.out.println("The door is now open! You can move between the locations.");
    }
}
*/

}