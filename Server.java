package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application implements EventHandler<ActionEvent> {
    public static final int TCP_PORT = 1234;
    TextArea ispisServer;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("Server");
        VBox sablon=new VBox();

        Button dugme1=new Button("Pokreni Server");
        dugme1.setOnAction(this);
        ispisServer=new TextArea();
        sablon.getChildren().addAll(dugme1,ispisServer);
        Scene scene=new Scene(sablon);
        scene.getStylesheets().add("./stil.css");
        primaryStage.setScene(scene);

          primaryStage.show();


    }


    @Override
    public void handle(ActionEvent actionEvent) {
     ispisServer.appendText("Server je pokrenut...");

        new Thread(()->{
            try {double D,Dx,Dy,Dz,x,y,z;

                ServerSocket serverSocket = new ServerSocket(TCP_PORT);
while(true){
                Socket socket = serverSocket.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                double[][] coefficients = new double[3][4];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        coefficients[i][j] = input.readDouble();
                    }
                }
        D=coefficients[0][0]*coefficients[1][1]*coefficients[2][2]
        +coefficients[0][1]*coefficients[1][2]*coefficients[2][0]
        +coefficients[0][2]*coefficients[1][0]*coefficients[2][1]
        -coefficients[0][2]*coefficients[1][1]*coefficients[2][0]
        -coefficients[0][0]*coefficients[1][2]*coefficients[2][1]
        -coefficients[0][1]*coefficients[1][0]*coefficients[2][2];
           if(D==0)
               out.println("Jednacina nema resenje");
           else{
                Dx=coefficients[0][3]*coefficients[1][1]*coefficients[2][2]
                +coefficients[0][1]*coefficients[1][2]*coefficients[2][3]+
                        coefficients[0][2]*coefficients[1][3]*coefficients[2][1]
                -coefficients[0][2]*coefficients[1][1]*coefficients[2][3]-
                        coefficients[0][3]*coefficients[1][2]*coefficients[2][1]
                -coefficients[0][1]*coefficients[1][3]*coefficients[2][2];

                Dy=coefficients[0][0]*coefficients[1][3]*coefficients[2][2]
                +coefficients[0][3]*coefficients[1][2]*coefficients[2][0]
                +coefficients[0][2]*coefficients[1][0]*coefficients[2][3]-
                coefficients[0][2]*coefficients[1][3]*coefficients[2][0]
                -coefficients[0][0]*coefficients[1][2]*coefficients[2][3]-
                coefficients[0][3]*coefficients[1][0]*coefficients[2][2];

                Dz=coefficients[0][0]*coefficients[1][1]*coefficients[2][3]
                +coefficients[0][1]*coefficients[1][3]*coefficients[2][0]
                +coefficients[0][3]*coefficients[1][0]*coefficients[2][1]
                -coefficients[0][3]*coefficients[1][1]*coefficients[2][0]
                 -coefficients[0][0]*coefficients[1][3]*coefficients[2][1] -coefficients[0][1]*coefficients[1][0]*coefficients[2][3];

                x=Dx/D;
               y=Dy/D;
               z=Dz/D;
               out.println("Resenja jednacine su:x="+String.format("%.2f", x)+",y="+String.format("%.2f", y)+",z="+String.format("%.2f", z));


           } socket.close();
          input.close();

           out.close(); } }
            catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
