
import java.util.ArrayList;
import java.util.Random;

public class Outside {

    //Attributes 
    private boolean goldenTicket;
    private boolean doorOpen;
    private ArrayList<int[]> chocolateBars;
    private int[] goldenTicketLocation;

    //private Item goldenTicketItem;
    /**
     * Constructor for Outside
     */

    public Outside() {
        this.goldenTicket = false;
        this.doorOpen = false;
        this.chocolateBars = new ArrayList<>();
        placeChocolateBars();
        //this.goldenTicketItem = new Item("Golden Ticket", "A shimmering golden ticket to enter the factory.", 0, 0, "Outside", false);
    }

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

    public boolean checkLocationForChocolate(int x, int y) {
        for (int[] location : chocolateBars) {
            if (location[0] == x && location[1] == y) {
                return true;
            }
        }
        return false;
    }

    public boolean checkGoldenTicket(int x, int y) {
        return (x == goldenTicketLocation[0] && y == goldenTicketLocation[1]);
    }

    public boolean getDoorOpenOutside() {
        return doorOpen;
    }

    public void setDoorOpenOutside(boolean doorState) {
        doorOpen = doorState;
    }

    public void setGoldenTicketState(boolean ticketState) {
        goldenTicket = ticketState;
    }

    public boolean getGoldenTicketState() {
        return goldenTicket;
    }
}
