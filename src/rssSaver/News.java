package rssSaver;

public class News {

    private int identify;
    private String title, content;
    private int views = 0;

    public int getIdentify() {
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

    public void setIdentify(int identify) {
        this.identify = identify;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
