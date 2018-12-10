import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherParse {

    private static String JSON_WEATHER_PATH = "weather.json";

    public static void main(String[] args) {

        FileReader jsonFile = null;
        try {
            jsonFile = new FileReader(JSON_WEATHER_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        JSONParser parser = new JSONParser();
        Object jsonParsed = null;
        try {
            jsonParsed = parser.parse(jsonFile);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        JSONObject root = (JSONObject) jsonParsed;	
        

        String name = (String) root.get("name");
        System.out.println("* City name: " + name);
        

        JSONObject coord = (JSONObject) root.get("coord");
        System.out.println("* City latitude: " + coord.get("lat"));
        System.out.println("* City longitude: " + coord.get("lon"));

        JSONArray weather = (JSONArray) root.get("weather");
        for (int i = 0; i < weather.size(); i++) {
        	JSONObject main = (JSONObject) weather.get(i);
            System.out.println("* Weather: " + main.get("main"));
        }
    }
}
