import org.junit.jupiter.api.Test;
import model.items.Activity;
import model.items.Item;
import ui.ToDoList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestToDoList {

    @Test
    public void testParseInteger() {
        ToDoList toDoList = new ToDoList();
        assertEquals(5, toDoList.parseInput("5"));
        assertEquals(0, toDoList.parseInput("0"));
        assertEquals(999, toDoList.parseInput("999"));

    }

    @Test
    public void testPrintList() {
        ToDoList toDoList = new ToDoList();

        toDoList.initialize();

        Item test = new Activity("test");
        toDoList.addItemCheckList(test);
        toDoList.crossList.add(test);
        toDoList.printList();
    }

    @Test
    public void testAddCrossItem() {
        ToDoList toDoList = new ToDoList();
        toDoList.initialize();
        toDoList.checkList.clear();
        toDoList.crossList.clear();
        Item test = new Activity("test");
        toDoList.addItemCheckList(test);
        assertEquals(1, toDoList.checkList.size());
        toDoList.crossItem(0);
        assertEquals(1, toDoList.crossList.size());
    }

}
