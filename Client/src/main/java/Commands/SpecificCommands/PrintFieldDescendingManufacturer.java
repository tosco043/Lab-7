package Commands.SpecificCommands;

import Commands.Command;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class PrintFieldDescendingManufacturer extends Command implements Serializable {
    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public PrintFieldDescendingManufacturer(){

    }

    public PrintFieldDescendingManufacturer(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void aboutCommand() {
        System.out.println("print_field_ascending_height- print field height of all elements in ascending order");

    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Client: Invalid command's format! Fail to execute InfoCommand!");
        }
        else{
            receiver.printFieldDescendingManufacturer();
        }
    }
}
