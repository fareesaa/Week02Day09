package Comp.Assignment.Assignment2.View;

import Comp.Assignment.Assignment2.Controller.ControllerServer;

import java.io.IOException;
import java.util.Scanner;

public class Server2 {
    public static void main(String args[]) throws IOException {
        ControllerServer controller = new ControllerServer();
        Scanner input = new Scanner(System.in);
        if (controller.loadDataLogin() == true) {
            controller.serverUp();

            controller.serverRead();

            controller.serverDown();
        }
    }
}
