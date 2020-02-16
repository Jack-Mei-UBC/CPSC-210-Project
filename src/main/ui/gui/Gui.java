package ui.gui;

import model.items.Activity;
import model.items.Chore;
import model.items.GroceryItem;
import model.items.Item;
import model.network.ReadWebPage;
import taskten.Counter;
import ui.ToDoList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

public class Gui {
    private JTextField textFieldName;
    private JPanel panelMain;
    private JComboBox comboBoxChecklist;
    private JButton buttonComplete;
    private JComboBox comboBoxCrosslist;
    private JButton buttonClearCrossList;
    private JTextField textFieldLocation;
    private JButton loadButton;
    private JTextField textFieldDescription;
    private JComboBox comboBoxType;
    private JComboBox comboBoxDays;
    private JComboBox comboBoxMonth;
    private JTextField textFieldYear;
    private JButton createItemButton;
    private JButton saveButton;
    private JComboBox comboBoxHour;
    private JComboBox comboBoxMinute;
    private JComboBox comboBoxSecond;
    private JButton buttonCheckListData;
    private JButton buttonCrossListData;
    private JButton buttonClearCheckList;
    private ToDoList data;

    //MODIFIES : this
    //EFFECTS : Creates all the listeners for the buttons and the description textfield
    public void instantiateButtons() {
        toggleDescription();
        createItem();
        crossItem();
        showData();
        save();
        clearCheckList();
        clearCrossList();
    }

    //MODIFIES : this
    //EFFECTS : makes it so the textfield for description is only editable when GroceryItem is selected
    public void toggleDescription() {
        comboBoxType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxType.getSelectedItem().equals("Grocery Item")) {
                    textFieldLocation.setEnabled(true);
                } else {
                    textFieldLocation.setEnabled(false);
                }

            }
        });
    }

    //MODIFIES : this, data
    //EFFECTS : removes all the entries from the checklist
    public void clearCheckList() {
        buttonClearCheckList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.checkList.clear();
                comboBoxChecklist.removeAllItems();
            }
        });
    }

    //MODIFIES : this, data
    //EFFECTS : removes all the entries from the crosslist
    public void clearCrossList() {
        buttonClearCrossList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.crossList.clear();
                comboBoxCrosslist.removeAllItems();
            }
        });
    }

    //EFFECTS : creates a popup with the data from the selected crosslist or checklist item
    //          corresponding to the combobox
    public void showData() {
        buttonCheckListData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (data.checkList.size() != 0) {
                    String display = getMessage(data.checkList.get(comboBoxChecklist.getSelectedIndex()));
                    JOptionPane.showMessageDialog(null, display);
                }
            }
        });
        buttonCrossListData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (data.crossList.size() != 0) {
                    String display = getMessage(data.crossList.get(comboBoxCrosslist.getSelectedIndex()));
                    JOptionPane.showMessageDialog(null, display);
                }
            }
        });
    }

    //EFFECTS : returns the data of an item
    public String getMessage(Item item) {
        String out = "Name : " + item.getName() + "\n";
        if (item instanceof GroceryItem) {
            out += "Type : Grocery Item\n";
            out += "Description : " + item.getDescription() + "\n";
            ReadWebPage readWebPage = new ReadWebPage();
            try {
                out += "Weather : " + readWebPage.getWeather((GroceryItem) item);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (item instanceof Activity) {
            out += "Type : Activity\n";
        } else {
            out += "Type : Chore\n";
        }
        out += "Date Due :\n" + item.getDueDate();

        return out;
    }

    //MODIFIES : this, ToDoList data, Item
    //EFFECTS : moves the item from the checklist to crosslist
    public void crossItem() {
        buttonComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (data.checkList.size() == 0) {
                    return;
                }
                int place = comboBoxChecklist.getSelectedIndex();
                data.crossItem(place);
                comboBoxCrosslist.addItem(comboBoxChecklist.getSelectedItem());
                comboBoxChecklist.removeItemAt(place);
            }
        });
    }

    //MODIFIES : this, item, ToDoList data
    //EFFECTS : creates an item with the inputs given in the textfields and comboboxes
    public void createItem() {
        createItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item;
                if (comboBoxType.getSelectedItem().equals("Grocery Item")) {
                    item = new GroceryItem(textFieldName.getText());
                    ((GroceryItem)item).setLocation(textFieldLocation.getText());
                } else if (comboBoxType.getSelectedItem().equals("Activity")) {
                    item = new GroceryItem(textFieldName.getText());
                } else {
                    item = new Chore(textFieldName.getText());
                }
                setDate(item);
                data.addItemCheckList(item);
                comboBoxChecklist.addItem(item.getName());
                data.notify(item.getName());
            }
        });
    }

    //MODIFIES : Item item
    //EFFECTS : adds the date values in the item class handed in
    public void setDate(Item item) {
        int year = Integer.parseInt(textFieldYear.getText()) + 1;
        int month = comboBoxMonth.getSelectedIndex();
        int day = Integer.parseInt(comboBoxDays.getSelectedItem().toString());

        int hour = Integer.parseInt(comboBoxHour.getSelectedItem().toString());
        int minute = Integer.parseInt(comboBoxMinute.getSelectedItem().toString());
        int second = Integer.parseInt(comboBoxSecond.getSelectedItem().toString());
        item.setDueDate(month,day,year,hour,minute,second);
    }

    //MODIFIES : ToDoList data, SaveLoad
    //EFFECTS : saves the checklist and crosslist in a text file
    public void save() {
        saveButton.addActionListener(e -> {
            try {
                data.loadSave.save();
                data.counters.get(0).end();
            } catch (Exception ex) {
                System.out.println("Failed save!");
            }
        });
    }

    //MODIFIES : this
    //EFFECTS : inputs data into the comboboxes
    public void instantiateLists() {
        for (Item item : data.checkList) {
            comboBoxChecklist.addItem(item.getName());
        }
        for (Item item : data.crossList) {
            comboBoxCrosslist.addItem(item.getName());
        }
        for (int k = 1;k <= 31;k++) {
            comboBoxDays.addItem(k);
        }
        for (int k = 1;k <= 24;k++) {
            comboBoxHour.addItem(k);
        }
        for (int k = 0;k < 60;k++) {
            comboBoxSecond.addItem(k);
            comboBoxMinute.addItem(k);
        }

    }

    public Gui(ToDoList data) {
        this.data = data;
        instantiateButtons();
        instantiateLists();

    }

    //EFFECTS : runs the program
    public static void main(String[] args) throws IOException {


        ToDoList main = new ToDoList();
        main.initialize();
        main.addObserver(new Counter());

        JFrame frame = new JFrame("Checklist and Crosslist");
        frame.setContentPane(new Gui(main).panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
