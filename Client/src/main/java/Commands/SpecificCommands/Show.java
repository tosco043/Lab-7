package Commands.SpecificCommands;

import Commands.Command;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class Show extends Command implements Serializable {
    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public Show(){

    }

    public Show(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void aboutCommand() {
        System.out.println("show                        - display all the elements of the collection");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Client: Invalid command's format! Fail to execute ShowCommand!");
        }
        else{
            receiver.show();
        }
    }
}
