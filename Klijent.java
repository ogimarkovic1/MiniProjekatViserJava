package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Klijent extends Application implements EventHandler<ActionEvent> {
    public static final int TCP_PORT = 1234;

    TextField indexi[][]=new TextField[3][4];
    Label ispis;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
                primaryStage.setTitle("Klijent");
                VBox sablon=new VBox();
                Label naslov=new Label("Indeksi u matrici 3x4");
                sablon.getChildren().add(naslov);
                dodajGrid(sablon);
                dodajOstatak(sablon);
                Scene scene=new Scene(sablon);
                scene.getStylesheets().add("./stil.css");
                primaryStage.setScene(scene);
                primaryStage.show();
    }
    private void dodajGrid(VBox sablon){GridPane gp=new GridPane();


       for(int i=0;i<3;i++)
           for(int j=0;j<4;j++)
           {indexi[i][j] = new TextField();
                if(j==0)
                    indexi[i][j].setPromptText("Index x");
                else if(j==1)
                    indexi[i][j].setPromptText("Index y");
                else if(j==2)
                    indexi[i][j].setPromptText("Index z");
                else
                    indexi[i][j].setPromptText("Rezultat");

           }


       for(int i=0;i<3;i++)
           for(int j=0;j<4;j++)
               gp.add(indexi[i][j],j,i);

        sablon.getChildren().add(gp);
    }
    private void dodajOstatak(VBox sablon){
        Button dugme=new Button("Prosledi podatke");
        dugme.setOnAction(this);
        Label label1=new Label("Odgovor servera-resenje jednacine:");
        ispis=new Label();
        sablon.getChildren().addAll(dugme,label1,ispis);


    }

    @Override
    public void handle(ActionEvent actionEvent) {
        boolean flag=false;
        for(int i=0;i<3;i++)
            for(int j=0;j<4;j++)
                if(indexi[i][j].getText().length()==0)
                        flag=true;
    if(!flag){
       try {
            InetAddress address=InetAddress.getByName("127.0.0.1");
            Socket socket=new Socket(address,TCP_PORT);
           BufferedReader in = new BufferedReader(new
                   InputStreamReader(socket.getInputStream()));

           DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            for(int i=0;i<3;i++)
                for(int j=0;j<4;j++)
                    output.writeDouble(Double.parseDouble(indexi[i][j].getText()));

           output.flush();
          String resenje=in.readLine();

        ispis.setText(resenje);
in.close();
output.close();
socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    else
        ispis.setText("Niste uneli sve neophodne podatke");
    }

}
