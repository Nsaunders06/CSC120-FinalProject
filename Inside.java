
import java.util.Random;

public class Inside {
    private boolean entranceDoorOpen;
    private boolean exitDoorOpen;
    private boolean contractSigned;
    private boolean penFound;
    private int[] penLocation;
    private int[] contractLocation;

/**
 * Constructor for Inside 
 */
    public Inside() {
        this.entranceDoorOpen = false;
        this.exitDoorOpen = false;
        this.contractSigned = false;
        this.penFound = false;
        placeItems();
    }
/**
 * Get if the door inside into outside is open 
 * @return entrenceDoorOpen 
 */
    public boolean getDoorOpenInsideToOutside() {
        return entranceDoorOpen;
    }
/**
 * sets the door for inside into outside to open 
 * @param doorState
 */
    public void setDoorOpenInsideToOutside(boolean doorState) {
        entranceDoorOpen = doorState;
    }
/**
 * Gets if the door to inside to garden is open 
 * @return exitDoorOpen
 */
    public boolean getDoorOpenInsideToGarden() {
        return exitDoorOpen;
    }
/**
 * sets the door from inside to garden to open 
 * @param doorState
 */
    public void setDoorOpenInsideToGarden(boolean doorState) {
        exitDoorOpen = doorState;
    }
/**
 * places items in the room 
 */
    private void placeItems() {
        Random rand = new Random();
        this.penLocation = new int[]{rand.nextInt(5) - 2, rand.nextInt(5) - 2};
        boolean contractPlaced = false;
        while (!contractPlaced) {
            int x = rand.nextInt(5) - 2;
            int y = rand.nextInt(5) - 2;
            if (x != penLocation[0] && y != penLocation[1]) {
                this.contractLocation = new int[]{x, y};
                contractPlaced = true;
            }
        }
    }
/**
 * Looks to see the pen is at the location 
 * @param x 
 * @param y
 * @return boolean for if you have pen 
 */
    public boolean checkLocationForItem(int x, int y) {
        if (x == penLocation[0] && y == penLocation[1]) {
            return true; // Found the pen
        }
        return false;
    }
/**
 * Signs the contract if the user has the pen 
 * @param x
 * @param y
 * @return boolean for if the contract is signed 
 */
    // Signs the contract if the pen is found
    public boolean signContract(int x, int y) {
        if (penFound && x == contractLocation[0] && y == contractLocation[1]) {
            setContractSigned(true);
            System.out.println("You signed the contract!");
            return true;
        } else if ((!(x == contractLocation[0]) || !(y == contractLocation[1])) && penFound) {
            System.out.println("You have found the pen, but the contract is not here!");
            return false;
        } else if (!(x == contractLocation[0]) || !(y == contractLocation[1]) && !penFound) {
            System.out.println("You need to find the pen to be able to sign the contract! Also, the contract is not here!");
            return false;
        } else {
            System.out.println("You need to find the pen to sign the contract!");
            return false;
        }
    }

/**
 * Gets is the pen is found 
 * @return penFound 
 */
    // Getter and Setter for penFound
    public boolean isPenFound() {
        return penFound;
    }
/**
 * Sets the pen as found 
 * @param penFound
 */
    public void setPenFound(boolean penFound) {
        this.penFound = penFound;
    }

/**
 * Gets if contract is signed 
 * @return contractSigned 
 */
    // Getter for contractSigned
    public boolean isContractSigned() {
        return contractSigned;
    }
/**
 * Sets the contract to signed 
 * @param state
 */
    public void setContractSigned(boolean state) {
        contractSigned = state;
    }
/**
 * gets the x coordinate for the contract 
 * @return int location of the x 
 */
    public int getContractLocationX() {
        return contractLocation[0];
    }
/**
 * gets the y coordinate for the contract 
 * @return int location of the y 
 */
    public int getContractLocationY() {
        return contractLocation[1];
    }
/**
 * gets the x coordinate for the pen 
 * @return int location of the x 
 */
    public int getPenLocationX() {
        return penLocation[0];
    }
/**
 * gets the y coordinate for the pen 
 * @return int location of the y 
 */
    public int getPenLocationY() {
        return penLocation[1];
    }
}
