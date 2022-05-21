package Data;

import java.io.Serializable;

public enum OrganizationType implements Serializable {
    COMMERCIAL ("COMMERCIAL"),
    PUBLIC ("PUBLIC"),
    GOVERNMENT ("GOVERNMENT"),
    TRUST ("TRUST"),
    PRIVATE_LIMITED_COMPANY ("PRIVATE_LIMITED_COMPANY");

    private final String string;

    OrganizationType(String string){
        this.string = string;
    }

    public String getString(){
        return string;
    }
}
