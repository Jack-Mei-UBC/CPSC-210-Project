package model;

import org.junit.jupiter.api.Test;
import model.items.Chore;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestChore {

    @Test
    public void testDescription() {
        Chore chore = new Chore("dishes");
        chore.setDescription("this is a test getDescription");
        assertEquals("this is a test getDescription", chore.getDescription());

        Chore chore1 = new Chore("clothes");
        assertEquals("",chore1.getDescription());

    }

    @Test
    public void testName() {
        Chore chore = new Chore("dishes");
        assertEquals( "dishes", chore.getName());
        Chore chore1 = new Chore("asdf123");
        assertEquals( "asdf123", chore1.getName());
        Chore chore3 = new Chore("");
        assertEquals("",chore3.getName());
    }



}
