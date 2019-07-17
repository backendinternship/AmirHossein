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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
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

    public boolean equals(String title, String content) {

        return this.title.length() == title.length() && this.content.length() == content.length();
        /*
            I assumed if (each news title length and content length in addition to equality of identifies) are same,
            both of newses are equal
         */
    }
}
