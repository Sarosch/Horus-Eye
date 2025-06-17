package CJP.Server.Suporte;


import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class BroadCast extends Thread{
    private Queue<DatagramPacket> buffer = new LinkedList();
    private InetAddress address;
    private ArrayList<InetAddress> Ip = new ArrayList<>();
    private DatagramPacket ForNews = null;
    private int port;
    private DatagramSocket broadcast;
    private ArrayList<InetAddress> olds = new ArrayList<>();
    public BroadCast(InetAddress BroadCastAddress, int BroadCastPort){
        WifiSupport();
        address = BroadCastAddress;
        port = BroadCastPort;
    }
    @Override
    public void run() {
        super.run();
        try {
            broadcast = new DatagramSocket(port);
            broadcast.setBroadcast(true);

            while(broadcast.getBroadcast()){
                byte[] size = new byte[1024];
                DatagramPacket packet = new DatagramPacket(size, size.length);
                broadcast.receive(packet);
                if(!Ip.contains(packet.getAddress())) {
                    buffer.add(packet);
                    if (!olds.contains(packet.getAddress())) {
                        broadcast.send(ForNews);
                        olds.add(packet.getAddress());
                    }
                }

            }
        } catch (SocketException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
    private void WifiSupport(){
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()){
                NetworkInterface Intf = interfaces.nextElement();
                if(!Intf.isUp() || Intf.isLoopback() || Intf.isVirtual())
                    continue;
                Enumeration<InetAddress> addresses = Intf.getInetAddresses();

                while(addresses.hasMoreElements()){
                    InetAddress addr = addresses.nextElement();
                    if(addr instanceof Inet4Address && !addr.isLoopbackAddress()){
                        Ip.add(addr);
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
    public void SetForNews(String Data){
        byte[] buf = new byte[1024];
        buf = Data.getBytes();
        ForNews = new DatagramPacket(buf,buf.length,address,port);
    }
    public InetAddress getPacketAddress(){
        return buffer.peek().getAddress();
    }
    public String Read(){
        DatagramPacket packet= buffer.poll();
        return new String(packet.getData(),0, packet.getLength());
    }
    public String Peek(){
        DatagramPacket packet= buffer.peek();
        return new String(packet.getData(),0, packet.getLength());
    }
    public boolean toRead(){
        return !buffer.isEmpty();
    }
    public DatagramPacket getRaw(){
        return buffer.peek();
    }
    public void Send(String data){
        try {
        byte[] buf = new byte[1024];
        buf = data.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length,address,port);
            broadcast.send(packet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

}

