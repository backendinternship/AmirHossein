package rssSaver;

import constants.Constants;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class RSSReaderFromWebsite {

    private static RSSReaderFromWebsite instance;
    private BufferedReader bufferedReader;


    static RSSReaderFromWebsite getInstance() {
        if (instance == null) instance = new RSSReaderFromWebsite();
        return instance;
    }

    private RSSReaderFromWebsite() {
    }

    ArrayList<News> getRSS(String urlAddress) {

        ArrayList<News> newses = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new URL(urlAddress).openStream());
            bufferedReader = new BufferedReader(inputStreamReader);

            readRSS(newses);

            inputStreamReader.close();
            bufferedReader.close();

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }

        return newses;
    }

    private void readRSS (ArrayList<News> newses) throws XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(bufferedReader);
        News news = null;
        int identify = 0;

        while (xmlStreamReader.hasNext()) {
            xmlStreamReader.next();

            if (xmlStreamReader.getEventType() == xmlStreamReader.START_ELEMENT) {

                if (xmlStreamReader.getLocalName().equalsIgnoreCase("item"))
                    news = new News();

                else if (news != null && xmlStreamReader.getLocalName().equalsIgnoreCase("title"))
                    news.setTitle(xmlStreamReader.getElementText());

                else if (news != null && xmlStreamReader.getLocalName().equalsIgnoreCase("description"))
                    news.setContent(xmlStreamReader.getElementText());

            } else if (xmlStreamReader.getEventType() == xmlStreamReader.END_ELEMENT) {

                if (news != null && xmlStreamReader.getLocalName().equalsIgnoreCase("item")) {
                    news.setIdentify(++identify);
                    newses.add(news);
                }
            }
        }
    }
}
