package constants;

public enum Constants {

    DATA_BASE_NAME("rss"),
    MYSQL_USER_NAME("amirhossein"),
    MYSQL_PASSWORD("ahak"),

    DIGITAL_TRENDS_WEBSITE("https://www.digitaltrends.com/feed/"),
    DIGITAL_TRENDS_TABLE_IN_MYSQL("rss.digitalTrend"),
    DIGITAL_TRENDS_VIEWS_TABLE_IN_MYSQL("rss.digitalTrendViews");

    private String address;

    public String get() {
        return address;
    }

    Constants(String address) {
        this.address = address;
    }
}
