package Data;

import java.io.Serializable;

public class Address implements Serializable {
    private String zipcode;
    private Location location;

    public Address(String zipcode, Location location) {
        this.zipcode = zipcode;
        this.location = location;
    }

    public Address() {

    }


    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Location getLocation() {
        return location;
    }

    public String toString(){
        String addDetail = ("               zip code: "+ zipcode + '\n');
        addDetail += ("location: \n");
        addDetail += ("           x: " + getLocation().getLocX() + '\n');
        addDetail += ("           y: " + getLocation().getLocY() + '\n');
        addDetail += ("           name: " + getLocation().getName() + "\n\n");
        return addDetail;
    }
}
