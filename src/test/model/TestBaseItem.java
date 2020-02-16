package model;

import org.junit.jupiter.api.Test;
import model.exceptions.InvalidDateException;
import model.items.Activity;
import model.items.Chore;
import ui.ToDoList;

import static org.junit.jupiter.api.Assertions.*;

public class TestBaseItem {
    @Test
    public void testGetDueDate() {
        Activity activity = new Activity("run");
        try {
            activity.setDueDate(12,31,2019,10,59,59);
        }
        finally {
            assertEquals("Tue Dec 31 22:59:59 PST 2019", activity.getDueDate());
        }
        Activity activity1 = new Activity( "run2");


        try {
            activity1.setDueDate(9,24,2019,2,0,0);
        }
        finally {
            assertEquals("Tue Sep 24 14:00:00 PDT 2019", activity1.getDueDate());
        }
        activity1.setDueDate(-1,24,2019,2,0,0);
    }

    @Test
    public void testDate(){
        Chore chore = new Chore("dishes");
            chore.setDueDate(1,1,1,1,1,1);

        assertEquals("Sat Jan 01 13:01:01 PST 1", chore.getDueDate());


    }

    @Test
    public void testAddOwner(){
        ToDoList toDoList = new ToDoList();
        Activity activity = new Activity("test");
        toDoList.initialize();
        activity.addOwnerCheckList(toDoList);
    }

    @Test
    public void testCompare() {
        Activity activity = new Activity("test");
        assertEquals(activity.equals(activity),true);
    }
}

