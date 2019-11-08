package scripts.api.painting.enums;


//Created by Fluffee
public enum PaintLocation {

    BOTTOM_LEFT_PLAY_SCREEN(5, 337, true, false),
    BOTTOM_RIGHT_PLAY_SCREEN(513, 337, true, true),
    TOP_LEFT_PLAY_SCREEN(5, 5, false, false),
    TOP_RIGHT_PLAY_SCREEN(513, 5, false, true),
    TOP_LEFT_CHATBOX(8, 345, false, false),
    TOP_RIGHT_CHATBOX(509, 345, false, true),
    BOTTOM_LEFT_CHATBOX(8, 474, true, false),
    BOTTOM_RIGHT_CHATBOX(509, 474, true, true),
    INVENTORY_AREA(551, 207, false, false);

    private int xCoordinate;
    private int yCoordinate;
    private boolean yCoordinateAtBottom;
    private boolean xCoordinateAtRight;

    PaintLocation(int xCoordinate, int yCoordinate, boolean yCoordinateAtBottom, boolean xCoordinateAtRight) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.yCoordinateAtBottom = yCoordinateAtBottom;
        this.xCoordinateAtRight = xCoordinateAtRight;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public boolean isYCoordinateAtBottom() {
        return yCoordinateAtBottom;
    }

    public boolean isXCoordinateAtRight() {
        return xCoordinateAtRight;
    }
}