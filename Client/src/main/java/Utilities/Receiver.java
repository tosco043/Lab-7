package Utilities;

import Commands.SerializedCommands.SerializedArgumentCommand;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Commands.SpecificCommands.*;
import Data.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Scanner;

public class Receiver {
    private final Invoker invoker;
    private final Sender sender;
    private final CommandAsker commandAsker = new CommandAsker(new InputChecker());
    private final HashMap<String, Boolean> inStack = new HashMap<>();

    public Receiver(Invoker invoker, Sender sender){
        this.invoker = invoker;
        this.sender = sender;
    }

    public static boolean isLogin = false;
    public static String handle = "";
    public static String password = "";

    public void help(){
        invoker.getCommands().forEach((name, command) -> command.aboutCommand());
    }

    public void info() throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new Info(), handle + " " + password));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }

    public void login(String acc, String pass) throws IOException {

        sender.sendObject(new SerializedArgumentCommand(new Login(), acc + " " + pass));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        System.out.println(response);
        response = response.trim();
        if(response.equals("Unsuccessfully")){
            isLogin = false;
            handle = "";
            password = "";
        } else{
            isLogin = true;
            handle = acc;
            password = pass;
        }
    }

    public void register(String login, String password) throws IOException{
        sender.sendObject(new SerializedArgumentCommand(new Register(), login + " " + password));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }

    public void show() throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new Show(), handle + " " + password));
        byte[] responseBytes = new byte[2048];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }

        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }

    public void exit(){
        System.exit(0);
    }

    public void clear() throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new Clear(), handle + " " + password));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }

    public void executeScript(String fileName) throws IOException{
        if(inStack.get(fileName) != null){
            if(inStack.get(fileName)){
                System.out.println("To avoid infinite recursion. File " + fileName + " can't be executed!");
                return;
            }
        }

        inStack.put(fileName, true);

        File scriptFile = new File(fileName);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(scriptFile);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Script file doesn't exist. Please check the file path!");
            return ;
        }

        while(fileScanner.hasNextLine()){
            String[] userCommand = fileScanner.nextLine().trim().split(" ");
            invoker.executeCommand(userCommand);
            System.out.println("----------------------------------------");
        }

        inStack.put(fileName, false);
    }

    public void add() throws IOException {
        Product product = commandAsker.createProduct();
        product.setId((long) -1);
        product.setOwner(handle);
        sender.sendObject(new SerializedCombinedCommand(new Add(), handle + " " + password, product));
        byte[] responseBytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);

        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("u got a problem!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }

    public void addIfMax() throws IOException{
        Product product = commandAsker.createProduct();
        product.setId((long) -1);
        product.setOwner(handle);
        sender.sendObject(new SerializedCombinedCommand(new Add(), handle + " " + password, product));
        byte[] responseBytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);

        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("u got a problem!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }

    public void addIfMin() throws IOException{
        Product product = commandAsker.createProduct();
        product.setId((long) -1);
        product.setOwner(handle);
        sender.sendObject(new SerializedCombinedCommand(new AddIfMin(), handle + " " + password, product));
        byte[] responseBytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        //response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void removeByID(String arg) throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new RemoveById(), handle + " " + password + " " + arg));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }

    public void update() throws IOException{
        String id = commandAsker.idAsker();
        Product product = commandAsker.createProduct();
        product.setOwner(handle);
        product.setId((long) Integer.parseInt(id));
        sender.sendObject(new SerializedCombinedCommand(new Update(),
                handle + " " + password + " " + id, product));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        sender.getDatagramSocket().setSoTimeout(5000);
        try {
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }

    public void printFieldDescendingManufacturer() throws IOException{
        sender.sendObject(new SerializedArgumentCommand(new PrintFieldDescendingManufacturer(), handle + " " + password));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }


    public void countByManufacturer(String args) throws IOException{
        sender.sendObject(new SerializedArgumentCommand(new CountByManufacturer(), handle + " " + password + " " + args));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try{
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        }catch (SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }

    public void filterContainsName(String args) throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new FilterContainsName(), handle + " " + password + " " + args));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try{
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        }catch (SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }


}
