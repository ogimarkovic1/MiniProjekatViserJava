/*package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
    Server server;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    ServerThread(Server server,Socket socket) throws IOException {
        this.socket=socket;
        this.server=server;
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));

        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);

        start();
    }

    @Override
    public void run() {
        try {
            String sa_klijenta=in.readLine();


            String zaslanje=sa_klijenta.replaceAll("\\s+","");


            if(palindrom(zaslanje))
                out.println("Da,string koji ste poslali jeste palindrom!");
            else
                out.println("String koji ste poslali nije palindrom");
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static boolean palindrom(String str) {
        int levi = 0;
        int desni = str.length() - 1;

        while (levi < desni) {

            if (str.charAt(levi) != str.charAt(desni)) {
                return false;
            }

            levi++;
            desni--;
        }

        return true;
    }
}*/
