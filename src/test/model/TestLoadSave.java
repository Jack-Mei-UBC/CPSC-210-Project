package model;

import model.items.LoadSave;
import org.junit.jupiter.api.Test;
import ui.ToDoList;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestLoadSave {
    private String checkListLocation = "./data/todoListData.txt";
    private String crossListLocation = "./data/crossListData.txt";
    @Test
    public void testLoadSave() throws IOException {
        ToDoList list = new ToDoList();
        list.initialize();
        LoadSave test = new LoadSave(list);
        PrintWriter writer = new PrintWriter(checkListLocation,"UTF-8");
        writer.println("class model.items.Activity:test1::8 30 2019 23 56 10:\n" +
                "        class model.items.GroceryItem:test2::8 30 2019 23 56 14\n" +
                "        class model.items.GroceryItem:test4::8 29 2019 23 56 14\n" +
                "        class model.items.Chore:test3::8 30 2019 23 56 17");
        writer.close();
        test.load();

        assertEquals(4,test.checkList.size());
        assertEquals(0, test.crossList.size());
        test.save();
        writer = new PrintWriter(crossListLocation,"UTF-8");
        writer.println("class model.items.Activity:test1::8 30 2019 23 56 10:\n" +
                "        class model.items.GroceryItem:test2::8 30 2019 23 56 14\n" +
                "        class model.items.GroceryItem:test4::8 30 2019 23 56 14\n" +
                "        class model.items.Chore:test3::8 30 2019 23 56 17");
        writer.close();
        test.load1();
        assertEquals(4,test.crossList.size());
        test.save();
        writer = new PrintWriter(checkListLocation,"UTF-8");
        writer.println(" ");
        writer.close();
        test.load();
        test.load1();
        assertEquals(0,test.checkList.size());
    }

}
