package info.maigo.lab.hello;
import org.apache.log4j.Logger;
import org.json.*;
import com.mashape.unirest.http.*;

public class App {
  static Logger logger = Logger.getLogger(App.class);
  public static void main(String[] args) throws Exception {
    logger.info(Level.INFO,“Hello World1”);
    logger.log(Level.INFO,“Hello World2”);
    System.out.println("Hello World3");
    for (String name: args) {
      System.out.println("name: " + name);
      String jsonString = new App(name).getUserInfoJSON();
      JSONObject json = new JSONObject(jsonString);
      System.out.println(json.toString(2));
    }
  }

  private final String name;

  public App(String name) {
    this.name = name;
  }

  public String getUserInfoURL() {
    // Users | GitHub Developer Guide: https://developer.github.com/v3/users/
    return "https://api.github.com/users/" + name;
  }

  public String getUserInfoJSON() throws Exception {
    String url = getUserInfoURL();
    HttpResponse<JsonNode> res = Unirest.get(url).asJson();
    return res.getBody().toString();
  }
}

