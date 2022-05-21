package Data;

import java.io.Serializable;

public class Location implements Serializable {
    private final long locX;
    private final long locY;
    private final String name;

    public Location(long locX, long locY, String name){
        this.locX = locX;
        this.locY = locY;
        this.name = name;
    }

    public long getLocX() {
        return locX;
    }

    public String getName() {
        return name;
    }

    public long getLocY() {
        return locY;
    }
}
