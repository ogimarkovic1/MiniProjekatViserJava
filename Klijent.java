package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class Klijent extends Application implements EventHandler<ActionEvent> {
    VBox sablon;
    Button dugmad[][]=new Button[4][4];
    int min=0;
    int max=15;
     int min1=0;
     int max1=15;
    Label ispis=new Label();
    Button start=new Button("START");
     int count=0;
     boolean flag=false;
      boolean flagovi[]=new boolean[16];
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MinMax");
        sablon=new VBox();

        start.setId("start");
        start.setOnAction(this);
        sablon.getChildren().add(start);
    dodajDugmadIlabelu(sablon);
        Scene scene=new Scene(sablon);
        scene.getStylesheets().add("./stil.css");
        primaryStage.setScene(scene);

        primaryStage.show();

    }


    @Override
    public void handle(ActionEvent actionEvent) {

        if(actionEvent.getSource()==start){
           // ispis.setText("Igra je krenula");
            for (int i=0;i<16;i++)
                flagovi[i]=false;
            flag=true;
        }
        if(flag) {
            if (actionEvent.getSource() == start) {
                for (int i = 0; i < 16; i++)
                    flagovi[i] = false;
                min1 = 0;
                max1 = 15;
                count = 0;
                ispis.setText("Igra je krenula");
                int niz[] = new int[16];
                for (int i = 0; i <= 15; i++)
                    niz[i] = 0;

                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++) {
                        while (true) {
                            int randomNum = (int) (Math.random() * (max - min + 1)) + min;
                            if (niz[randomNum] == 0) {
                                dugmad[i][j].setText(Integer.toString(randomNum));
                                dugmad[i][j].setOnAction(this);
                                niz[randomNum] = 1;
                                break;
                            }
                        }
                    }
            }
            if (actionEvent.toString().contains(Integer.toString(max1))) {
                flagovi[max1] = true;}


             else   if (actionEvent.toString().contains(Integer.toString(min1)) && flagovi[max1]==true) {
                    flagovi[min1] = true;


                    if (flagovi[min1] == true && flagovi[max1] == true) {
                        for (int i = 0; i < 4; i++)
                            for (int j = 0; j < 4; j++) {
                                if (dugmad[i][j].getText().equals(Integer.toString(min1)) || dugmad[i][j].getText().equals(Integer.toString(max1)))
                                    dugmad[i][j].setText(".");
                            }
                        count++;
                        ++min1;
                        --max1;
                    }


                }

            else
                flagovi[max1]=false;



            }
        if (count == 8) {
            ispis.setText("Presli ste igricu.Stisnite start da pokrenete opet");
            flag = false;
        }

        }



















    private void dodajDugmadIlabelu(VBox sablon){
        int niz[]=new int[16];
        for(int i=0;i<=15;i++)
            niz[i]=0;

        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
            {   while (true){
                int randomNum = (int)(Math.random() * (max - min + 1)) + min;
                if(niz[randomNum]==0)
                {
                    dugmad[i][j]=new Button(Integer.toString(randomNum));
                    dugmad[i][j].setOnAction(this);
                    niz[randomNum]=1;
                    break;
                }}
            }


        GridPane gp=new GridPane();


        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                gp.add(dugmad[i][j],j,i);

            sablon.getChildren().add(gp);
ispis.setText("Stisnite start za pokretanje");
sablon.getChildren().add(ispis);
    }
}
