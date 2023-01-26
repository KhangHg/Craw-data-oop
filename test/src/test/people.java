package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;

public class people {
    public static void main(String[] args) throws IOException {
        // URL to crawl
        String url = "https://nguoikesu.com/nhan-vat?start=1";
        
        // Connect to the website and get the HTML
        Document doc = Jsoup.connect(url).get();
        
        // Select the data using CSS selectors
        Elements elements = doc.select(".item-content");
        
     // Create a JSON array to store the href values
        JSONArray hrefArray = new JSONArray();

        // Loop through each a element and get the href value
        for (Element element : elements) {
            String href = element.attr("href");
            JSONObject hrefObject = new JSONObject();
            hrefObject.put("data", href);
            hrefArray.put(hrefObject);
        }

        // Write the array to a JSON file
        try (FileWriter file = new FileWriter("people.json")) {
            file.write(hrefArray.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
