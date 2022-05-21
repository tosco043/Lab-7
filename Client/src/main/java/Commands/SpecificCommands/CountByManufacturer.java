package Commands.SpecificCommands;

import Commands.Command;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class CountByManufacturer extends Command implements Serializable {
    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public CountByManufacturer(){}

    public CountByManufacturer(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void aboutCommand(){
        System.out.println("count_by_manufacturer manufacturer        : print the number of elements whose manufacturer field is equal to the given one");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("Client: Invalid command's format! Fail to execute InfoCommand!");
        }
        else{
            receiver.countByManufacturer(args[1]);
        }
    }
}
