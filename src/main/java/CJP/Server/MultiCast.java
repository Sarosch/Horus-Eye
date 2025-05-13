package CJP.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Queue;


public class MultiCast{
    //TIP Sender Format: | fictional address  | Package Type | Request Code | Extra Data Lenght | Extra Data
    //
    private static Queue<String> Buffer;

    private int Address = 00;
    private static MulticastSocket cast;
    private InetAddress Group;
    private int port;

    /**
     * Create de Multicast Instance
     *
     * @param port - Int Port number for access to cast
     */
    public MultiCast(int port){
        this.port = port;
    }

    /**
     *
     * @param Group_Address -  {@link InetAddress} Create with .getByAddress({@link Byte})
     */
    public void SetGroup (InetAddress Group_Address) {
        this.Group = Group_Address;
    }
    public void Start(String device_name,String Address, String Port){
        Buffer = cast_recive.Buffer;
        String Hi = device_name + "=" + Address + ":" + Port;
        DatagramPacket hello = new DatagramPacket(Hi.getBytes(),Hi.getBytes().length,Group,port);
        byte[] buffer = new byte[1024];
        try {
            DatagramPacket recived = new DatagramPacket(buffer,buffer.length);
            cast = new MulticastSocket(port);
            cast.joinGroup(Group);
            cast.send(hello);
            cast.receive(recived);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        new cast_recive(cast);
    }

    public void Send(String Data){
        DatagramPacket pack = new DatagramPacket(Data.getBytes(),Data.getBytes().length,Group,port);
    }
    public String Read(){
        while(Buffer.isEmpty());
        return Buffer.poll();
    }

}

class cast_recive extends Thread{
    private MulticastSocket cast;
    private DatagramPacket pack;
    public static Queue<String> Buffer;




    @Override
    public void run() {
        super.run();
        try {
            cast.receive(pack);
            Buffer.add(new String(pack.getData(),0,pack.getLength()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public  cast_recive(MulticastSocket cast){
        this.cast = cast;
        while(true);
    }
}

