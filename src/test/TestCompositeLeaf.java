import org.junit.jupiter.api.Test;
import taskten.Composite;
import taskten.Leaf;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCompositeLeaf {
    @Test
    public void testInitComposite() {
        Composite c = new Composite("test", 1);
        assertEquals("test",c.getName());
        assertEquals(1, c.getSubComp());

        c.setName("1");
        c.setSubComp(2);
        assertEquals("1",c.getName());
        assertEquals(2,c.getSubComp());
    }

    @Test
    public void testInitLeaf() {
        Leaf c = new Leaf("test", 1);
        assertEquals("test",c.getName());
        assertEquals(1, c.getSubComp());

        c.setName("1");
        c.setSubComp(2);
        assertEquals("1",c.getName());
        assertEquals(2,c.getSubComp());
    }

    @Test
    public void testAddComponent() {
        Composite c = new Composite("test", 1);
        c.addComponent(new Leaf("1",1));
        Leaf d = new Leaf("1",2);
        c.addComponent(d);
        c.print();
        assertEquals(d,c.getChild(1));
        c.removeComponent(d);
        c.print();
    }
}
