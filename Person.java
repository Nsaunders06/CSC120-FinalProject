
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
    public Person(String roomLocation) {
        this.locationX = -2;
        this.locationY = -2;
        this.roomLocation = roomLocation;
        this.inventory = new ArrayList<Item>();
    }

//This says if the door is open or not 
    public void open(int locationX, int locationY, String room, Outside outside, Inside inside) {
        if (room.equals("Outside") && locationX == 2 && locationY == 2 && outside.getGoldenTicketState()) {
            outside.setDoorOpenOutside(true);
            inside.setDoorOpenInsidetoOutside(true);
            System.out.println("The door opens!");
        } else { //Could add more else statements if there are more things that need to be opened
            System.out.println("You cannot open anything here.");
        }
    } // Come back and fix this once more stuff is in place 

    public void grab(ArrayList<Item> gameItems, Outside outside) {
        Item foundItem = null;
        //Check for items in the gameItems list
        for (Item item : gameItems) {
            if (item.getX() == this.getLocationX() && item.getY() == this.getLocationY() && item.getRoom().equals(this.getRoomLocation())) {
                foundItem = item;
                break;
            }
        }
        //If an item is found, pick it up
        if (foundItem != null) {
            inventory.add(foundItem);
            gameItems.remove(foundItem); // Remove the item from the world
            System.out.println("You picked up: " + foundItem.getName());
        } //If no item is found, check for chocolate bars in Outside
        else if (this.getRoomLocation().equals("Outside")) {
            int x = this.getLocationX();
            int y = this.getLocationY();

            if (outside.checkLocationForChocolate(x, y)) {
                if (outside.checkGoldenTicket(x, y)) {
                    System.out.println("You found the golden ticket!");
                    outside.setGoldenTicketState(true);
                } else {
                    System.out.println("You picked up a regular chocolate bar.");
                }
            } else {
                System.out.println("There's nothing here to grab.");
            }

        }
        //If no item or chocolate bar is found
        else {
            System.out.println("There's nothing here to grab.");
        }
    }

    public void drop(String itemName, ArrayList<Item> gameItems) {
        Item droppedItem = null;

        //Search for the item in the inventory
        for(Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                droppedItem = item;
                break;
            }
        } 

        //Drop the item if found
        if (droppedItem != null) {
            inventory.remove(droppedItem); //Remove from inventory
            droppedItem.setLocationX(this.getLocationX());
            droppedItem.setLocationY(this.getLocationY());
            droppedItem.setRoom(this.getRoomLocation());
            gameItems.add(droppedItem);
            System.out.println("You dropped: " + droppedItem.getName());
        } else {
            System.out.println("You don't have that item in your inventory.");
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

    public String viewLocation() {
        return "(" + getLocationX() + "," + getLocationY() + ")";
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
        boolean roomChange = false;
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
                //roomChange = true;
                if (newX != locationX) {
                    newX = (2 - newX);
                    newY = -2;
                }

            }
        } else if (roomLocation.equals("Inside") && newX < -2 && newY < -2) {
            // Moving from Inside to Outside
            if (!inside.getDoorOpenInsidetoOutside()) {
                System.out.println("The door is closed. You cannot exit.");
                return;
            } else {
                newRoom = "Outside";
                System.out.println("You move through the door into the Outside.");
                //roomChange = true;
            }
        } else if (newX < -2 || newX > 2 || newY < -2 || newY > 2) {
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

    public String getInventory() {
        String itemListNames = "";
        for (int i = 0; i < inventory.size(); i++) {
            itemListNames += inventory.get(i).getName() + " ";
        }
        return itemListNames;
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
        if (this.getLocationX() == -2) {
            System.out.println("You have a wall directly to your left");
        }
        if (this.getLocationX() == 2) {
            System.out.println("you have a wall directly to your right");
        }
        if (this.getLocationY() == 2) {
            System.out.println("you have a wall directly in front of you");
        }
        if (this.getLocationY() == -2) {
            System.out.println("you have a wall directly behind you");
        }
        if (this.getRoomLocation().equals("Outside") && this.getLocationX() == 0 && this.getLocationY() == 0) {
            System.out.println("You see a golden object"); //Come back and fix 
        }
        if (this.getRoomLocation().equals("Garden")) {
            System.out.println("You see a chocolate river");
        }
        if (this.getRoomLocation().equals("Inside")) {
            System.out.println("You see in front of you an easel and some sort of pen?");
        }
        if (this.getRoomLocation().equals("Outside") && this.getLocationX() == 2 && this.getLocationY() == 2) {
            System.out.println("You see a door to your right.");
        }
    }

}
