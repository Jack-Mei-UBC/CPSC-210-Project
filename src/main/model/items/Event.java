package model.items;

import model.exceptions.InvalidDateException;

public interface Event {
    String getDuration();

    String getDueDate();

    String getName();

    String getDescription(); //Gives a description of the item

    void setDueDate(int month, int date, int year, int hour, int minute, int second) throws InvalidDateException;

    void setDueDate(String month, String date, String year, String hour, String minute, String second); //Sets due date

    void setDescription(String description); //Gives description of event
}
