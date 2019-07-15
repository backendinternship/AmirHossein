package rssWriter;

import java.util.ArrayList;

public class WebsiteRSS {

    private ArrayList<News> newses;

    public ArrayList<News> getNewses() {
        return newses;
    }

    public WebsiteRSS(ArrayList<News> newses) {
        this.newses = newses;
    }
}
