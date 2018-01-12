package challenge.hiddenfounders.com.hiddenfounderschallenge.utils;

import com.google.gson.Gson;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by azamani on 27/12/2017.
 */

public class JsonUtils {

    private static final Gson gson = new Gson();

    public static Map<String, Object> parse(String data) {
        return gson.fromJson(data, Map.class);
    }

    public static Object findByPath(Map _map, String path) {

        boolean hasPathList = false;
        if (path.contains("[")) {
            hasPathList = true;
        }
        Map map = _map;
        String[] split = path.split("\\.");
        if (split.length == 1) {
            return hasPathList && path.contains("[") ? traverseList(map, path) : map.get(path);
        } else {
            String part = null;
            for (int i = 0; i < split.length - 1; i++) {
                part = split[i];
                if (hasPathList && part.contains("[")) {
                    map = traverseList(map, part);
                } else if (map != null && map.get(part) != null) {
                    if (map.get(part) instanceof Map) {
                        Map bean = (Map) map.get(part);
                        map = bean;
                    } else {
                        //throw new Exception("key " + part + " from Path: " + path + " must be a Map ...");
                    }
                } else {
                    return null;
                }
            }
            if (hasPathList && split[split.length - 1].contains("[")) {
                return traverseList(map, split[split.length - 1]);
            } else if (map != null) {
                return map.get(split[split.length - 1]);
            } else {
                return null;
            }
        }
    }

    private static Map traverseList(Map map, String part) {
        Map bean = null;
        String path = part.replaceAll("\\[.*\\]", "");
        if (map != null && findByPath(map, path) instanceof Collection<?>) {
            List<Map> listBeans = (List<Map>) JsonUtils.findByPath(map, path);
            String lstIdx = part.substring(part.indexOf("[") + 1, part.indexOf("]"));
            Integer indexElement = new Integer(lstIdx);
            bean = listBeans.get(indexElement);
        }
        return bean;
    }


}
