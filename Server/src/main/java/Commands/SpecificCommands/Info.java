package Commands.SpecificCommands;

import Commands.Command;
import Utilities.Logging;
import Utilities.Receiver;

import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class Info extends Command implements Serializable {
    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress){
        Logging.log(Level.INFO, "Server is executing Info command....");
        Receiver receiver = new Receiver(datagramChannel);
        receiver.info((String) o, socketAddress);
    }
}
