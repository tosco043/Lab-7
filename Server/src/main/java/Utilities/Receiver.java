package Utilities;

import Data.Product;
import Database.DatabaseCommunicator;
import Database.ProductBase;
import Database.SecretBase;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool;

import static java.nio.ByteBuffer.wrap;

public class Receiver {
    private final DatagramChannel datagramChannel;
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public Receiver(DatagramChannel datagramChannel){
        this.datagramChannel = datagramChannel;
    }



    public void clear(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(ProductBase.validation(str[0], str[1])){
                    CollectionManager.clearCollectionOnDatabase(str[0]);
                    byte[] response = "Clear data of people who belong to you!".getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void add(String arg, Object o, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(ProductBase.validation(str[0], str[1])){
                    Product product = (Product) o;
                    DatabaseCommunicator.getProductBase().addProductToDataBase(product, -1);
                    byte[] response = CollectionManager.add().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void addIfMax(String arg, Object o, SocketAddress socketAddress) {
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(ProductBase.validation(str[0], str[1])){
                    Product product = (Product) o;
                    DatabaseCommunicator.getProductBase().addProductToDataBase(product, -1);
                    byte[] response = CollectionManager.addIfMax().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void addIfMin(String arg, Object o, SocketAddress socketAddress) {
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(ProductBase.validation(str[0], str[1])){
                    Product product = (Product) o;
                    DatabaseCommunicator.getProductBase().addProductToDataBase(product, -1);
                    byte[] response = CollectionManager.addIfMin().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void info(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(ProductBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.info().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void removeById(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(ProductBase.validation(str[0], str[1])){
                    int id = Integer.parseInt(str[2]);
                    if(ProductBase.validationPermissionEdit(id, str[0])){
                        byte[] response = CollectionManager.removeById(id).getBytes();
                        datagramChannel.send(wrap(response), socketAddress);
                    }
                    else{
                        datagramChannel.send(wrap("You can't remove the element not belong to you!".getBytes()), socketAddress);
                    }
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void show(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() ->{
            String[] str = arg.split(" ");
            try {
                if(ProductBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.show().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void update(String arg, Object o, SocketAddress socketAddress) {
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(ProductBase.validation(str[0], str[1])){
                    int id = Integer.parseInt(str[2]);
                    if(ProductBase.validationPermissionEdit(id, str[0])){
                        DatabaseCommunicator.getProductBase().deleteOrganizationFromDataBase(id);
                        DatabaseCommunicator.getProductBase().addProductToDataBase((Product) o, id);
                        byte[] response = CollectionManager.update(id).getBytes();
                        datagramChannel.send(wrap(response), socketAddress);
                    } else{
                        datagramChannel.send(wrap("You can't update the element not belong to you!".getBytes()), socketAddress);
                    }
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void register(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            String response;
            try {
                if(!(DatabaseCommunicator.getUsers().isExisting("handle", str[0]))){
                    DatabaseCommunicator.getUsers().addUserToDataBase(str[0], str[1]);
                    response = "Successfully";
                } else response = "Unsuccessfully";
                datagramChannel.send(wrap(response.getBytes()), socketAddress);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void login(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            String response;
            //System.out.println(str[0] + " " + str[1]);
            try {
                if(DatabaseCommunicator.getUsers().isExisting("handle", str[0])){
                    //System.out.println("handle yes!");
                    if(DatabaseCommunicator.getUsers().isExisting("password", SecretBase.HashPsw(str[1]))){
                        //System.out.println("password yes!");
                        response = "Successfully";
                    } else  response = "Unsuccessfully";
                } else response = "Unsuccessfully";
                datagramChannel.send(wrap(response.getBytes()), socketAddress);

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void printFieldDescendingManufacturer(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try{
                if(ProductBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.printFieldDescendingManufacturer().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void countByManufacturer(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try{
                if(ProductBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.countByManufacturer(str[2]).getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            }catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void filterContainsName(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try{
                if(ProductBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.filterContainsName(str[2]).getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            }catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }



}
