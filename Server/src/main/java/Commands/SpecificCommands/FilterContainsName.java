package Commands.SpecificCommands;

import Commands.Command;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Utilities.Logging;
import Utilities.Receiver;

import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class FilterContainsName extends Command implements Serializable {
    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress){
        Receiver receiver = new Receiver(datagramChannel);
        Logging.log(Level.INFO, "Server is executing filter contains name command....");
        receiver.filterContainsName((String) o, socketAddress);
    }
}
