
import java.util.ArrayList;

public class Person {

    //attributes 
    int locationX;
    int locationY;
    String roomLocation;
    //inventory to hold items
    private ArrayList<Item> inventory;

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
    public void open(int locationX, int locationY, String room, Outside outside, Inside inside, Garden garden) {
        if (room.equals("Outside") && locationX == 2 && locationY == 2) {
            if (outside.getGoldenTicketState()) {
                if (!outside.getDoorOpenOutside()) {
                    outside.setDoorOpenOutside(true);
                    inside.setDoorOpenInsideToOutside(true);
                    System.out.println("The door opens!");
                } else {
                    System.out.println("The door is already open!");
                }
            } else {
                System.out.println("You must have the golden ticket to open the door!");
            }
        } else if (room.equals("Inside") && locationX == -2 && locationY == -2) {
            if (!outside.getDoorOpenOutside()) {
                outside.setDoorOpenOutside(true);
                inside.setDoorOpenInsideToOutside(true);
                System.out.println("The door opens!");
            } else {
                System.out.println("The door is already open!");
            }
        } else if (room.equals("Inside") && locationX == 2 && locationY == -2) {
            if (inside.isContractSigned() && !inside.getDoorOpenInsideToGarden()) {
                inside.setDoorOpenInsideToGarden(true);
                garden.setDoorOpenGardenToInside(true);
                System.out.println("The door opens!");
            } else if (inside.getDoorOpenInsideToGarden()) {
                System.out.println("The door is already open!");
            } else if (!inside.isContractSigned()) {
                System.out.println("You must sign the contract in order to open the door!");
            }
        } else if (room.equals("Garden") && locationX == -2 && locationY == -2) {
            if (!inside.getDoorOpenInsideToGarden()) {
                inside.setDoorOpenInsideToGarden(true);
                garden.setDoorOpenGardenToInside(true);
            } else {
                System.out.println("This door is already open!");
            }
        } else {
            System.out.println("You cannot open anything here.");
        }
    }

    //This says if the door is open or not 
    public void close(int locationX, int locationY, Outside outside, Inside inside, Garden garden, String room) {
        if (room.equals("Outside") && locationX == 2 && locationY == 2) {
            if (outside.getGoldenTicketState()) {
                if (outside.getDoorOpenOutside()) {
                    outside.setDoorOpenOutside(false);
                    inside.setDoorOpenInsideToOutside(false);
                    System.out.println("The door is now closed!");
                } else {
                    System.out.println("The door is already closed!");
                }
            } else {
                System.out.println("You cannot do anything without the golden ticket.");
            }
        } else if (room.equals("Inside") && locationX == -2 && locationY == -2) {
            if (outside.getDoorOpenOutside()) {
                outside.setDoorOpenOutside(false);
                inside.setDoorOpenInsideToOutside(false);
                System.out.println("The door closes!");
            } else {
                System.out.println("The door is already closed!");
            }
        } else if (room.equals("Inside") && locationX == 2 && locationY == -2) {
            if (inside.getDoorOpenInsideToGarden()) {
                inside.setDoorOpenInsideToGarden(false);
                garden.setDoorOpenGardenToInside(false);
            } else if (!inside.getDoorOpenInsideToGarden()) {
                System.out.println("The door is already closed!");
            } else if (!inside.isContractSigned()) {
                System.out.println("You must sign the contract in order to interact with the door!");
            }
        } else if (room.equals("Garden") && locationX == -2 && locationY == -2) {
            if (!inside.getDoorOpenInsideToGarden()) {
                inside.setDoorOpenInsideToGarden(true);
                garden.setDoorOpenGardenToInside(true);
            } else {
                System.out.println("This door is already open!");
            }
        } else {
            System.out.println("You cannot open anything here.");
        }
    }

