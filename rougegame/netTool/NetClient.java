package netTool;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
//Client与Server互相发消息，Clinet将得分送给Server，获得排名后返回
public class NetClient {
    private String rank;

    public NetClient(int grade) throws IOException {
        String playGrade=String.valueOf(grade);
        InetAddress add = InetAddress.getByName("localhost");
        int port = 8080;
        DatagramPacket sendPacket = new DatagramPacket(playGrade.getBytes(),playGrade.getBytes().length , add, port);
        DatagramSocket localSocket = new DatagramSocket();
        localSocket.send(sendPacket);
        byte[] data2 = new byte[10];
        DatagramPacket receivePacket = new DatagramPacket(data2, data2.length);
        System.out.println("Sending Grade To local host");
        localSocket.receive(receivePacket );
        String arr = new String(receivePacket.getData());
        this.rank=arr;
        System.out.println("Finish");
        localSocket.close();
    }

    public String getRank() {
        return this.rank;
    }
}
