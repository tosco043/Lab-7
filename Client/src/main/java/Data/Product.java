package Data;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Product implements Comparable<Product>, Serializable {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private Double price;
    private UnitOfMeasure unitOfMeasure;
    private Organization manufacturer;
    private String owner;


    public Product(Long id, String owner, String name, Coordinates coordinates, LocalDateTime creationDate, Double price,
                   UnitOfMeasure unitOfMeasure, Organization manufacturer) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;

    }

    public Product(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Organization manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String toString(){
        String detail = ("Name: " + name + '\n');
        detail += ("ID: "+ id + '\n');
        detail += ("Price: " + price + '\n');
        detail += ("Coordinates: " + '\n');
        detail += ("             x: "+ coordinates.getX()+ '\n');
        detail += ("             y: "+ coordinates.getY()+ '\n');
        detail += ("Creation date: "+ creationDate.toString() + '\n');
        detail += ("Unit of measure: "+ unitOfMeasure + '\n');
        detail += ("Manufacturer: "+ manufacturer.toString() + '\n');
        detail += ("owner: " + owner + "\n\n");
        return detail;
    }
    @Override
    public int compareTo(Product o) {
        return (int) (price - o.getPrice());
    }
}
