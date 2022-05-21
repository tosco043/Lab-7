package Utilities;

import Data.Product;
import Database.DatabaseCommunicator;
import Database.ProductBase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionManager {

    private static Set<Product> listProduct = Collections.synchronizedSet(new HashSet<>());
    private static final LocalDate creationDate = LocalDate.now();

    public static int getNumberOfProducts(){
        return listProduct.size();
    }

    public static LocalDate getCreationDate(){
        return creationDate;
    }

    public static void initializeCollection(){
        listProduct = new HashSet<>();
    }

    public static Set<Product> getCollection(){
        return listProduct;
    }

    public static String info(){
        return "Collection's type: " + listProduct.getClass().getSimpleName() + '\n' +
                "Initialization date: " + CollectionManager.getCreationDate() + '\n' +
                "Collection's size: " + CollectionManager.getNumberOfProducts();
    }

    public static String show(){
        StringBuilder str = new StringBuilder();
        if(listProduct.size() == 0) str.append("Collection is empty!");
        else listProduct.stream().sorted((o1, o2) -> {
            if(o1.getId() == o2.getId()) return 0;
            else return o1.getId() > o2.getId() ? 1 : -1;
        }).forEach(p -> str.append(p.toString()));
        return String.valueOf(str);
    }

    public static void clearCollectionOnDatabase(String owner) throws SQLException{
        ProductBase.clearCollectionOnDataBase(owner);
    }

    public static String add() throws SQLException{
        ProductBase.loadCollection(getCollection());
        return "New element was added into Collection!";
    }

    public static String addIfMax() throws SQLException{
        ProductBase.loadCollection(getCollection());
        return "New element was added into Collection!";
    }

    public static String addIfMin() throws SQLException{
        ProductBase.loadCollection(getCollection());
        return "New element was added into Collection!";
    }

    public static String removeById(long id) throws SQLException{
        String response = "";
        for (Product product: listProduct){
            if (product.getId() == id){
                DatabaseCommunicator.getProductBase().deleteOrganizationFromDataBase((int) id);
                response = "Person has ID = " + id +" is removed!";
                break;
            }
        }
        ProductBase.loadCollection(getCollection());
        if(response.equals("")) return "ID doesn't exist!";
        else return response;
    }

    public static String update(long id) throws SQLException {
        ProductBase.loadCollection(getCollection());
        return "Product has id = " + id + " was updated";
    }

    public static String printFieldDescendingManufacturer(){
        StringBuilder stringBuilder = new StringBuilder();
        listProduct.stream().sorted().forEach(p -> stringBuilder.append(p.getManufacturer().getOrgName()).append('\n'));
        return String.valueOf(stringBuilder);
    }

    public static String countByManufacturer(String arg) {
        ArrayList<String> list = new ArrayList<>();
        for (Product product : listProduct){
            if (product.getManufacturer().getOrgName().equals(arg)){
                list.add(product.getManufacturer().getOrgName());
            }

        }
        return  list.size() + " found KEY WORD: "+ arg;
    }

    public static String filterContainsName(String arg) {
        ArrayList<String> list = new ArrayList<>();
        for (Product product : listProduct){
            if (product.getName().contains(arg)){
                list.add(product.getName());
            }
        }
        return list.toString() + " Key word: " + arg;
    }


}
