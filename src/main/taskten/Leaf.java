package taskten;

public class Leaf implements Component {
    private String name;
    private int subComp;

    public Leaf(String name, int subComp) {
        this.name = name;
        this.subComp = subComp;
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

    //EFFECTS : prints the leaf name, and how far of a subComponent it is.
    public void print() {
        System.out.println("This is : " + name + " at the subcomp : " + subComp);
    }
}
