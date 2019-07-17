package rssSaver;

import DataBase.DataBase;
import constants.Constants;


public class WriteNewsInDataBase {

    public static void main(String[] args) {

        DataBase.getInstance().resetDataBase(
                RSSReaderFromWebsite.getInstance().getRSS(Constants.DIGITAL_TRENDS_WEBSITE.get()));
    }
}
