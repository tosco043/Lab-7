package Commands.SpecificCommands;

import Commands.Command;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class AddIfMax extends Command implements Serializable {
    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public AddIfMax(Receiver receiver) {
        this.receiver = receiver;
    }

    public AddIfMax() {

    }


    @Override
    public void aboutCommand() {
        System.out.println("add_if_max {element} :            add a new item to the collection if its value is greater than the" +
                " value of the largest item in this collection");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Client: Invalid command's format! Fail to execute AddIfMax command!");
        } else {
            receiver.addIfMax();
        }
    }
}
