
public class Item {

    private String name;
    private String description;
    private int locationX;
    private int locationY;
    private String room;

    /**
     * Constructor for the item class
     *
     * @param name
     * @param description
     * @param locationX
     * @param locationY
     * @param room
     */
    public Item(String name, String description, int locationX, int locationY, String room) {
        this.name = name;
        this.description = description;
        this.locationX = locationX;
        this.locationY = locationY;
        this.room = room;
    }

    /**
     * getter for the items name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for the items description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets the x location of the item
     *
     * @return locationX
     */
    public int getX() {
        return locationX;
    }

    /**
     * gets the y location of the item
     *
     * @return locationY
     */
    public int getY() {
        return locationY;
    }

    /**
     * gets the room location of the item
     *
     * @return room
     */
    public String getRoom() {
        return room;
    }

    /**
     * Sets the x coordinate of the item
     *
     * @param x
     */
    public void setLocationX(int x) {
        this.locationX = x;
    }

    /**
     * Sets the y coordinate of the item
     *
     * @param y
     */
    public void setLocationY(int y) {
        this.locationY = y;
    }

    /**
     * Sets the room location of the item
     *
     * @param room
     */
    public void setRoom(String room) {
        this.room = room;
    }

}
