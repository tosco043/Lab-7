package Utilities;

import Data.*;

import java.time.LocalDateTime;
import java.util.*;

public class CommandAsker {
    private final InputChecker inputChecker;
    private static final HashSet<Long> idChecker = new HashSet<>();
    public CommandAsker(InputChecker ic){
        this.inputChecker = ic;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public Product createProduct() {
        Product newProduct = new Product();
        System.out.println("Let's create new product");

        //newProduct.setId(idGenerator());
        newProduct.setName(nameAsker());
        newProduct.setCoordinates(coordinatesAsker());
        newProduct.setPrice(priceAsker());
        newProduct.setCreationDate(creationDateAsker());
        newProduct.setUnitOfMeasure(unitOfMeasureAsker());
        newProduct.setManufacturer(manufacturerAsker());
        return newProduct;
    }

    /**public Long idGenerator(){
     System.out.println("ID is automatically generated");
     Long newID = new Random().nextLong();
     if(idChecker.contains(newID) || newID <0){
     System.out.println("Invalid ID generated, another ID generating");
     return idGenerator();
     }
     else {
     idChecker.add(newID);
     System.out.println("ID = "+ newID + "has been generated");
     return newID;
     }
     }**/

    public String nameAsker(){
        System.out.println("Insert name: ");
        return (scanner.nextLine());
    }

    public Coordinates coordinatesAsker(){
        System.out.println("Insert coordinates: ");
        while(true){
            System.out.println("Insert the x and y respectively");
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 2){
                System.out.println("please insert two numbers (x and y respectively)");
            }
            else{
                if(inputChecker.longValidCheck(inputNumber[0], (long) -712, Long.MAX_VALUE)) continue;
                if(inputChecker.longValidCheck(inputNumber[1], Long.MIN_VALUE, (long) 858)) continue;
                long x = Long.parseLong(inputNumber[0]);
                Long y = Long.parseLong(inputNumber[1]);
                return new Coordinates(x, y);
            }
        }
    }
    public Double priceAsker() {
        System.out.println("Insert price in rubles: ");
        String pri = scanner.nextLine();
        Double price = Double.parseDouble(pri);
        if (!inputChecker.doubleValidCheck(pri, 1.00, Double.MAX_VALUE)) {
            System.out.println("Insert price in form ##.##: ");
            return priceAsker();
        }
        else{return price;}
    }

    public LocalDateTime creationDateAsker(){
        return java.time.LocalDateTime.now();
    }

    public UnitOfMeasure unitOfMeasureAsker() {
        while(true) {
            System.out.println("Insert unit of measurement: ");
            String[] unit = scanner.nextLine().trim().split(" ");
            if(unit.length != 1){
                System.out.println("Please insert a unit of measurement");
                continue;
            }

            try{
                return UnitOfMeasure.valueOf(unit[0]);
            } catch(IllegalArgumentException e){
                System.out.println("Invalid unit!");
                System.out.println("Please insert one of the following");
                for(UnitOfMeasure unitOfMeasure : UnitOfMeasure.values()){
                    System.out.println(unitOfMeasure);
                }
            }
        }
    }


    public Organization manufacturerAsker(){
        System.out.println("DETAILS ABOUT THE MANUFACTURER: ");

        Organization newOrganization = new Organization();

        newOrganization.setOrgId(orgIdGenerator());
        newOrganization.setOrgName(orgNameAsker());
        newOrganization.setAnnualTurnover(annualTurnoverAsker());
        newOrganization.setType(typeAsker());
        newOrganization.setPostalAddress(postalAddressAsker());

        return newOrganization;
    }

   public long orgIdGenerator() {
     System.out.println("Organization ID is automatically generated");
     long neworgID = new Random().nextLong();
     if (idChecker.contains(neworgID) || neworgID <= 0) {
     System.out.println("Input is invalid");
     return orgIdGenerator();
     }
     else {
     idChecker.add(neworgID);
     System.out.println("org ID = " + neworgID + "has been generated");
     return neworgID;
     }

     }

    public String orgNameAsker(){
        System.out.println("NOTE: PUT A '_' BETWEEN EACH WORD IF THE NAME IS MORE THAN A WORD");
        System.out.println("Insert name: ");
        return (scanner.nextLine());
    }

    public double annualTurnoverAsker() {
        System.out.println("Insert annual turnover: ");

        String pri = scanner.nextLine();
        double annualTurnover = Double.parseDouble(pri);
        inputChecker.doubleValidCheck(pri, 1.00, Double.MAX_VALUE);
        return annualTurnover;
    }

    public OrganizationType typeAsker(){
        while(true) {
            System.out.println("Insert the organization type: ");
            String[] type = scanner.nextLine().trim().split(" ");
            if (type.length != 1){
                System.out.println("Please insert the correct organization type");
                continue;
            }
            try{
                return OrganizationType.valueOf(type[0]);
            } catch(IllegalArgumentException e){
                System.out.println("Invalid type!");
                System.out.println("Please insert one of the following");
                for(OrganizationType organizationType : OrganizationType.values()){
                    System.out.println(organizationType);
                }
            }
        }
    }

    public Address postalAddressAsker(){
        System.out.println("Put in the address details");

        Address newAddress = new Address();

        newAddress.setZipcode(zipcodeAsker());
        newAddress.setLocation(locationAsker());

        return newAddress;
    }

    public String zipcodeAsker(){
        System.out.println("Insert to zip code");
        String str = scanner.nextLine();
        if(str.contains(" ")){
            System.out.println("please zip code must not contain space");
            return zipcodeAsker();
        }
        else if (!inputChecker.zipcodeValidCheck(str))
        {return str;}
        return str;
    }

    public Location locationAsker(){
        System.out.println("Insert Location: ");
        System.out.println("Insert X: ");
        Long locX = longAsker(Integer.MIN_VALUE,Integer.MAX_VALUE);
        System.out.println("Insert Y: ");
        long locY = longAsker(Long.MIN_VALUE, Long.MAX_VALUE);
        System.out.println("Insert name: ");
        String name = scanner.nextLine();
        return new Location(locX, locY, name);
    }





   /** public double doubleAsker(double min, double max){
        while(true){
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1 ){
                System.out.println("please enter exactly one double number: ");
            }
            else{
                double x;
                try {
                    x = Double.parseDouble(inputNumber[0]);
                    if( x < min ) continue;
                    if( x > max) continue;
                    return x;
                } catch (NumberFormatException e){
                    System.out.println("please insert an double number");
                }
            }
        }
    }**/

   public Long longAsker(long min, long max){
       while(true){
           String[] inputNumber = scanner.nextLine().trim().split(" ");
           if(inputNumber.length != 1 ){
               System.out.println("please enter exactly one Long number: ");
           }
           else{
               long x;
               try {
                   x = Long.parseLong(inputNumber[0]);
                   if( x < min ) continue;
                   if( x > max) continue;
                   return x;
               } catch (NumberFormatException e){
                   System.out.println("please insert an Long number");
               }
           }
       }
   }

    public String idAsker() {
        while (true) {
            System.out.println("insert id");
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if (inputNumber.length != 1) {
                System.out.println("please enter exactly one Long number: ");
            } else {
                try {
                    Long.parseLong(inputNumber[0]);
                    return inputNumber[0];
                } catch (NumberFormatException e) {
                    System.out.println("please insert an Long number");
                }
            }

        }
    }
}
