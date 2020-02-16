package model.items;


import model.exceptions.InvalidDateException;
import ui.ToDoList;

public interface Item {

    String getName(); //Gives the name of the item

    String getDescription(); //Gives a description of the item

    String getDueDate(); //Gives the date it must be done by

    void setDescription(String description); //Gives description of event

    void setDueDate(int month, int da, int year, int hour, int minute, int second);

    void setDueDate(String month, String date, String year, String hour, String minute, String second); // Sets due date

    public void addOwnerCheckList(ToDoList owner);

    @Override
    String toString();
}
