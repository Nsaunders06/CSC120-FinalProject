public class Item {
    private String name;
    private String description;
    private int locationX; 
    private int locationY;
    private String room;

    public Item(String name, String description, int locationX, int locationY, String room) {
        this.name = name;
        this.description = description;
        this.locationX = locationX; 
        this.locationY = locationY; 
        this.room = room;
    }


    /*
     * Getters and setters
     */
    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getX() {
        return locationX;
    }

    public int getY() {
        return locationY;
    }

    public String getRoom() {
        return room;
    }

}
