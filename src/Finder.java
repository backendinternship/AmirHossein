import constants.Constants;
import rssWriter.News;
import rssWriter.WebsiteRSS;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import rssWriter.WriteRSS;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Finder {

    private static ArrayList<News> newses;

    public static void main(String[] args) {

        newses = Objects.requireNonNull(readRSSFromJson(Constants.DIGITAL_TRENDS_JSON.get())).getNewses();
        if (newses == null) System.out.println("Reading RSS from json didn't successfully");

        while(true) {
            String identify = new Scanner(System.in).nextLine().trim();
            if (identify.equals("finish")) break;
            News news = findNews(identify);

            if (news == null) System.out.println("This news isn't exist");
            else {
                news.incrementView();
                System.out.println("Identify:‌ " + news.getIdentify());
                System.out.println("Title:‌ " + news.getTitle());
                System.out.println("Content: ‌" + news.getContent());
                System.err.println("Views:‌ " + news.getViews());
            }
        }

        writeLastNewsesView();
    }

    private static WebsiteRSS readRSSFromJson(String jsonAddress) {

        try {
            JsonReader jsonReader = new JsonReader(new FileReader(jsonAddress));
            return new GsonBuilder().create().fromJson(jsonReader, WebsiteRSS.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static News findNews(String identify) {

        for (News news : newses)
            if (news.getIdentify().equals(identify)) return news;

        return null;
    }

    private static void writeLastNewsesView() {
        WriteRSS.writeRSSToJson(Constants.DIGITAL_TRENDS_JSON.get(), new WebsiteRSS(newses));
    }
}
