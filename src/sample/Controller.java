package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    public String userName;
    String fileName = "todos.json";
    @FXML
    ListView todoList;

    @FXML
    TextField todoText;

    ObservableList<ToDoItem> todoItems = FXCollections.observableArrayList(); //makes an observable array list of type todoItem
    ArrayList<ToDoItem> savableList = new ArrayList<ToDoItem>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Please enter your name: ");
        Scanner inputScanner = new Scanner(System.in);
        userName = inputScanner.nextLine();
//        todoList.setItems(todoItems);



        if (userName != null && !userName.isEmpty()) {
            fileName = userName + ".json";
        }

        ToDoItemList existingList = retrieveList();

        if (existingList != null) {
            for (ToDoItem item : existingList.todoItems) {
                todoItems.add(item);
            }
        }
        todoList.setItems(todoItems);
    }

    public void saveToDoList() {
        if (todoItems != null && todoItems.size() > 0) {
            System.out.println("Saving " + todoItems.size() + " items in the list");
            savableList = new ArrayList<ToDoItem>(todoItems);
            System.out.println("There are " + savableList.size() + " items in my savable list");
            saveList();
        } else {
            System.out.println("No items in the ToDo List");
        }
    }

    public void saveFile(String jsonString) {       //Writes to file
        try {
            File jsonFile = new File("todos.json");
            FileWriter jsonWriter = new FileWriter(jsonFile);
            jsonWriter.write(jsonString);
            jsonWriter.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

//    public String jsonSave(String todoToSave) {
//        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
//        String jsonString = jsonSerializer.serialize(todoToSave);
//
//        return jsonString;
//    }
//
//    public ToDoItem jsonRestore(String jsonTD) {
//        JsonParser toDoItemParser = new JsonParser();
//        ToDoItem item = toDoItemParser.parse(jsonTD, ToDoItem.class);
//
//        return item;
//    }


    public void addItem() {
        System.out.println("Adding item ...");
        todoItems.add(new ToDoItem(todoText.getText()));

        todoText.setText("");
    }




    public void removeItem() {
        ToDoItem todoItem = (ToDoItem) todoList.getSelectionModel().getSelectedItem();
        System.out.println("Removing " + todoItem.text + " ...");
        todoItems.remove(todoItem);
    }

    public void markAllDone() {
        System.out.println("Marking all as done");
        for (ToDoItem item : todoItems) {
            if (item != null) {
                item.isDone = !item.isDone;
                todoList.setItems(null);
                todoList.setItems(todoItems);
            }
        }
    }

    public void toggleAll() {
        System.out.println("Toggling all ...");
        for (ToDoItem item : todoItems) {
            if (item != null) {
                item.isDone = !item.isDone;
                todoList.setItems(null);
                todoList.setItems(todoItems);
            }
        }
    }

    public void markAllIncomplete() {
        System.out.println("Marking all incomplete");
        for (ToDoItem item : todoItems) {
            if (item != null) {
                item.isDone = false;
                todoList.setItems(null);
                todoList.setItems(todoItems);
            }
        }
    }

    public void toggleItem() {
        System.out.println("Toggling item ...");
        ToDoItem todoItem = (ToDoItem) todoList.getSelectionModel().getSelectedItem();
        if (todoItem != null) {
            todoItem.isDone = !todoItem.isDone;
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
    }

    public void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            todoItems.add(new ToDoItem(todoText.getText()));
            todoText.setText("");
        }
    }


    public ToDoItemList retrieveList() {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            fileScanner.useDelimiter("\\Z");
            String fileContents = fileScanner.next();
            JsonParser ToDoItemParser = new JsonParser();
            ToDoItemList theListContainer = ToDoItemParser.parse(fileContents, ToDoItemList.class);
            if(theListContainer != null){
                for (ToDoItem item : theListContainer.todoItems) {
                    todoItems.add(item);
                }
            }
            return theListContainer;
        } catch (IOException ioException) {
            return null;
        }

    }

    public void saveList() {
        try {

            JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
            String jsonString = jsonSerializer.serialize(new ToDoItemList(todoItems));

//            System.out.println("JSON = ");
//            System.out.println(jsonString);

            File sampleFile = new File(fileName);
            FileWriter jsonWriter = new FileWriter(sampleFile);
            jsonWriter.write(jsonString);
            jsonWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

