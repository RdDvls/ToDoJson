package sample;

/**
 * Created by RdDvls on 9/12/16.
 */
import java.util.ArrayList;
import java.util.List;


public class ToDoItemList {
    public ArrayList<ToDoItem> todoItems = new ArrayList<ToDoItem>();

    public ToDoItemList(List<ToDoItem> incomingList) {
        todoItems = new ArrayList<ToDoItem>(incomingList);
    }

    public ToDoItemList() {

    }
}
