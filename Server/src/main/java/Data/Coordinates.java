package Data;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private final long x;
    private final Long y;

    public Coordinates(long x, Long y){
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }
}
