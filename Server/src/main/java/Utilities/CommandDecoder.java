package Utilities;

import Commands.Command;
import Commands.SerializedCommands.SerializedArgumentCommand;
import Commands.SerializedCommands.SerializedCombinedCommand;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class CommandDecoder {
    private final SocketAddress socketAddress;
    private final DatagramChannel datagramChannel;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public CommandDecoder(DatagramChannel datagramChannel, SocketAddress socketAddress){
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    public void decode(Object o){
        executor.execute(() -> {
            if (o instanceof SerializedArgumentCommand) {
                Logging.log(Level.INFO, "Receiving data is argument command!");
                SerializedArgumentCommand sac = (SerializedArgumentCommand) o;
                Command command = sac.getCommand();
                String arg = sac.getArg();

                try{
                    command.execute(arg, datagramChannel, socketAddress);
                }catch (IOException | ParseException | SQLException e){
                    e.printStackTrace();
                }
            }
            if (o instanceof SerializedCombinedCommand){
                Logging.log(Level.INFO, "Receiving data is combined command!");
                SerializedCombinedCommand scc = (SerializedCombinedCommand) o;
                Command command = scc.getCommand();
                try{
                    command.execute(scc, datagramChannel, socketAddress);
                } catch (IOException | ParseException | SQLException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
