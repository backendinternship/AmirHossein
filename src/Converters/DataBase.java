package Converters;

import rssSaver.News;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    private static DataBase instance;
    private Connection connection;

    public static DataBase getInstance() {

        if (instance == null) instance = new DataBase();
        return instance;
    }

    private DataBase() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/rss?", "amirhossein", "ahak");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetDataBase(ArrayList<News> newses) {

        try {   //delete all newses
            connection.prepareStatement("delete from rss.digitalTrend");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        for (News news : newses) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into rss.digitalTrend values (?, ?, ?, 0)");

                preparedStatement.setInt(1, news.getIdentify());
                preparedStatement.setString(2, news.getTitle());
                preparedStatement.setString(3, news.getContent());
                preparedStatement.execute();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public News getNewsByID(int identify) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from rss.digitalTrend where id = " + identify);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {

                News news = new News();
                news.setIdentify(resultSet.getInt("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setViews(resultSet.getInt("view"));
                preparedStatement.close();
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
                    "update rss.digitalTrend set view = view + 1 where id = " + identify);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
