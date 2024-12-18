
public class Garden {

    //Coordinated of objects and hazards
    private final int[][] chocolateRiver = {{-2, 2}, {-1, 1}, {1, -1}, {2, -2}};
    private final int[] gumballMachine = {-2, 0};
    private final int[] tvCamera = {-1, -2};
    private final int[] bridge = {0, 0};
    private final int[] elevator = {2, 1};
    private boolean entranceDoorOpen;

    //Constructor
    public Garden() {
        this.entranceDoorOpen = false;
    }

    //Check if the player falls into the chocolate river
    public boolean isInChocolateRiver(int x, int y) {
        for (int[] cords : chocolateRiver) {
            if (cords[0] == x && cords[1] == y) {
                System.out.println("You fell in the chocolate river. Game over!");
                return true;
            }
        }
        return false;
    }

    //Check if the player is at the gumball machine
    public boolean isAtGumballMachine(int x, int y) {
        if (x == gumballMachine[0] && y == gumballMachine[1]) {
            System.out.println("You chewed the gumball! As you continue to chew, you notice yourself turning purple and growing rounder and rounder rapidly. You've turned into a blueberry! Time to go get juiced! You will be purple-ish for a while though. Because of medical complications, you needed to leave. Game over!");
        } else {
            System.out.println("There is no gumball machine here! But you probably shouldn't just go around chewing random gum in a strange factory...do what you want though...but don't say you haven't been warned.");
        }
        return (x == gumballMachine[0] && y == gumballMachine[1]);
    }

    //Check if the player is at the TV camera
    public boolean isAtTVCamera(int x, int y) {
        if (x == tvCamera[0] && y == tvCamera[1]) {
            System.out.println("Don't play with technology you've never seen before! Especially in a strange factory. Now you're tiny! Time to go get flattened out to be a normal height again! Because of medical complications, you needed to leave. Game over!");
            return true;
        } else {
            System.out.println("There is no TV Camera here to play with. Maybe don't play with strange machinery in a factory though...do what you want...but don't say you weren't warned.");
        }
        return false;
    }

    //Check if the player is at the bridge
    public void isAtBridge(int x, int y) {
        if (x == bridge[0] && y == bridge[1]) {
            System.out.println("You are on the bridge. You safely cross the chocolate river.");
        }
    }

    //Check if the player is at the boat
    public boolean isAtElevator(int x, int y) {
        if (x == elevator[0] && y == elevator[1]) {
            System.out.println("You have boarded the elevator! The factory is yours, all yours! Congratulations! You win!");
            return true;
        }
        return false;
    }

    public boolean getDoorOpenGardenToInside() {
        return entranceDoorOpen;
    }

    public void setDoorOpenGardenToInside(boolean doorState) {
        entranceDoorOpen = doorState;
    }

    public String getChocolateRiverDirection(int x, int y) {
        
        for (int[] point : chocolateRiver) {
            int dx = point[0] - x;
            int dy = point[1] - y;
    
            if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1) { // Adjacent position
                if (dx == -1 && dy == 0) return "west";
                if (dx == 1 && dy == 0) return "east";
                if (dx == 0 && dy == -1) return "south";
                if (dx == 0 && dy == 1) return "north";
                if (dx == -1 && dy == 1) return "northwest";
                if (dx == 1 && dy == 1) return "northeast";
                if (dx == -1 && dy == -1) return "southwest";
                if (dx == 1 && dy == -1) return "southeast";
            }
        }
        return null; // No river nearby
    }
}
