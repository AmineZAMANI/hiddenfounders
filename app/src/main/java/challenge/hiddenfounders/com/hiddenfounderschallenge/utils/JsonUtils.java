package challenge.hiddenfounders.com.hiddenfounderschallenge.utils;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by azamani on 27/12/2017.
 */

public class JsonUtils {

    private static final Gson gson = new Gson();

    public static Map<String, String> parse(String data) {
        return gson.fromJson(data, Map.class);
    }


}
