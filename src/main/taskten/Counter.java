package taskten;

//SINGLETON AND OBSERVER
public class Counter {
    private static int items;

    //MODIFIES : this
    //EFFECTS : prints out the name of the item added
    public static void update(String s) {
        items++;
        System.out.println("ADDED 1 ITEM : " + s);
    }

    //MODIFIES : this
    //EFFECTS : prints out the total number of items added, and then clears the counter
    public static void end() {
        System.out.println("TOTAL ITEMS ADDED : " + items);
        items = 0;
    }
}
