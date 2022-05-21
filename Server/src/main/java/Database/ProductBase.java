package Database;

import Data.*;
import Utilities.CollectionManager;
import Utilities.Logging;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.logging.Level;

public class ProductBase {
    private static Connection connection;

    public ProductBase(Connection connection) throws SQLException {
        ProductBase.connection = connection;
        Statement statement = connection.createStatement();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS listproduct " +
                "(id BIGSERIAL NOT NULL PRIMARY KEY, " +
                "owner VARCHAR(50) NOT NULL, " +
                "product_name VARCHAR(50) NOT NULL, " +
                "x INT NOT NULL, " +
                "y INT NOT NULL, " +
                "creationDate VARCHAR(50) NOT NULL, " +
                "price DECIMAL(10,4) NOT NULL, " +
                "unitOfMeasure VARCHAR(50) NOT NULL, " +
                "orgId VARCHAR(50) NOT NULL, " +
                "orgName VARCHAR(50) NOT NULL, " +
                "annualTurnover DECIMAL(10,4) NOT NULL, " +
                "org_type VARCHAR(50) NOT NULL, " +
                "zipcode VARCHAR(50) NOT NULL, " +
                "locX INT NOT NULL, "+
                "locY INT NOT NULL, "+
                "loc_name VARCHAR(50) NOT NULL )";
        System.out.println("table is created");
        statement.execute(createTableSQL);
        System.out.println("successfully created");
    }

    public static boolean validation(String handle, String password) throws SQLException {
        String query = "SELECT * FROM users;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            if (resultSet.getString(1).equals(handle) &&
                    resultSet.getString(2).equals(SecretBase.HashPsw(password))) {
                return true;
            }
        }
        return false;
    }

    public static boolean validationPermissionEdit(int id, String handle) throws SQLException {
        String query = "SELECT * FROM listproduct;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            if (resultSet.getInt("id") == id && resultSet.getString("owner").equals(handle)) {
                return true;
            }
        }
        return false;
    }

    public static void clearCollectionOnDataBase(String owner) throws SQLException {
        String sql = "DELETE FROM listproduct Where owner = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, owner);
        preparedStatement.executeUpdate();
        ProductBase.loadCollection(CollectionManager.getCollection());
    }

    public static void loadCollection(Set<Product> listProduct) throws SQLException {
        String query = "SELECT * FROM listproduct ORDER by id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        listProduct.clear();
        while (resultSet.next()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            listProduct.add(new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    new Coordinates(resultSet.getLong(4), resultSet.getLong(5)),
                    LocalDateTime.parse(resultSet.getString(6), dtf),
                    resultSet.getDouble(7), UnitOfMeasure.valueOf(resultSet.getString(8)),
                    new Organization( resultSet.getLong(9), resultSet.getString(10),
                            resultSet.getDouble(11), OrganizationType.valueOf(resultSet.getString(12)),
                            new Address(resultSet.getString(13),
                                    new Location(resultSet.getLong(14), resultSet.getLong(15), resultSet.getString(16))))));
        }
    }



    public void addProductToDataBase(Product product, int id) throws SQLException {
        if (id == -1) {
            System.out.println("a dog");
            String sql = "INSERT INTO listproduct (owner, product_name, x, y, creationDate, price, unitOfMeasure, orgId, orgName, annualTurnover," +
                    "org_type, zipcode, locX, locY, loc_name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getOwner());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setLong(3, product.getCoordinates().getX());
            preparedStatement.setLong(4, product.getCoordinates().getY());
            LocalDateTime localDate = product.getCreationDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String strDate = localDate.format(dtf);
            preparedStatement.setString(5, strDate);
            preparedStatement.setDouble(6, product.getPrice());
            preparedStatement.setString(7, product.getUnitOfMeasure().getString());
            preparedStatement.setString(8, String.valueOf(product.getManufacturer().getOrgId()));
            preparedStatement.setString(9, product.getManufacturer().getOrgName());
            preparedStatement.setDouble(10, product.getManufacturer().getAnnualTurnover());
            preparedStatement.setString(11, product.getManufacturer().getType().getString());
            preparedStatement.setString(12, product.getManufacturer().getPostalAddress().getZipcode());
            preparedStatement.setLong(13, product.getManufacturer().getPostalAddress().getLocation().getLocX());
            preparedStatement.setLong(14, product.getManufacturer().getPostalAddress().getLocation().getLocY());
            preparedStatement.setString(15, product.getManufacturer().getPostalAddress().getLocation().getName());
            preparedStatement.execute();

        } else {
            System.out.println("stupid cat");
            String sql = "INSERT INTO listproduct (id, owner, product_name, x, y, creationDate, price, unitOfMeasure, orgName, annualTurnover, " +
                    "orgId, org_type, zipcode, locX, locY, loc_name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("we ready");
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, product.getOwner());
            System.out.println("common");
            preparedStatement.setString(3, product.getName());
            preparedStatement.setLong(4, product.getCoordinates().getX());
            preparedStatement.setLong(5, product.getCoordinates().getY());
            System.out.println("stop this");
            LocalDateTime localDate = product.getCreationDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String strDate = localDate.format(dtf);
            Logging.log(Level.INFO, strDate);
            preparedStatement.setString(6, strDate);
            preparedStatement.setDouble(7, product.getPrice());
            preparedStatement.setString(8, product.getUnitOfMeasure().getString());
            preparedStatement.setString(9, String.valueOf(product.getManufacturer().getOrgId()));
            preparedStatement.setString(10, product.getManufacturer().getOrgName());
            preparedStatement.setDouble(11, product.getManufacturer().getAnnualTurnover());
            preparedStatement.setString(12, product.getManufacturer().getType().getString());
            preparedStatement.setString(13, product.getManufacturer().getPostalAddress().getZipcode());
            preparedStatement.setLong(14, product.getManufacturer().getPostalAddress().getLocation().getLocX());
            preparedStatement.setLong(15, product.getManufacturer().getPostalAddress().getLocation().getLocY());
            preparedStatement.setString(16, product.getManufacturer().getPostalAddress().getLocation().getName());
            preparedStatement.execute();
        }
    }

    public void deleteOrganizationFromDataBase(int deleteId) throws SQLException {
        String sql = "DELETE FROM listproduct WHERE id = ?;";
        //String sql = "DELETE FROM listproduct WHERE ID = '" + id + "';";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deleteId);
        preparedStatement.executeUpdate();
    }
}
