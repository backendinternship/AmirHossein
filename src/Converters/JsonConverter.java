package Converters;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import rssSaver.WebsiteNewses;

import java.io.*;

public class JsonConverter {

    public static void writeToJson(String jsonAddress, Object object) {

        String rss = new GsonBuilder().setPrettyPrinting().create().toJson(object);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(jsonAddress));
            bufferedWriter.write(rss);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebsiteNewses readRSSFromJson(String jsonAddress) {

        try {
            JsonReader jsonReader = new JsonReader(new FileReader(jsonAddress));
            return new GsonBuilder().create().fromJson(jsonReader, WebsiteNewses.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}