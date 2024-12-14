public class Outside{
//Attributes 
boolean goldenTicket; 
boolean doorOpen; 
//private Item goldenTicketItem;
/** 
 * Constructor for Outside 
 */

public Outside(){
    this.goldenTicket = false; 
    this.doorOpen = false; 
    //this.goldenTicketItem = new Item("Golden Ticket", "A shimmering golden ticket to enter the factory.", 0, 0, "Outside", false);
}
//I'm not actually sure if this is ever used...we can make it be used but that would just mean it automatically tells them the ticket and door are there instead of them having to look around
public void interact(Person player) {
    if (player.getLocationX() == 0 && player.getLocationY() == 0 && !goldenTicket) {
        System.out.println("You found the golden ticket!");
        //some code here if they grab the ticket?? 
    }
    if (goldenTicket && player.getLocationX() == 2 && player.getLocationY() == 2) {
        System.out.println("A door appears!");
       
    }
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