package DataBase;

import rssSaver.News;

import java.sql.*;
import java.util.ArrayList;

import static constants.Constants.*;

public class DataBase {

    private static DataBase instance;
    private Connection connection;

    public static DataBase getInstance() {

        if (instance == null) instance = new DataBase();
        return instance;
    }

    private DataBase() {

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + DATA_BASE_NAME.get() +"?",
                    MYSQL_USER_NAME.get(), MYSQL_PASSWORD.get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetDataBase(ArrayList<News> newses) {

        try {   //delete all newses
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from " + DIGITAL_TRENDS_TABLE_IN_MYSQL.get());
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("delete from " + DIGITAL_TRENDS_VIEWS_TABLE_IN_MYSQL.get());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        for (News news : newses) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into " + DIGITAL_TRENDS_TABLE_IN_MYSQL.get() + " values (?, ?, ?)");

                preparedStatement.setInt(1, news.getIdentify());
                preparedStatement.setString(2, news.getTitle());
                preparedStatement.setString(3, news.getContent());
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement(
                        "insert into " + DIGITAL_TRENDS_VIEWS_TABLE_IN_MYSQL.get() + " values (?, 0)");
                preparedStatement.setInt(1, news.getIdentify());
                preparedStatement.execute();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public News getNewsByID(int identify) {

        try {
            PreparedStatement newsTable = connection.prepareStatement(
                    "select * from " + DIGITAL_TRENDS_TABLE_IN_MYSQL.get() + " where id = " + identify);
            ResultSet newsTableResult = newsTable.executeQuery();

            PreparedStatement viewsTable = connection.prepareStatement(
                    "select * from " + DIGITAL_TRENDS_VIEWS_TABLE_IN_MYSQL.get() + " where id = " + identify);
            ResultSet viewsResult = viewsTable.executeQuery();

            if (newsTableResult.next() && viewsResult.next()) {

                News news = new News();
                news.setIdentify(newsTableResult.getInt("id"));
                news.setTitle(newsTableResult.getString("title"));
                news.setContent(newsTableResult.getString("content"));
                news.setViews(viewsResult.getInt("view"));
                newsTable.close();
                return news;
            } else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateViewsInDataBase(int identify) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update " + DIGITAL_TRENDS_VIEWS_TABLE_IN_MYSQL.get() + " set view = view + 1 where id = " + identify);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
