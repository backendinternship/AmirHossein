package constants;

public enum Constants {

    DIGITAL_TRENDS_WEBSITE("https://www.digitaltrends.com/feed/"),
    DIGITAL_TRENDS_JSON("rss/digitaltrends.json");


    private String address;

    public String get() {
        return address;
    }

    Constants(String address) {
        this.address = address;
    }
}
