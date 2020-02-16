package model.items;

import java.util.Calendar;

public class Activity extends BaseItem implements Item, Event {

    protected String name = "";
    protected String description = "";
    //protected Date dueDate = new Date();
    protected String duration = "";

    //MODIFIES : this
    //EFFECTS : creates a new Item class instance and returns it
    public Activity(String name) {
        this.name = name;
    }

    @Override
    //EFFECTS : returns the data of the object in string form
    public String toString() {
        String out = "";
        out += this.getClass() + ":";
        out += getName() + ":";
        out += getDescription() + ":";
        out += getDueDateData() + ":";
        out += getDuration();
        return (out);
    }

    //MODIFIES : this
    //EFFECT : sets duration of the activity
    public void setDuration(String duration) {
        this.duration = duration;
    }

    //EFFECT : return the duration of the activity
    public String getDuration() {
        return ("" + duration);
    }


    //EFFECT : returns the date the item must be picked up by
    public String getDueDate() {
        return (dueDate.toString());
    }


    //EFFECT : return the name of the instance of this object
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


}
