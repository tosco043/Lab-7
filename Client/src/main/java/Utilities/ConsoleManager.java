package Utilities;

import Commands.SpecificCommands.*;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {

    public void interactive(String host, String p) throws IOException{
        Communicator communicator = null;
        try{
            communicator = new Communicator(host, Integer.parseInt(p));
            communicator.startCommunication();
        } catch (NumberFormatException e){
            System.out.println("Client: Error! Port is invalid!");
            System.exit(0);
        }

        Sender sender = new Sender(communicator);
        Invoker invoker = new Invoker();
        Receiver receiver = new Receiver(invoker, sender);

        invoker.register("add", new Add(receiver));
        invoker.register("add_if_max", new AddIfMax(receiver));
        invoker.register("add_if_min", new AddIfMin(receiver));
        invoker.register("clear", new Clear(receiver));
        invoker.register("count_by_manufacturer", new CountByManufacturer(receiver));
        invoker.register("execute_script", new ExecuteScript(receiver));
        invoker.register("exit", new Exit(receiver));
        invoker.register("filter_contains_name", new FilterContainsName(receiver));
        invoker.register("help", new Help(receiver));
        invoker.register("info", new Info(receiver));
        invoker.register("login", new Login(receiver));
        invoker.register("print_field_descending_manufacturer", new PrintFieldDescendingManufacturer(receiver));
        invoker.register("register", new Register(receiver));
        invoker.register("remove_by_id", new RemoveById(receiver));
        invoker.register("show", new Show(receiver));
        invoker.register("update", new Update(receiver));

        System.out.println("Insert 'login' if you are not lo" +
                "gged in!");
        System.out.println("Insert 'register' if you don't have account yet");

        Scanner userInput = new Scanner(System.in);
        while(true){
            if(!userInput.hasNextLine()){
                communicator.endCommunication();
                System.exit(0);
            }
            String strCommand = userInput.nextLine();
            if(strCommand.contains(";") | strCommand.contains("'")| strCommand.contains("\"")){
                System.out.println("Please insert request without character ; ' \" ");
                System.out.println("-----------------------------------------");
                continue;
            }
            String[] userCommand = strCommand.trim().split(" ");
            invoker.executeCommand(userCommand);
            System.out.println("-------------------------------------");
        }
    }
}
