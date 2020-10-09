import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javafx.event.*;

public class SpotifySlider extends Application
{
   private String selectedValue;
   private double selectedValueDouble;
   private ArrayList<MusicObject> musicObjects = new ArrayList<MusicObject>();
   private ArrayList<Label> LabelArray;
   private Pane pane = new Pane();
   private Label label = new Label();
   private Button addSong = new Button("Add Song");
   private TextArea songArea = new TextArea();
   private String songString;
   
   public void start(Stage primaryStage)  throws IOException
   {
      //create a PaneWithRactangles object. 
      
      BorderPane borderPane = new BorderPane();
      Label valueLabel = new Label();
      
      
      Slider slider = new Slider(0, 1, .5);
      slider.setOrientation(Orientation.VERTICAL);
      slider.setMajorTickUnit(0.1f);
      slider.setMinorTickCount(5);
      slider.setBlockIncrement(0.125f);
      slider.setSnapToTicks(true);
      slider.setShowTickMarks(true);
      slider.setShowTickLabels(true);
      borderPane.setCenter(slider);
      
      
      File file = new File("VibeChamberPlaylistRanked.txt");
      Scanner scn = new Scanner(file);
      String str;
      String songNameError;
      String songName;
      String link;
      String ratingString;
      String songNameTest;
      int rating = 0;
      int count = 0;
      while(scn.hasNextLine() && count < 84){
         scn.useDelimiter(";");
         songNameTest = scn.next();
         StringBuilder sb = new StringBuilder(songNameTest);
         sb.deleteCharAt(0);
         songName = sb.toString();
         link = scn.next();
         ratingString = scn.next();
         rating = Integer.parseInt(ratingString);
         MusicObject someMusic = new MusicObject(songName, link, rating);
         musicObjects.add(someMusic);
         System.out.println("Successfully added");
         count++;
         
         
      }
      
      slider.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
               selectedValue = String.format("%.2f", newValue);
               valueLabel.setText(String.format("%.2f", newValue));
            }
        }); 
       
      ButtonHandler button = new ButtonHandler();
      addSong.setOnAction(button);
      pane.getChildren().add(songArea);
      borderPane.setBottom(valueLabel);
      borderPane.setRight(pane);
      borderPane.setTop(addSong);      
      //put gui on top of the rootPane
      
   
      // Create a scene and place rootPane in the stage
      Scene scene = new Scene(borderPane, 600, 400);
      primaryStage.setTitle("Spotify Slider"); 
      primaryStage.setScene(scene); // Place the scene in the stage
      primaryStage.show(); // Display the stage
   }
   
      
   
   private class ButtonHandler implements EventHandler<ActionEvent>{
   
      public void handle(ActionEvent event){
      
         selectedValueDouble = Double.parseDouble(selectedValue);
         selectedValueDouble *= 10;
         int selectedValueInt = (int) selectedValueDouble;
         double randomVar = Math.random();
         randomVar *= 84;
         int randomInt = (int) randomVar;
         System.out.println(randomInt);
         for(int i = randomInt; i < musicObjects.size()-1; i++){
            if(musicObjects.get(i).getRating() == selectedValueInt){
               System.out.println(musicObjects.get(i).toString());
               songString = "Song Name:\t" + musicObjects.get(i).getSongName() + "\nRating:\t" + musicObjects.get(i).getRating();
               songArea.setText(songString);
               i=musicObjects.size()-1;
            }
         }

      
      
      }
   }
   
   
   
   
   
   public static void main(String[] args) throws IOException{
      Application.launch(args);
   }
}
