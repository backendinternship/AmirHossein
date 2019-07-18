package rssSaver;

import DataBase.DataBase;
import constants.Constants;

import java.sql.SQLException;


public class WriteNewsInDataBase {

    public static void main(String[] args) {

        try {
            DataBase.connectToDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        DataBase.getInstance().resetDataBase(
                RSSReaderFromWebsite.getInstance().getRSS(Constants.DIGITAL_TRENDS_WEBSITE.get()));
    }
}
