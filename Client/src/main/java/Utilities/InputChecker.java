package Utilities;

public class InputChecker {
    public InputChecker(){}

    private static final double eps = 1E-6;

    public boolean longValidCheck(String s, Long min, Long max){
        try{
            long x = Long.parseLong(s);
            if(x >= min && x <= max) return false;
            System.out.println("Input is invalid. Please enter the long number in correct range");
            return true;
        } catch(NumberFormatException e){
            System.out.println("Input is invalid. Please enter a long number");
            return true;
        }
    }

    public boolean doubleValidCheck(String s, Double min, Double max) {
        try {
            Double x = Double.parseDouble(s);
            if (x - min > eps && max - x > eps) {
                return true;
            }
            System.out.println("Input is invalid. Please enter the double number in correct range");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Input is invalid. Please enter a double number");
            return false;
        }
    }
    public boolean zipcodeValidCheck(String s){
        if (s.length() > 30){
            System.out.println("Input is invalid, zip code must not be greater than 30 characters");
        } return false;
    }
}
