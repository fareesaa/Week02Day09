package Comp.Assignment.Assignment3.Controller;

import Comp.Assignment.Assignment2.Model.Model;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.regex.Pattern;

public class ControllerServer {
    ServerSocket ss;
    Socket s;
    DataInputStream dis;
    DataOutputStream dout;
    Socket sc;
    DataInputStream disc;
    DataOutputStream doutc;
    Scanner input;
    String srvmsg;
    int port;
    String ip;
    Map m = new HashMap<>();

    Scanner scan = new Scanner(System.in);
    public boolean loadDataLogin(){
        boolean login = false;
        try{
            System.out.println("Masukkan Username anda: ");
            String uname  = scan.next();
            System.out.println("Masukkan Password anda: ");
            String pass  = scan.next();
            FileReader fr1 = new FileReader("C:\\Users\\BTPNSSHIFTED\\Task\\subtask\\Week02Day09\\file\\Username.txt");

            FileReader fr2 = new FileReader("C:\\Users\\BTPNSSHIFTED\\Task\\subtask\\Week02Day09\\file\\Password.txt");

            int x=0;
            String username = "";
            while(( x=fr1.read())!=-1){
                username = username + (char)x;
            }


            int y=0;
            String password= "";
            while (( y = fr2.read())!=-1){
                password = password+(char)y;
            }
            System.out.println(uname);
            System.out.println(password);

            String regUname = "^[A-Za-z0-9+_.-]+@(.+)$";
            boolean regexUsername = Pattern.matches(regUname, uname);
            String regPass = ".{8,32}" + "[a-zA-z]*" + "\\w*";
            boolean regexPassword = Pattern.matches(regPass, pass);
            if(regexUsername==true && regexPassword==true){
                login = true;

            }else{
                login = false;
            }

            if(login==true && uname.equals(username) && pass.equals(password)){
                System.out.println("===Selamat login telah berhasil===");
            }
            else {
                System.out.println("Login gagal!");
            }
            fr1.close();
            fr2.close();
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("load success...");
        return login;
    }
    public String getPropValue(String key)throws IOException{
        System.out.println("Read Config");
        InputStream inputstream = null;
        String ret="";

        try{
            Properties properties = new Properties();
            String propFileName = "config.properties";

            inputstream =getClass().getClassLoader().getResourceAsStream(propFileName);
            if(inputstream != null) {
                properties.load(inputstream);
            }else {
                throw new FileNotFoundException("File "+propFileName+ " tidak dapat ditemukan pada classpath");
            }
            ret = properties.getProperty(key);

            System.out.println(ret+":" +key);


        }catch (Exception e){
            System.out.println(e);
        }finally {
            inputstream.close();
        }
        return  ret;
    }
    public void serverUp(){
        try {
            this.ss = new ServerSocket(Integer.parseInt(getPropValue("PORT")));
            this.s = ss.accept();

            dis = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void serverDown() throws IOException {
        this.ss.close();
    }
    public void serverReadMsg() throws IOException {
        System.out.println("print Data");
        String reqClient = (String)dis.readUTF() ;

        String [] parsedReqStrGet = reqClient.trim().split("\n");
        for (int i = 0; i < parsedReqStrGet.length; i++) {
            String [] parsedReqStrGet2 = parsedReqStrGet[i].trim().split(",");
            ArrayList<Double> alNilai = new ArrayList<>();
            int id = i+1;
            String nama = parsedReqStrGet2[0];
            for(int x= 1; x<4;x++){
                System.out.println(x);
                Double nilai = Double.parseDouble(parsedReqStrGet2[x]);
                alNilai.add(nilai);
                System.out.println(nilai);
            }
            m.put(id,new Model(id, nama, alNilai));
        }
    }
    public void serverReadClientData() throws IOException {
        System.out.println("print Data");
        String reqDirClient = (String)dis.readUTF();
        String [] parsedRequest = reqDirClient.trim().split(",");
        String dir = parsedRequest[0];
        String file = parsedRequest[1];
        /*FileReader fr = new FileReader(dir+"\\"+file+".txt");*/
        FileReader fr = new FileReader("C:\\Users\\BTPNSSHIFTED\\Task\\subtask\\Week02Day09\\file\\data.txt");
        BufferedReader br = new BufferedReader(fr);

        int x;
        String isi ="";

        while(( x=fr.read())!=-1){
            isi = isi + ((char) x);
        }
        String [] parsedIsiNl = isi.trim().split("\n");
        for(int i = 1; i<parsedIsiNl.length; i++){

            String [] parseComma = parsedIsiNl[i].split(",");

            int getId = Integer.parseInt(parseComma[0]);
            System.out.println("Nama: "+parseComma[1]);
            System.out.println("Nilai Fisika: "+parseComma[2]);
            System.out.println("Nilai Biologi: "+parseComma[3]);
            System.out.println("Nilai Kimia: "+parseComma[4]);

        }
        br.close();
    }

    public void serverRead() throws IOException {
        System.out.println("Read Data");

        input  = new Scanner(System.in);
        String msg = "Data Done Processing";

        String  reqFileClient=(String)dis.readUTF();

        FileReader fr = new FileReader("C:\\Users\\BTPNSSHIFTED\\Task\\subtask\\Week02Day09\\file\\"+reqFileClient+".txt");
        BufferedReader br = new BufferedReader(fr);
        System.out.println("Message from Client = "+reqFileClient);
        int x;
        String isi ="";

        while(( x=fr.read())!=-1){
            isi = isi + ((char) x);
        }
        String [] parsedStrGet = isi.trim().split("\n");
        for (int i = 0; i < parsedStrGet.length; i++) {
            String [] parsedStrGet2 = parsedStrGet[i].trim().split(",");

            System.out.println("Nama :"+parsedStrGet2[0]);
            System.out.println("Nilai Fisika :"+parsedStrGet2[1]);
            System.out.println("Nilai Kimia :"+parsedStrGet2[2]);
            System.out.println("Nilai Biologi :"+parsedStrGet2[3]);

        }
        dout.writeUTF(msg);
        dout.flush();

        dout.close();
    }
    public void clientUp(){
        try {
            this.sc = new Socket("localhost",Integer.parseInt(getPropValue("PORT")));
            disc = new DataInputStream(sc.getInputStream());
            doutc = new DataOutputStream(sc.getOutputStream());

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
