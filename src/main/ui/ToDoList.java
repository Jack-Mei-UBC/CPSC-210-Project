package ui;

import taskten.Composite;
import taskten.Counter;
import model.exceptions.InvalidDateException;
import model.exceptions.InvalidSelectionException;
import model.items.*;

import java.io.*;
import java.util.*;

public class ToDoList {
    public ArrayList<Item> checkList;
    public ArrayList<Item> crossList;
    public ArrayList<Counter> counters;
    private Scanner scanner;
    private Map<Item, Item> multiTask;
    public LoadSave loadSave;

    //MODIFIES : this
    //EFFECTS : creates the private lists and scanner we need

    public void initialize() {
        multiTask = new HashMap<Item,Item>();
        scanner = new Scanner(System.in);
        loadSave = new LoadSave(this);
        try {
            loadSave.load();
            loadSave.load1();
        } catch (IOException e) {
            System.out.println("failed load");
        }
        checkList = new ArrayList<>(loadSave.checkList);
        crossList = new ArrayList<>(loadSave.crossList);
        counters = new ArrayList<>();
    }


    //MODIFIES : this
    //EFFECT : adds new item to checkList

    public void addItemCheckList(Item item) {
        if (!checkList.contains(item)) {
            checkList.add(item);
            item.addOwnerCheckList(this);
        }
    }



    //REQUIRES : checklist has item
    //MODIFIES : this
    //EFFECTS : moves item specified from checklist to crossList
    public void crossItem(int place) {
        System.out.println("List the number of the item to remove it");
        for (int k = 0; k < checkList.size();k++) {
            System.out.println("" + k + checkList.get(k).getName());
        }
        if (place == -1) {
            place = parseInput(scanner.nextLine());
        }
        crossList.add(checkList.get(place));
        checkList.remove(place);
    }

    //EFFECTS : prints out checklist
    public void printList() {
        System.out.println("Not yet done are :");
        for (int k = 0; k < checkList.size();k++) {
            if (multiTask.containsKey(checkList.get(k))) {
                printListItem(checkList,k);
                System.out.println("connected task -> " + multiTask.get(checkList.get(k)).toString());
            } else {
                printListItem(checkList,k);
            }
        }
    }


    //INSTANCE OF REFACTORING -- FIX ONE
    public void printListItem(List<Item> list, int index) {
        System.out.println("" + index + " " + list.get(index).getName() + " " + list.get(index).getDueDate());
    }


    //REQUIRES : input is an integer
    //EFFECTS : returns the input as an integer
    public int parseInput(String input) {
        return (Integer.parseInt(input));
    }

    public void addObserver(Counter counter) {
        counters.add(counter);
    }

    public void removeObserver(Counter counter) {
        counters.remove(counter);
    }

    //EFFECTS : Makes the counter print out the String input
    public void notify(String input) {
        for (Counter c : counters) {
            c.update(input);
        }
    }


}
