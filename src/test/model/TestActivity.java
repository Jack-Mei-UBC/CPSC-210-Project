package model;

import org.junit.jupiter.api.Test;
import model.items.Activity;

import static org.junit.jupiter.api.Assertions.*;

public class TestActivity {

    @Test
    public void testDescription() {
        Activity activity = new Activity("run");
        activity.setDescription("this is a test getDescription");
        assertEquals("this is a test getDescription", activity.getDescription());

        Activity activity1 = new Activity("run");
        assertEquals("",activity1.getDescription());

    }

    @Test
    public void testName() {
        Activity activity = new Activity("run");
        assertEquals( "run", activity.getName());
        Activity activity2 = new Activity("asdf123");
        assertEquals( "asdf123", activity2.getName());
        Activity activity3 = new Activity("");
        assertEquals("",activity3.getName());
    }

    @Test
    public void testDuration(){
        Activity activity = new Activity("run");
        activity.setDuration("One hour");
        assertEquals("One hour", activity.getDuration());
        activity.setDuration("");
        assertEquals("", activity.getDuration());
    }

    @Test
    public void testToString() {

        Activity activity = new Activity("run");
        activity.setDueDate(12,31,2019,10,59,59);

        System.out.println(activity.toString());
        assertEquals(activity.toString(), "class model.items.Activity:run::11 31 2019 22 59 59:");
    }

}
