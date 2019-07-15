package rssWriter;

import com.google.gson.GsonBuilder;
import constants.Constants;

import java.io.*;

public class WriteRSS {

    public static void main(String[] args) {

        writeRSSToJson(Constants.DIGITAL_TRENDS_JSON.get(),
                new WebsiteRSS(RSSReaderFromWebsite.getInstance().getRSS(Constants.DIGITAL_TRENDS_WEBSITE.get())));
    }

    public static void writeRSSToJson(String jsonAddress, WebsiteRSS websiteRSS) {

        String rss = new GsonBuilder().setPrettyPrinting().create().toJson(websiteRSS);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(jsonAddress));
            bufferedWriter.write(rss);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
