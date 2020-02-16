import model.items.GroceryItem;
import model.network.ReadWebPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReadWebPage {
    @Test
    public void testPrintWeather() throws MalformedURLException {
        GroceryItem item = new GroceryItem("item");
        item.setLocation("Vancouver");
        ReadWebPage readWebPage = new ReadWebPage();
        System.out.println(readWebPage.getWeather(item));
    }

    @Test
    public void testPrintWeatherBreak() throws MalformedURLException {
        GroceryItem item = new GroceryItem("item");
        item.setLocation("Vanasd");
        ReadWebPage readWebPage = new ReadWebPage();
        assertEquals("",readWebPage.getWeather(item));
    }

    @Test
    public void testPrintURL() throws IOException {
        ReadWebPage readWebPage = new ReadWebPage();
        readWebPage.read("https://www.students.cs.ubc.ca/~cs-210/2018w1/welcomemsg.html");
    }
}
