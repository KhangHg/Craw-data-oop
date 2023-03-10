package test;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class Href {
    public static void main(String[] args) throws IOException {
        // The URL of the page you want to crawl
        String url = "https://nguoikesu.com";

        // Connect to the website and get the HTML
        Document doc = Jsoup.connect(url).get();

        // Find all the a elements
        Elements elementsLevel0 = doc
                .select("#jm-left > div > div:nth-child(1) > div > div.jm-module-content.clearfix > ul > li > a");
        Elements elementsLevel1 = doc.select(
                "#jm-left > div > div:nth-child(1) > div > div.jm-module-content.clearfix > ul > li > ul > li > a");
        elementsLevel0.addAll(elementsLevel1);
        Elements elements = elementsLevel0;

        // Create a JSON array to store the href values
        JSONArray hrefArray = new JSONArray();

        // Loop through each a element and get the href value
        for (Element element : elements) {
            String href = url + element.attr("href");
            JSONObject hrefObject = new JSONObject();
            hrefObject.put("href", href);
            hrefArray.put(hrefObject);
        }

        // Write the array to a JSON file
        try (FileWriter file = new FileWriter("hrefs.json")) {
            file.write(hrefArray.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
