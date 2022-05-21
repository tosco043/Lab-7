package Commands.SpecificCommands;

import Commands.Command;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class FilterContainsName extends Command implements Serializable {
    public Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public FilterContainsName(Receiver receiver){
        this.receiver = receiver;
    }

    public FilterContainsName() {

    }

    @Override
    public void aboutCommand() {
        System.out.println("filter_contains_name name         : display elements whose name field value contains the given substring");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("Client: Invalid command's format! Fail to execute InfoCommand!");
        }
        else {
            receiver.filterContainsName(args[1]);
        }
    }
}
