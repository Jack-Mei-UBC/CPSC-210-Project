package model.items;

import model.exceptions.InvalidDateException;
import ui.ToDoList;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public abstract class BaseItem implements Item {
    //EFFECT : returns the data of DueDate
    Date dueDate = new Date();
    ToDoList owner;

    public String toString() {
        String out = "";
        out += this.getClass() + ":";
        out += getName() + ":";
        out += getDescription() + ":";
        out += getDueDateData();

        return (out);
    }

    //MODIFIES : Date date
    //MODIFIES : sets the date to be the input date
    public void setDueDate(String month, String date, String year, String hour, String minute, String second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        cal.set(Calendar.DATE, Integer.parseInt(date));
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.HOUR, Integer.parseInt(hour));
        cal.set(Calendar.MINUTE, Integer.parseInt(minute));
        cal.set(Calendar.SECOND, Integer.parseInt(second));
        dueDate = cal.getTime();
        //from https://stackoverflow.com/questions/14739953/set-a-date-object-java
    }

    //REQUIRES : input is a valid date
    //MODIFIES : THIS
    //EFFECT : sets the dueDate of the item

    public void setDueDate(int month, int da, int year, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, da);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        dueDate = cal.getTime();
        //from https://stackoverflow.com/questions/14739953/set-a-date-object-java
    }

    //MODIFIES : this, ToDoList owner
    //EFFECTS : couples this and owner
    public void addOwnerCheckList(ToDoList owner) {
        if (this.owner != null) {
            this.owner = owner;
            owner.addItemCheckList(this);
        }
    }

    //EFFECTS : returns a string with the data from the internal Date class
    protected String getDueDateData() {
        String out = "";
        out += dueDate.getMonth();
        out += " " + dueDate.getDate();
        out += " " + (dueDate.getYear() + 1900);
        out += " " + dueDate.getHours();
        out += " " + dueDate.getMinutes();
        out += " " + dueDate.getSeconds();
        return (out);
    }

    public abstract String getDescription();

    public abstract String getName();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseItem baseItem = (BaseItem) o;
        return Objects.equals(dueDate, baseItem.dueDate) && Objects.equals(owner, baseItem.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dueDate, owner);
    }
}
