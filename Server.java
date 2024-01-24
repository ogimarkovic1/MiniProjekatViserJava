package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application implements EventHandler<ActionEvent> {
    TextArea ispisServer;
    public static final int TCP_PORT = 1111;



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {

        primaryStage.setTitle("Server");
        VBox sablon = new VBox();
        Button dugme1 = new Button("Pokreni server");
        Label ls = new Label("Stiglo sa klijenta");
        dugme1.setOnAction(this);
        ispisServer=new TextArea();
        sablon.getChildren().addAll(dugme1, ls, ispisServer);
        Scene scene = new Scene(sablon);
        scene.getStylesheets().add("./stil.css");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Server je pokrenut...");


        new Thread(()->{

            try {
                ServerSocket ss = new ServerSocket(TCP_PORT);



while (true){  Socket socket=ss.accept();
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new
                        OutputStreamWriter(socket.getOutputStream())), true);
                     String sa_klijenta = in.readLine();
                    ispisServer.appendText("\n" + sa_klijenta + "\n");

                    String zaslanje = sa_klijenta.replaceAll("\\s+", "");

                    if (palindrom(zaslanje))
                        out.println("Da,string koji ste poslali jeste palindrom!");
                    else
                        out.println("String koji ste poslali nije palindrom");
                    socket.close();
                    in.close();
                    out.close();
                   }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }).start();
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

}





