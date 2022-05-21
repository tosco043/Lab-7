package Commands.SpecificCommands;

import Commands.Command;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class Add extends Command implements Serializable {
    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public Add(){

    }

    public Add(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void aboutCommand(){
        System.out.println("add {element}               - add new element into the collection");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Client: Invalid command's format! Fail to execute Add Command!");
        } else {
            receiver.add();
        }
    }
}
