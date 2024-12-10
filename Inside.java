public class Inside {
    //Attributes
    boolean entranceDoorOpen;
    boolean exitDoorOpen;
    
    //Constructor
    public Inside() {
        this.entranceDoorOpen = false;
        this.exitDoorOpen = false;
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
}
