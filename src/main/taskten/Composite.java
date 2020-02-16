package taskten;

import java.util.ArrayList;

public class Composite implements Component {
    private ArrayList<Component> components;
    private String name;
    private int subComp;

    public Composite(String name, int subComp) {
        this.name = name;
        this.subComp = subComp;
        components = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubComp(int subComp) {
        this.subComp = subComp;
    }

    public String getName() {
        return name;
    }

    public int getSubComp() {
        return subComp;
    }

    public void addComponent(Component c) {
        components.add(c);
    }

    public void removeComponent(Component c) {
        components.remove(c);
    }

    public Component getChild(int k) {
        return components.get(k);
    }

    //EFFECTS : prints out all the subcomponents that are coupled
    public void print() {
        System.out.println("This is : " + name + " at the subcomp : " + subComp);
        for (Component c : components) {
            c.print();
        }
    }
}
