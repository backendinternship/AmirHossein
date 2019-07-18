package DataBase;

import static org.junit.Assert.*;

import org.junit.*;
import rssSaver.News;
import rssSaver.RSSReaderFromWebsite;

import java.sql.*;
import java.util.ArrayList;

import static constants.Constants.*;

public class DataBaseTest {

    private ArrayList<News> newses;
    private Connection connection;

    private String urlAddress = DIGITAL_TRENDS_WEBSITE.get();
    private String tableInMySQL = DIGITAL_TRENDS_TABLE_IN_DATA_BASE.get();
    private String viewTableInMySQL = DIGITAL_TRENDS_VIEWS_TABLE_IN_DATA_BASE.get();

    @Before
    public void getNewsesFromWebsite() throws SQLException {

        try {
            DataBase.connectToDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        newses = RSSReaderFromWebsite.getInstance().getRSS(urlAddress);
        DataBase.getInstance().resetDataBase(newses);
        connection = DriverManager.getConnection(
                DATA_BASE_URL.get(), DATA_BASE_USER_NAME.get(), DATA_BASE_PASSWORD.get());
    }

    @Test
    public void testDataStoreCorrectness() throws SQLException {

        for (News news : newses) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from " + tableInMySQL + " where id = " + news.getIdentify());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                assertTrue(news.equals(resultSet.getString("title"), resultSet.getString("content")));
            preparedStatement.close();
        }
    }

    @Test
    public void testSimilarityOfViewsTableAndMainTableSize() throws SQLException {

        ResultSet resultSet = connection.prepareStatement("select * from " + viewTableInMySQL).executeQuery();
        resultSet.last();
        assertEquals(newses.size(), resultSet.getRow());
    }
}
