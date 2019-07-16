package rssWriter;

import Converters.JsonConverter;
import constants.Constants;


public class WriteNewsesToJson {

    public static void main(String[] args) {

        JsonConverter.writeRSSToJson(Constants.DIGITAL_TRENDS_JSON.get(),
                new WebsiteNewses(RSSReaderFromWebsite.getInstance().getRSS(Constants.DIGITAL_TRENDS_WEBSITE.get())));
    }
}
