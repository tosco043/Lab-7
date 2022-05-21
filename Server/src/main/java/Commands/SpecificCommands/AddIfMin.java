package Commands.SpecificCommands;

import Commands.Command;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Utilities.Logging;
import Utilities.Receiver;

import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class AddIfMin extends Command implements Serializable {
    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress){
        SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
        String arg = combinedCommand.getArg();
        Object ob = combinedCommand.getObject();
        Receiver receiver = new Receiver(datagramChannel);
        Logging.log(Level.INFO, "Server is executing add if min command....");
        receiver.addIfMin(arg, ob, socketAddress);
    }
}
