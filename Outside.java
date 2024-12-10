public class Outside{
//Attributes 
boolean goldenTicket; 
boolean doorOpen; 
private Item goldenTicketItem;
/** 
 * Constructor for Outside 
 */

public Outside(){
    this.goldenTicket = false; 
    this.doorOpen = false; 
    this.goldenTicketItem = new Item("Golden Ticket", "A shimmering golden ticket to enter the factory.", 0, 0, "Outside");
}

public void interact(Person player) {
    if (player.getLocationX() == 0 && player.getLocationY() == 0 && !goldenTicket) {
        System.out.println("You found the golden ticket!");
        //some code here if they grab the ticket?? 
    }
    if (goldenTicket && player.getLocationX() == 2 && player.getLocationY() == 2) {
        System.out.println("A door appears!");
        //some code here to open door?
    }
}

public boolean getDoorOpenOutside() {
    return doorOpen;
}

public void setDoorOpenOutside(boolean doorState) {
    doorOpen = doorState;
}
}