package model.items;

import ui.ToDoList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadSave {
    public ArrayList<Item> checkList;
    public java.util.ArrayList<Item> crossList;
    private String checkListLocation = "./data/todoListData.txt";
    private String crossListLocation = "./data/crossListData.txt";
    private ToDoList owner;

    public LoadSave(ToDoList owner) {
        this.owner = owner;
        checkList = new ArrayList<Item>();
        crossList = new ArrayList<Item>();
    }

    //MODIFIES : Activity item
    //EFFECTS : creates an Activity with the specifications from the list entered in.
    public Item createActivity(List<String> lines, int k) {
        String[] date;
        lines.set(k,getNextSplit(lines.get(k)));
        Activity item = new Activity(lines.get(k).substring(0,lines.get(k).indexOf(":")));
        lines.set(k,getNextSplit(lines.get(k)));
        item.setDescription((lines.get(k).substring(0,lines.get(k).indexOf(":"))));
        lines.set(k,getNextSplit(lines.get(k)));
        date = (lines.get(k).substring(0,lines.get(k).indexOf(":"))).split(" ");
        item.setDueDate(date[0],date[1],date[2],date[3],date[4],date[5]);
        lines.set(k,getNextSplit(lines.get(k)));
        item.setDuration(lines.get(k));
        return (item);
    }

    //MODIFIES : GroceryItem item
    //EFFECTS : creates an GroceryItem with the specifications from the list entered in.
    public Item createGroceryItem(List<String> lines, int k) {
        lines.set(k,getNextSplit(lines.get(k)));
        Item item = new GroceryItem((lines.get(k).substring(0,lines.get(k).indexOf(":"))));
        createItem(item, lines.get(k));
        return (item);
    }

    //MODIFIES : Chore item
    //EFFECTS : creates an Chore with the specifications from the list entered in.

    public Item createChore(List<String> lines, int k) {
        lines.set(k,getNextSplit(lines.get(k)));
        Item item = new Chore(lines.get(k).substring(0,lines.get(k).indexOf(":")));
        createItem(item, lines.get(k));
        return (item);
    }

    //COHESION 2
    public String getNextSplit(String input) {
        return (input.substring(input.indexOf(":") + 1));
    }

    //MODIFIES : Item item
    //EFFECTS : creates an item with the specifications from the string entered in
    //COHESION 2
    public void createItem(Item item, String input) {
        String[] date;
        input = getNextSplit(input);
        item.setDescription(input.substring(0,input.indexOf(":")));
        input = getNextSplit(input);
        date = (input.split(" "));
        item.setDueDate(date[0],date[1],date[2],date[3],date[4],date[5]);
    }

    //MODIFIES : this
    //EFFECTS : loads the program from the save file
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(checkListLocation));;
        checkList.clear();
        for (int k = 0; k < lines.size();k++) {
            if (!(lines.get(k).indexOf(":") < 0)) {
                if (lines.get(k).substring(0,lines.get(k).indexOf(":")).equals("class model.items.Activity")) {
                    addItemCheckList(createActivity(lines, k));
                } else if (lines.get(k).substring(lines.get(k).indexOf("c"),
                        lines.get(k).indexOf(":")).equals("class model.items.GroceryItem")) {
                    addItemCheckList(createGroceryItem(lines,k));
                } else if (lines.get(k).substring(lines.get(k).indexOf("c"),
                        lines.get(k).indexOf(":")).equals("class model.items.Chore")) {
                    System.out.println(lines.get(k).substring(0,lines.get(k).indexOf(":")));
                    addItemCheckList(createChore(lines,k));
                }
            }

        }

    }

    //MODIFIES : this
    //EFFECTS : loads the program from the save file
    public void load1() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(crossListLocation));;
        crossList.clear();
        for (int k = 0; k < lines.size();k++) {
            if (lines.get(k).substring(0,lines.get(k).indexOf(":")).equals("class model.items.Activity")) {
                crossList.add(createActivity(lines, k));
            } else if (lines.get(k).substring(lines.get(k).indexOf("c"),
                    lines.get(k).indexOf(":")).equals("class model.items.GroceryItem")) {
                crossList.add(createGroceryItem(lines,k));

            } else {
                crossList.add(createChore(lines,k));
            }
        }
    }

    //MODIFIES : this, text file
    //EFFECTS : saves the program
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(checkListLocation,"UTF-8");
        checkList = new ArrayList<>(owner.checkList);
        crossList = new ArrayList<>(owner.crossList);
        for (Item item : checkList) {
            writer.println(item.toString());
        }
        writer.close();
        writer = new PrintWriter(crossListLocation,"UTF-8");
        for (Item item : crossList) {
            writer.println(item.toString());
        }
        writer.close();
    }

    //MODIFIES : this, Item item
    public void addItemCheckList(Item item) {
        if (!checkList.contains(item)) {
            checkList.add(item);
            item.addOwnerCheckList(owner);
        }
    }
}
