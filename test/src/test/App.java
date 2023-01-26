package test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // The name of the file to read
        String filename = "D:\\HUST\\Nam3\\OOP\\Project\\test\\src\\test\\hrefs.json";

        // Read the file into a JSONArray object
        JSONArray array;
        try (FileReader reader = new FileReader(filename)) {
            JSONTokener tokener = new JSONTokener(reader);
            array = new JSONArray(tokener);
        }
        String[] urls = new String[array.length()];

        // Loop through the array and print out the values
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            urls[i] = object.getString("href").toString();
            // System.out.println( urls[i]);
        }

        // Create a JSON array to store the data
        JSONArray dataArray = new JSONArray();

        // Loop through each URL
        for (String url : urls) {
            // Connect to the website and get the HTML
            Document doc = Jsoup.connect(url).get();

            // Find all the elements with the class "data"
            Elements elements1 = doc.getElementsByClass("subheading-category");
            Elements elements2 = doc.getElementsByClass("category-desc clearfix");

            JSONObject dataObject = new JSONObject();
            // Add each element to the array as a JSON object
            for (Element element1 : elements1) {
                dataObject.put("name", element1.text());
            }

            for (Element element2 : elements2) {
                dataObject.put("data", element2.text());
                dataArray.put(dataObject);
            }

        }

        // Write the array to a JSON file
        try (FileWriter file = new FileWriter("trieu-dai-lich-su.json")) {
            file.write(dataArray.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
