import Converters.JsonConverter;
import constants.Constants;
import rssSaver.News;
import rssSaver.WebsiteNewses;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UI {

    private static ArrayList<News> newses;

    public static void main(String[] args) {

        newses = Objects.requireNonNull(JsonConverter.readRSSFromJson(Constants.DIGITAL_TRENDS_JSON.get())).getNewses();
        if (newses == null) {
            System.err.println("Reading RSS from json didn't successfully");
            return;
        }

        while(true) {
            int identify = new Scanner(System.in).nextInt();
            if (identify == -1 /* finish */) break;
            News news = findNews(identify);

            if (news == null)
                System.err.println("This news isn't exist");
            else {
                news.incrementView();
                System.out.println("Identify:‌ " + news.getIdentify());
                System.out.println("Title:‌ " + news.getTitle());
                System.out.println("Content: ‌" + news.getContent());
                System.err.println("Views:‌ " + news.getViews());
            }
        }

        saveLastNewsesView();
    }

    private static News findNews(int identify) {

        for (News news : newses)
            if (news.getIdentify() == identify) return news;

        return null;
    }

    private static void saveLastNewsesView() {
        JsonConverter.writeRSSToJson(Constants.DIGITAL_TRENDS_JSON.get(), new WebsiteNewses(newses));
    }
}
