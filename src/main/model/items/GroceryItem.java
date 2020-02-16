package model.items;

import java.util.Calendar;

public class GroceryItem extends BaseItem implements Item {
    protected String name = "";
    protected String description = "";
    protected String location = "";

    //MODIFIES : this
    //EFFECTS : creates a new Item class instance and returns it
    public GroceryItem(String name) {
        this.name = name;
    }

    //REQUIRES : input is a valid date
    //MODIFIES : THIS
    //EFFECT : sets the dueDate of the item
    public void setDueDate(int month, int date, int year, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, date);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        dueDate = cal.getTime();
        //from https://stackoverflow.com/questions/14739953/set-a-date-object-java
    }


    //EFFECT : returns the date the item must be picked up by
    public String getDueDate() {
        return (dueDate.toString());
    }


    //EFFECTS : return the name of the instance of this object
    public String getName() {
        return (this.name);
    }

    //MODIFIES : this
    //EFFECTS : changes description of item
    public void setDescription(String description) {
        this.description = description;
    }

    //EFFECTS : Gives a description of the item
    public String getDescription() {
        return (description);
    }

    //MODIFIES : this
    //EFFECTS : sets location of the item
    public void setLocation(String location) {
        this.location = location;
    }

    //EFFECTS : returns location
    public String getLocation() {
        return (this.location);
    }
}
