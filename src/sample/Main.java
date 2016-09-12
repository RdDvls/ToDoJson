package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        Controller myController = new Controller();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Stage is closing. Now saving...");
                myController.saveList();
            }
        });
        primaryStage.show();
    }

    public void saveFile (String jsonString){       //Writes to file
        try{
            File jsonFile = new File("ToDoInput.json");
            FileWriter jsonWriter = new FileWriter(jsonFile);
            jsonWriter.write(jsonString);
            jsonWriter.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        ToDoItem hardCodedItem = new ToDoItem("This is the string");
//        Main myMain = new Main();
//        String savedTosoAsJson = myMain.jsonSave();
//        System.out.println("Todo as Json: " + savedTosoAsJson);
//        myMain.saveFile(savedTosoAsJson);
        launch(args);
    }
}