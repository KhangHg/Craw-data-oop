package test;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        // The URL of the page you want to crawl
        String url = "https://nguoikesu.com/";

        // Connect to the website and get the HTML
        Document doc = Jsoup.connect(url).get();

        // Find all the elements with the class "data"
        Elements elements = doc.select("#jm-left > div > div:nth-child(1) > div > div.jm-module-content.clearfix > ul > li > a"); 

        // Create a JSON array to store the data
        JSONArray dataArray = new JSONArray();

        // Add each element to the array as a JSON object
        for (Element element : elements) {
            JSONObject dataObject = new JSONObject();
            dataObject.put("data", element.text());
            dataArray.put(dataObject);
        }

        // Write the array to a JSON file
        try (FileWriter file = new FileWriter("data.json")) {
            file.write(dataArray.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}