    public void grab(ArrayList<Item> gameItems, Outside outside, Inside inside) {
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
            if (foundItem.getName().equals("Pen")) {
                inside.setPenFound(true);
            }
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

        } //If no item or chocolate bar is found
        else {
            System.out.println("There's nothing here to grab.");
        }
    }

    public void drop(String itemName, ArrayList<Item> gameItems) {
        Item droppedItem = null;

        //Search for the item in the inventory
        for (Item item : inventory) {
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

    public void move(String direction, int delta, Outside outside, Inside inside, Garden garden) {
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

        // Check for room transition at door locations
        if (roomLocation.equals("Outside") && newX > 2 && newY == 2) {
            // Moving from Outside to Inside
            if (!outside.getDoorOpenOutside()) {
                System.out.println("The door is closed. You cannot enter.");
                return;
            } else {
                newRoom = "Inside";
                System.out.println("You move through the door into the Inside.");
                if (newX != locationX) {
                    newX = (2 - newX);
                    newY = -2;
                }

            }
        } else if (roomLocation.equals("Inside") && newX < -2 && newY == -2) {
            // Moving from Inside to Outside
            if (!inside.getDoorOpenInsideToOutside()) {
                System.out.println("The door is closed. You cannot exit.");
                return;
            } else {
                newRoom = "Outside";
                newX += 4;
                newY = 2;
                System.out.println("You move through the door into the Outside.");
            }
        } else if (roomLocation.equals("Inside") && newX > 2 && newY == -2) {
            //Moving from Inside to Garden
            if (!inside.getDoorOpenInsideToGarden()) {
                System.out.println("The door is closed. You cannot exit.");
                return;
            } else {
                newRoom = "Garden";
                newX = (2 - newX);
                newY = -2;
                System.out.println("You move through the door into the Garden.");
            }
        } else if (roomLocation.equals("Garden") && newX < -2 && newY == -2) {
            //Moving from Garden to Inside,
            if (!garden.getDoorOpenGardenToInside()) {
                System.out.println("The door is closed. You cannot exit.");
                return;
            } else {
                newRoom = "Garden";
                newX += 4;
                newY = -2;
                System.out.println("You move through the door into the Garden.");
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

    public boolean shrink(Garden garden) {
        return garden.isAtTVCamera(this.getLocationX(), this.getLocationY());
    }

    public boolean boardElevator(Garden garden) {
        if (!garden.isAtElevator(this.getLocationX(), this.getLocationY())) {
            System.out.println("There is nothing to board here!");
            return false;
        }
        return true;
    }

    public void signContract(Inside inside) {
        if (this.getRoomLocation().equals("Inside")) {
            inside.signContract(this.getLocationX(), this.getLocationY());
        } else {
            System.out.println("There is nothing to sign in this room!");
        }
    }

    public boolean chocolateRiver(Garden garden) {
        if (this.getRoomLocation().equals("Garden")) {
            return garden.isInChocolateRiver(this.getLocationX(), this.getLocationY());
        }
        return false;
    }

    public boolean chew(Garden garden) {
        if (this.getRoomLocation().equals("Garden")) {
            return garden.isAtGumballMachine(this.getLocationX(), this.getLocationY());
        } else {
            System.out.println("There is nothing to chew in this room!");
        }
        return false;
    }

    public static void displayHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("- LOOK AROUND: Observe your surroundings.");
        System.out.println("- WHERE AM I: Check your current location.");
        System.out.println("- MOVE [direction] [steps]: Move in a specified direction (e.g., MOVE FORWARD 2).");
        System.out.println("- GRAB / PICK UP [item]: Pick up an item in your current location.");
        System.out.println("- VIEW INVENTORY / INVENTORY / WHAT DO I HAVE: Check items in your inventory.");
        System.out.println("- OPEN: Open an object or door if conditions are met.");
        System.out.println("- CLOSE: Close an object or door.");
        System.out.println("- SIGN: Sign the contract if you have a pen.");
        System.out.println("- CHEW: Interact with gumballs in the Garden.");
        System.out.println("- SHRINK: Interact with the TV camera in the Garden.");
        System.out.println("- BOARD / GET ON: Board the boat to win the game.");
        System.out.println("- HELP: Display this list of commands.");
        System.out.println("\nUse commands wisely to progress in the game!");
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

    public void lookAround(Outside outside, Inside inside, Garden garden) {
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

        if (this.getRoomLocation().equals("Inside") && this.getLocationX() == inside.getContractLocationX() && this.getLocationY() == inside.getContractLocationY()) {
            System.out.println("You see a piece of paper with a line for signatures...interesting.");
        }

        if (this.getRoomLocation().equals("Outside") && outside.checkLocationForChocolate(this.getLocationX(), this.getLocationY())) {
            System.out.println("You see a chocolate bar!");
        }

        if (this.getRoomLocation().equals("Inside") && this.getLocationX() == inside.getPenLocationX() && this.getLocationY() == inside.getPenLocationY()) {
            System.out.println("You see a mysterious writing utensil");
        }

        if (this.getRoomLocation().equals("Garden")) {
            String riverDirection = garden.getChocolateRiverDirection(this.getLocationX(), this.getLocationY());
            if (riverDirection != null) {
                System.out.println("You see the chocolate river to the " + riverDirection + ". Maybe there's a bridge in the middle of the room to help you get over it...");
            }
        }

        if (this.getRoomLocation().equals("Outside") && this.getLocationX() == 2 && this.getLocationY() == 2) {
            System.out.println("You see a door to your right.");
        }

        if (this.getRoomLocation().equals("Inside") && this.getLocationX() == -2 && this.getLocationY() == -2) {
            System.out.println("You see a door to your left.");
        }

        if (this.getRoomLocation().equals("Inside") && this.getLocationX() == 2 && this.getLocationY() == -2) {
            System.out.println("You see a door to your right.");
        }

        if (this.getRoomLocation().equals("Garden") && this.getLocationX() == -2 && this.getLocationY() == -2) {
            System.out.println("You see a door to your left.");
        }

        if (this.getRoomLocation().equals("Garden") && this.getLocationX() == 0 && this.getLocationY() == 0) {
            System.out.println("You see a bridge. Maybe it can help you get over the chocolate river.");
        }

        if (this.getRoomLocation().equals("Garden") && this.getLocationX() == 2 && this.getLocationY() == 1) {
            System.out.println("You see an elevator. Who knows what will happen if you get on...");
        }

        if (this.getRoomLocation().equals("Garden") && this.getLocationX() == -1 && this.getLocationY() == -2) {
            System.out.println("You see a TV Camera. Someone is telling you not to mess with it...");
        }

        if (this.getRoomLocation().equals("Garden") && this.getLocationX() == -2 && this.getLocationY() == 0) {
            System.out.println("You see a gumball machine. You feel like someone told you not to eat anything without expressed permission...");
        }

    }

}
