package DataBase;

import Converters.JsonConverter;
import constants.Constants;
import rssSaver.News;
import rssSaver.RSSReaderFromWebsite;
import rssSaver.WebsiteNewses;

import java.util.ArrayList;
import java.util.Objects;

public class MockitoMaker {

    static ArrayList<News> getMockitoNewses() throws Exception {

        ArrayList<News> newses = Objects.requireNonNull(
                JsonConverter.readRSSFromJson(Constants.RRS_MOCKITO_JSON_ADDRESS.get()),
                "JsonConverter couldn't convert json to object").getNewses();
        return newses;
    }

    public static void main(String[] args) {
        //make once mockito for ever

        ArrayList<News> newses = RSSReaderFromWebsite.getInstance().getRSS(Constants.DIGITAL_TRENDS_WEBSITE.get());
        JsonConverter.writeToJson(Constants.RRS_MOCKITO_JSON_ADDRESS.get(), new WebsiteNewses(newses));
    }
}
