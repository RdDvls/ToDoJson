package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RdDvls on 9/3/16.
 */
public class Container {
    List todoContainer = new ArrayList();

    public Container(List todoContainer) {
        this.todoContainer = todoContainer;
    }

    public List getTodoContainer() {
        return todoContainer;
    }

//    public void setTodoContainer(List todoContainer) {
//        this.todoContainer = todoContainer;
//    }
}
