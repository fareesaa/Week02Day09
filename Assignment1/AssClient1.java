package Comp.Assignment.Assignment1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class AssClient1 {
    public static void main(String args[]){
        Scanner inputStr = new Scanner(System.in);
        try{
            Properties prop = new Properties();
            String propFileName = "config.properties";
            String portStr = prop.getProperty("PORT");
            int port = Integer.parseInt(portStr);
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();

            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = (String)dis.readUTF();
            System.out.println("message = "+str);

            DataOutputStream dout =new DataOutputStream(s.getOutputStream());
            String msgClient = inputStr.nextLine();
            dout.writeUTF(msgClient);
            dout.flush();
            dout.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
