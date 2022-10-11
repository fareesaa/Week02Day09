package Comp.Assignment.Assignment1;

import Comp.Assignment.Properties.CrunchifyGetPropertyValues;

import javax.sound.sampled.Port;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class AssServer1 {
    public static void main(String args[]){
        String fileconfig = args[0];
        Scanner inputStr = new Scanner(System.in);
        Properties prop = new Properties();
        InputStream inputS = null;
        try{
            inputS = new FileInputStream(fileconfig);
            prop.load(inputS);
            ServerSocket ss = new ServerSocket(Integer.parseInt(prop.getProperty("PORT")));
            Socket s = ss.accept();

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout =new DataOutputStream(s.getOutputStream());

            String chat="";
            String reply = "";
            while (!reply.equals("EXIT")||!chat.equals("EXIT")){
                reply = (String)dis.readUTF();
                System.out.println("Client = "+reply);
                chat = inputStr.nextLine();
                dout.writeUTF(chat);
            }
            dout.flush();
            dout.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
