package model;

import org.junit.jupiter.api.Test;
import model.items.Activity;
import model.items.GroceryItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGroceryItem {

    @Test
    public void testGetDueDate() {
        GroceryItem groceryItem = new GroceryItem("milk");
        groceryItem.setDueDate(12,31,2019,10,59,59);
        GroceryItem groceryItem1 = new GroceryItem( "eggs");
        groceryItem1.setDueDate(9,24,2019,2,0,0);

        assertEquals("Tue Dec 31 22:59:59 PST 2019", groceryItem.getDueDate());
        assertEquals("Tue Sep 24 14:00:00 PDT 2019", groceryItem1.getDueDate());
    }

    @Test
    public void testDescription() {
        GroceryItem groceryItem = new GroceryItem("milk");
        groceryItem.setDescription("this is a test getDescription");
        assertEquals("this is a test getDescription", groceryItem.getDescription());

        GroceryItem groceryItem1 = new GroceryItem("eggs");
        assertEquals("",groceryItem1.getDescription());

    }

    @Test
    public void testName() {
        GroceryItem groceryItem = new GroceryItem("milk");
        assertEquals( "milk", groceryItem.getName());
        GroceryItem groceryItem1 = new GroceryItem("milk123312");
        assertEquals( "milk123312", groceryItem1.getName());
        Activity groceryItem2 = new Activity("");
        assertEquals("",groceryItem2.getName());
    }

    @Test
    public void testLocation(){
        GroceryItem groceryItem = new GroceryItem("milk");
        groceryItem.setLocation("Wal-mart");
        assertEquals("Wal-mart", groceryItem.getLocation());
        groceryItem.setLocation("");
        assertEquals("", groceryItem.getLocation());
        groceryItem.setLocation("123456zxc,.");
        assertEquals("123456zxc,.",groceryItem.getLocation());
    }
    @Test
    public void testDate(){
        GroceryItem groceryItem = new GroceryItem("milk");
        groceryItem.setDueDate(1,1,1,1,1,1);
        assertEquals("Sat Jan 01 13:01:01 PST 1", groceryItem.getDueDate());
        groceryItem.setDueDate("1", "1","1","1","1","1");
        assertEquals("Sat Jan 01 13:01:01 PST 1", groceryItem.getDueDate());
        //these differ as one gets the month with 0 -> jan, while the other is 1 -> jan
    }
    @Test
    public void testToString(){
        GroceryItem groceryItem = new GroceryItem("milk");
        groceryItem.setDueDate(1,1,1,1,1,1);
        assertEquals("class model.items.GroceryItem:milk::0 1 1 13 1 1",groceryItem.toString());
        groceryItem.setDueDate("1","1","1","1","1","1");
        assertEquals("class model.items.GroceryItem:milk::0 1 1 13 1 1",groceryItem.toString());
    }
}
