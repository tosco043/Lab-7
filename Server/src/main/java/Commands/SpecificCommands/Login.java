package Commands.SpecificCommands;

import Commands.Command;
import Utilities.Receiver;

import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class Login extends Command implements Serializable {
    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress){
        Receiver receiver = new Receiver(datagramChannel);
        receiver.login((String) o, socketAddress);
    }
}
