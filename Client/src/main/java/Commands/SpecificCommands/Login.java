package Commands.SpecificCommands;

import Commands.Command;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class Login extends Command implements Serializable {
    private static final long serialVersionUID = 1234567L;

    private Receiver receiver;

    public Login(){

    }

    public Login(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void aboutCommand(){
        System.out.println("login:");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length !=3){
            System.out.println("Client: Invalid command's format! Fail to execute LoginCommand!");
        }
        else{
           receiver.login(args[1], args[2]);
        }
    }
}
