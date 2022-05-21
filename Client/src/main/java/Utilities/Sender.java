package Utilities;

import Commands.SerializedCommands.SerializedArgumentCommand;
import Commands.SerializedCommands.SerializedCombinedCommand;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    private final DatagramSocket datagramSocket;
    private final Communicator communicator;

    public Sender(Communicator communicator) {
        this.communicator = communicator;
        this.datagramSocket = communicator.getDatagramSocket();
    }

    public DatagramSocket getDatagramSocket(){
        return datagramSocket;
    }

    public void sendObject(SerializedArgumentCommand sac) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(sac);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(dataBytes, dataBytes.length, datagramSocket.getInetAddress(),
                communicator.getPort());
        datagramSocket.send(datagramPacket);
    }

    public void sendObject(SerializedCombinedCommand scc) throws IOException{
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(scc);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(dataBytes, dataBytes.length, datagramSocket.getInetAddress(),
                communicator.getPort());
        datagramSocket.send(datagramPacket);
    }
}