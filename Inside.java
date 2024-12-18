
import java.util.Random;

public class Inside {

    //Attributes
    private boolean entranceDoorOpen;
    private boolean exitDoorOpen;
    private boolean contractSigned;
    private boolean penFound;
    private int[] penLocation;
    private int[] contractLocation;

    //Constructor
    public Inside() {
        this.entranceDoorOpen = false;
        this.exitDoorOpen = false;
        this.contractSigned = false;
        this.penFound = false;
        placeItems();
    }

    public boolean getDoorOpenInsideToOutside() {
        return entranceDoorOpen;
    }

    public void setDoorOpenInsideToOutside(boolean doorState) {
        entranceDoorOpen = doorState;
    }

    public boolean getDoorOpenInsideToGarden() {
        return exitDoorOpen;
    }

    public void setDoorOpenInsideToGarden(boolean doorState) {
        exitDoorOpen = doorState;
    }

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

    public boolean checkLocationForItem(int x, int y) {
        if (x == penLocation[0] && y == penLocation[1]) {
            return true; // Found the pen
        }
        return false;
    }

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

    // Getter and Setter for penFound
    public boolean isPenFound() {
        return penFound;
    }

    public void setPenFound(boolean penFound) {
        this.penFound = penFound;
    }


    // Getter for contractSigned
    public boolean isContractSigned() {
        return contractSigned;
    }

    public void setContractSigned(boolean state) {
        contractSigned = state;
    }

    public int getContractLocationX() {
        return contractLocation[0];
    }

    public int getContractLocationY() {
        return contractLocation[1];
    }

    public int getPenLocationX() {
        return penLocation[0];
    }

    public int getPenLocationY() {
        return penLocation[1];
    }
}
