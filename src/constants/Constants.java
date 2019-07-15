package constants;

public enum Constants {

    FIRST_TITLE_TAG("<title>"),
    SECOND_TITLE_TAG("</title>"),
    FIRST_ID_TAG("<guid isPermaLink=\"false\">https://www.digitaltrends.com/?p="),
    SECOND_ID_TAG("</guid>"),
    FIRST_CONTENT_TAG("<description><![CDATA["),
    SECOND_CONTENT_TAG("]]></description>"),

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
