package netTool;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import screen.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import asciiPanel.*;
import javax.swing.JFrame;
//Client与Server互相发消息，Server将得分纪录到排名文件中，返回给Client排名
public class NetServer {
    private final static int Port = 8080;
    private static String[] fileContentArr;
    private static byte[] fileContent;
    public NetServer() {

        try {
            FileInputStream in = new FileInputStream("rougegame/Grade.txt");
           fileContent=new byte[in.available()];
            in.read(fileContent);
            in.close();
            fileContentArr = new String(fileContent).split("\r\n");//按回车分割string
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  void serverStart ()throws IOException {
        NetServer newServer=new NetServer();
        DatagramSocket localServer = new DatagramSocket(Port);
        byte[] newscore = new byte[1];
        DatagramPacket packet = new DatagramPacket(newscore, newscore.length);
        System.out.println("Server Start");
        localServer.receive(packet);
        String grade = new String(packet.getData());
        int rank=fileContentArr.length+1;
        for(int i=0;i<fileContentArr.length;i++){//获取排名
            if(grade.compareTo(fileContentArr[i])>=0) {
                rank--;
            }
        }
     
        String fin=new String();
        for(int i=0;i<fileContentArr.length;i++){
            fin=fin+fileContentArr[i]+"\r\n";
        }
        fin+=grade;
        FileOutputStream fos = new FileOutputStream("rougegame/Grade.txt");//写入文件
            fos.write(fin.getBytes());
            fos.flush();
            fos.close();
        InetAddress add = packet.getAddress();
        int cport = packet.getPort();
        String rankArr=String.valueOf(rank);
        byte[] data=rankArr.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, add, cport);
        localServer.send(sendPacket);
        System.out.println("Server Close");
        localServer.close();
    }
}