import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapFactory {
    public Map getMap(String neededMap){
        if (neededMap==null){
            return null;
        }
        else if (neededMap.equals("HASHMAP")){
            return new HashMap();
        }
        else if (neededMap.equals("TREEMAP")){
            return new TreeMap();
        }
        else if(neededMap.equals("LINKEDHASHMAP")){
            return new LinkedHashMap();
        }
        return null;
    }
}
