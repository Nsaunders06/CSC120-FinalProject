
import java.util.ArrayList;
import java.util.Random;

public class Inside {

    //Attributes
    private boolean entranceDoorOpen;
    private boolean exitDoorOpen;
    private boolean contractSigned;
    private boolean penFound;
    private ArrayList<int[]> riddles;
    private String currentRiddle;
    private int[] penLocation;
    private int[] contractLocation;

    //Constructor
    public Inside() {
        this.entranceDoorOpen = false;
        this.exitDoorOpen = false;
        this.contractSigned = false;
        this.penFound = false;
        this.riddles = new ArrayList<>();
        placeItems();
        placeRiddles();

    }

    public boolean getDoorOpenInsidetoOutside() {
        return entranceDoorOpen;
    }

    public void setDoorOpenInsidetoOutside(boolean doorState) {
        entranceDoorOpen = doorState;
    }

    public boolean getDoorOpenInsidetoGarden() {
        return exitDoorOpen;
    }

    public void setDoorOpenInsidetoGarden(boolean doorState) {
        exitDoorOpen = doorState;
    }

    private void placeItems() {
        Random rand = new Random();
        int numberOfItems = 3;
        this.penLocation = new int[]{rand.nextInt(5) - 2, rand.nextInt(5) - 2};
        this.contractLocation = new int[]{rand.nextInt(5) - 2, rand.nextInt(5) - 2};
    }

    //Places riddles in the room
    private void placeRiddles() {
        Random rand = new Random();
        int numberOfRiddles = 3;

        while (riddles.size() < numberOfRiddles) {
            int x = rand.nextInt(5) - 2;
            int y = rand.nextInt(5) - 2;

            if (!(x == -2 && y == -2)) {
                boolean alreadyPlaced = false;
                for (int[] location : riddles) {
                    if (location[0] == x && location[1] == y) {
                        alreadyPlaced = true;
                        break;
                    }
                }
                if (!alreadyPlaced) {
                    riddles.add(new int[]{x, y});
                }
            }
        }
    }

    public boolean checkLocationForItem(int x, int y) {
        if (x == penLocation[0] && y == penLocation[1]) {
            return true; // Found the pen
        }
        return false;
    }

    public boolean checkLocationForRiddle(int x, int y) {
        for (int[] location : riddles) {
            if (location[0] == x && location[1] == y) {
                return true;
            }
        }
        return false;
    }

    // Provides a riddle for the player to solve
    public String getRiddle() {
        String[] riddleList = {
            "What has to be broken before you can use it? (Hint: Found in the kitchen)",
            "The more you take, the more you leave behind. What am I?",
            "I'm tall when I'm young, and I'm short when I'm old. What am I?"
        };
        Random rand = new Random();
        currentRiddle = riddleList[rand.nextInt(riddleList.length)];
        return currentRiddle;
    }

    // Checks if the riddle answer is correct
    public boolean checkRiddleAnswer(String answer) {
        return switch (currentRiddle) {
            case "What has to be broken before you can use it? (Hint: Found in the kitchen)" -> answer.equalsIgnoreCase("egg");
            case "The more you take, the more you leave behind. What am I?" -> answer.equalsIgnoreCase("footsteps");
            case "I’m tall when I’m young, and I’m short when I’m old. What am I?" -> answer.equalsIgnoreCase("candle");
            default -> false;
        };
    }

    // Signs the contract if the pen is found
    public boolean signContract(int x, int y) {
        if (penFound && x == contractLocation[0] && y == contractLocation[1]) {
            this.contractSigned = true;
            System.out.println("You signed the contract!");
            return true;
        } else {
            System.out.println("You need to find the pen to sign the contract.");
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

}
