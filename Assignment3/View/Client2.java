package Comp.Assignment.Assignment3.View;

import Comp.Assignment.Assignment2.Controller.ControllerServer;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String args []) throws IOException {
        ControllerServer controller = new ControllerServer();
        Scanner input = new Scanner(System.in);
        if (controller.loadDataLogin() == true) {
                        try {
                            Socket s = new Socket("localhost", Integer.parseInt(controller.getPropValue("PORT")));
                            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                            System.out.println("Masukkan nama file: ");
                            String file = input.nextLine();
                            dout.writeUTF(file);
                            dout.flush();

                            DataInputStream dis = new DataInputStream(s.getInputStream());

                            String str = (String) dis.readUTF();
                            System.out.println("Message from server= " + str);

                            dout.close();
                            s.close();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
        }
    }

}
