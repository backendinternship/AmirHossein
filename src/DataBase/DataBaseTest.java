package DataBase;

import org.junit.*;
import org.mockito.Matchers;
import org.mockito.Mockito;
import rssSaver.News;
import rssSaver.RSSReaderFromWebsite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static constants.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataBaseTest {

    private ArrayList<News> newses;
    private Connection connection;

    private String urlAddress = DIGITAL_TRENDS_WEBSITE.get();
    private String tableInMySQL = DIGITAL_TRENDS_TABLE_IN_DATA_BASE.get();
    private String viewTableInMySQL = DIGITAL_TRENDS_VIEWS_TABLE_IN_DATA_BASE.get();


    @Before
    public void getNewsesFromWebsite() throws Exception {

        RSSReaderFromWebsite rssReaderFromWebsite = Mockito.mock(RSSReaderFromWebsite.class);
        Mockito.when(rssReaderFromWebsite.getRSS(Matchers.any(String.class))).thenReturn(MockitoMaker.getMockitoNewses());

        DataBase.connectToDataBase();
        newses = RSSReaderFromWebsite.getInstance().getRSS(urlAddress);
        DataBase.getInstance().resetDataBase(newses);
        connection = DataBase.getInstance().getConnection();
    }

    @Test
    public void testDataStoreCorrectness() throws SQLException {

        for (News news : newses) {

            ResultSet resultSet = connection.prepareStatement(
                    "select * from " + tableInMySQL + " where id = " + news.getIdentify()).executeQuery();

            while (resultSet.next())
                assertTrue(news.equals(resultSet.getString("title"), resultSet.getString("content")));
        }
    }

    @Test
    public void testSimilarityOfViewsTableAndMainTableSize() throws SQLException {

        ResultSet resultSet = connection.prepareStatement("select * from " + viewTableInMySQL).executeQuery();
        resultSet.last();
        assertEquals(newses.size(), resultSet.getRow());
    }
}
