package rssSaver;

import constants.Constants;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;

public class RSSReaderFromWebsiteTest {

    @Test
    public void testReadRSS() {

        ArrayList<News> newses = RSSReaderFromWebsite.getInstance().getRSS(Constants.DIGITAL_TRENDS_WEBSITE.get());
        boolean[] titleExistence = new boolean[newses.size()];
        boolean[] contentExistence = new boolean[newses.size()];

        for (int i = 0; i < newses.size(); i++) {
            titleExistence[i] = newses.get(i).getTitle() != null;
            contentExistence[i] = newses.get(i).getContent() != null;
        }

        for (int i = 0; i < newses.size(); i++) {
            assertTrue(titleExistence[i]);
            assertTrue(contentExistence[i]);
        }
    }
}
