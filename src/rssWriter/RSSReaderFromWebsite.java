package rssWriter;

import constants.Constants;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class RSSReaderFromWebsite {

    private static RSSReaderFromWebsite instance;

    private BufferedReader bufferedReader;

    private final int titleSkipLength = Constants.FIRST_TITLE_TAG.get().length();
    private int idSkipLength = Constants.FIRST_ID_TAG.get().length();
    private int contentSkipLength = Constants.FIRST_CONTENT_TAG.get().length();

    private boolean isReadFinished = false;


    static RSSReaderFromWebsite getInstance() {
        if (instance == null) instance = new RSSReaderFromWebsite();
        return instance;
    }

    private RSSReaderFromWebsite() {
    }

    ArrayList<News> getRSS(String urlAddress) {

        ArrayList<News> newses = null;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new URL(urlAddress).openStream());
            bufferedReader = new BufferedReader(inputStreamReader);

            newses = readRSS();

            inputStreamReader.close();
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return newses;
    }

    private ArrayList<News> readRSS() {

        ArrayList<News> newses = new ArrayList<>();

        while (!isReadFinished) {
            News news = readANews();
            if (news == null) continue;
            newses.add(news);
        }

        return newses;
    }

    private News readANews() {

        String title = getTitle();
        String identify = getID();
        String content = getContent();
        if (title == null || content == null || identify == null) return null;
        return new News(title, identify, content);
    }

    private String getTitle() {

        String rssLine;

        while (true) {

            try {
                if ((rssLine = bufferedReader.readLine()) == null)  {
                    isReadFinished = true;
                    return null;
                }
                if (!rssLine.contains(Constants.FIRST_TITLE_TAG.get())) continue;

                return rssLine.substring(
                        rssLine.indexOf(Constants.FIRST_TITLE_TAG.get()) + titleSkipLength,
                        rssLine.indexOf(Constants.SECOND_TITLE_TAG.get()));

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private String getID() {

        String rssLine;

        while (true) {

            try {
                if ((rssLine = bufferedReader.readLine()) == null) {
                    isReadFinished = true;
                    return null;
                }
                if (!rssLine.contains(Constants.FIRST_ID_TAG.get())) continue;

                return rssLine.substring(
                        rssLine.indexOf(Constants.FIRST_ID_TAG.get()) + idSkipLength,
                        rssLine.indexOf(Constants.SECOND_ID_TAG.get()));

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private String getContent() {

        String rssLine;

        while (true) {

            try {
                if ((rssLine = bufferedReader.readLine()) == null) {
                    isReadFinished = true;
                    return null;
                }
                if (!rssLine.contains(Constants.FIRST_CONTENT_TAG.get())) continue;

                return rssLine.substring(
                        rssLine.indexOf(Constants.FIRST_CONTENT_TAG.get()) + contentSkipLength,
                        rssLine.indexOf(Constants.SECOND_CONTENT_TAG.get()));

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
