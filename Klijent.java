package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Klijent extends Application implements EventHandler<ActionEvent> {
    TextField unos;
    TextArea ispis;
    Label ispis1;
    public static final int TCP_PORT = 1111;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Klijent");

        VBox sablon=new VBox();
        dodajSablon(sablon);


        Scene scene=new Scene(sablon);
        scene.getStylesheets().add("./stil.css");
        primaryStage.setScene(scene);


        primaryStage.show();
    }
    private void dodajSablon(VBox sablon){
        Label label1=new Label("Unesite neki tekst za proveru");
        unos=new TextField();
        unos.setPromptText("Unesite tekst za proveru");
        Button dugme=new Button("Prosledi serveru");
        dugme.setOnAction(this);
        Label label2=new Label("Odgovor servera");
      //  ispis=new TextArea();
        ispis1=new Label();
        sablon.getChildren().addAll(label1,unos,dugme,label2,ispis1);


    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {

            if(unos.getText().length()>0)
            { InetAddress address=InetAddress.getByName("127.0.0.1");
                Socket socket=new Socket(address,TCP_PORT);
                BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                out.println(unos.getText());
                String odgovor=in.readLine();

                ispis1.setText(odgovor);
                in.close();
                out.close();
                socket.close();


            }
            else
                ispis1.setText("Niste uneli nikakav tekst");


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
