package model.network;

import model.items.BaseItem;
import model.items.GroceryItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadWebPage {
    BufferedReader br = null;

    //For Task 1
    //EFFECTS : prints out the data from the url given
    public void read(String theURL) throws IOException {
        URL url = new URL(theURL);
        br = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;

        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {

            sb.append(line);
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
        if (br != null) {
            br.close();
        }
    }

    //For Task 2
    //EFFECTS : returns a String with the weather of the GroceryItem entered
    public String getWeather(GroceryItem item) throws MalformedURLException {
        try {
            String location = item.getLocation();
            String apikey = "48fd57b63ffc4d1fab156ebf406fd98d";
            String londonweatherquery = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=";
            String theURL = londonweatherquery + apikey;
            String data = parseXML(theURL);

            String temp = data.substring(data.indexOf("temp") + 6, data.indexOf("pressure") - 1);
            String weather = data.substring(data.indexOf("description") + 14, data.indexOf("icon") - 3);
            return (temp + weather);
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    //REQUIRES : valid url
    //EFFECTS : converts the XML from the URL into a string
    public String parseXML(String theURL) {
        BufferedReader br = null;
        try {
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            String out = "";
            while ((line = br.readLine()) != null) {
                out += line + "\n";
            }
            return (out);
        } catch (Exception e) {
            return "";
        }
    }
}
