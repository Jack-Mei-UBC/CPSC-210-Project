package model.items;

import taskten.Component;

import java.util.Calendar;

public class Chore extends BaseItem implements Item {
    protected String name = "";
    protected String description = "";
    private Component component;

    //MODIFY : this
    //EFFECT : creates and sets name of instance
    public Chore(String name) {
        this.name = name;
    }

    //EFFECT : returns name of instance
    public String getName() {
        return (name);
    }

    //MODIFY : this
    //EFFECT : sets description of instance to the description
    public void setDescription(String description) {
        this.description = description;
    }

    //EFFECT : returns description name
    public String getDescription() {
        return (description);
    }


    //EFFECT : returns the date the item must be picked up by

    public String getDueDate() {
        return (dueDate.toString());
    }

}
