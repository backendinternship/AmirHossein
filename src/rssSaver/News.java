package rssSaver;

public class News {

    private String identify, title, content;
    private int views = 0;

    public String getIdentify() {
        return identify;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void incrementView() {
        views++;
    }

    public int getViews() {
        return views;
    }

    public News(String title, String identify, String content) {

        this.identify = identify;
        this.title = title;
        this.content = content;
    }
}
