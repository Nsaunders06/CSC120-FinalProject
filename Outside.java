
import java.util.ArrayList;
import java.util.Random;

public class Outside {

    //Attributes 
    private boolean goldenTicket;
    private boolean doorOpen;
    private ArrayList<int[]> chocolateBars;
    private int[] goldenTicketLocation;

    /**
     * Constructor for Outside
     */

    public Outside() {
        this.goldenTicket = false;
        this.doorOpen = false;
        this.chocolateBars = new ArrayList<>();
        placeChocolateBars();
    }
/**
 * places the location of the chololate bars randomly 
 */
    private void placeChocolateBars() {
        Random rand = new Random();
        int numberOfBars = 5; // Number of chocolate bars to place

        while (chocolateBars.size() < numberOfBars) {
            int x = rand.nextInt(5) - 2;
            int y = rand.nextInt(5) - 2;

            if (!(x == -2 && y == -2)) {
                boolean alreadyPlaced = false;
                for (int[] location : chocolateBars) {
                    if (location[0] == x && location[1] == y) {
                        alreadyPlaced = true;
                        break;
                    }
                }
                if (!alreadyPlaced) {
                    chocolateBars.add(new int[]{x, y});
                }
            }
        }
        //Randomly assign one chocolate bar the golden ticket
        goldenTicketLocation = chocolateBars.get(rand.nextInt(chocolateBars.size()));
    }

/**
 * checks if there is a chocolate bar at a location 
 * @param x
 * @param y
 * @return if there is a chocolate bar 
 */
    public boolean checkLocationForChocolate(int x, int y) {
        for (int[] location : chocolateBars) {
            if (location[0] == x && location[1] == y) {
                return true;
            }
        }
        return false;
    }
/**
 * Checks if there is a golden ticket inside the chocolate bar selected 
 * @param x
 * @param y
 * @return if there is a golden ticket 
 */
    public boolean checkGoldenTicket(int x, int y) {
        return (x == goldenTicketLocation[0] && y == goldenTicketLocation[1]);
    }
/**
 * Checks if the door to outside is open 
 * @return doorOpen
 */
    public boolean getDoorOpenOutside() {
        return doorOpen;
    }
/**
 * Sets the door to outside open 
 * @param doorState
 */
    public void setDoorOpenOutside(boolean doorState) {
        doorOpen = doorState;
    }
/**
 * Sets the golden ticket state to the corect state 
 * @param ticketState
 */
    public void setGoldenTicketState(boolean ticketState) {
        goldenTicket = ticketState;
    }
/**
 * gets the state of the golden ticket 
 * @return state of the goldenTicket 
 */
    public boolean getGoldenTicketState() {
        return goldenTicket;
    }
}
