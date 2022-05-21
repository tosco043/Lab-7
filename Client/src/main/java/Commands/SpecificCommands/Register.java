package Commands.SpecificCommands;

import Commands.Command;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class Register extends Command implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private Receiver receiver;

    public Register(){

    }

    public Register(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void aboutCommand() {

    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 3){
            System.out.println("Client: Invalid command's format! Fail to execute LoginCommand!");
        }
        else{
            receiver.register(args[1], args[2]);
        }
    }
}
