package constants;

public enum Constants {

    DATA_BASE_URL("jdbc:h2:~/test"),
    DATA_BASE_USER_NAME("sa"),
    DATA_BASE_PASSWORD(""),

    DIGITAL_TRENDS_WEBSITE("https://www.digitaltrends.com/feed/"),
    DIGITAL_TRENDS_TABLE_IN_DATA_BASE("digitalTrends"),
    DIGITAL_TRENDS_VIEWS_TABLE_IN_DATA_BASE("digitalTrendsViews");

    private String address;

    public String get() {
        return address;
    }

    Constants(String address) {
        this.address = address;
    }
}
