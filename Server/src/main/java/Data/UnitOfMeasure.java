package Data;

import java.io.Serializable;

public enum UnitOfMeasure implements Serializable {
    KILOGRAMS ("KILOGRAMS"),
    METERS ("METERS"),
    SQUARE_METERS ("SQUARE_METERS"),
    GRAMS ("GRAMS");

    private final String string;
    UnitOfMeasure(String string){
        this.string = string;
    }
    public String getString(){
        return string;
    }
}
