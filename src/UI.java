import DataBase.DataBase;
import rssSaver.News;

import java.sql.SQLException;
import java.util.Scanner;

public class UI {

    public static void main(String[] args) throws SQLException {

        DataBase.connectToDataBase();

        while(true) {
            int identify = new Scanner(System.in).nextInt();
            if (identify == -1 /* finish program */) break;
            News news = DataBase.getInstance().getNewsByID(identify);

            if (news == null)
                System.err.println("This news isn't exist");
            else {
                DataBase.getInstance().updateViewsInDataBase(identify);
                System.out.println("Identify:‌ " + news.getIdentify());
                System.out.println("Title:‌ " + news.getTitle());
                System.out.println("Content: ‌" + news.getContent());
                System.err.println("Views:‌ " + (news.getViews() + 1 /* 1 for present view */));
            }
        }
    }
}
