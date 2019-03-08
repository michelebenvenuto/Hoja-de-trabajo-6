import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        MapFactory mapFactory = new MapFactory();
        Map mapToUse=null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido Escoja el numero del tipo de mapa que desea utilizar:");
        System.out.println("1)HashMap");
        System.out.println("2)TreeMap");
        System.out.println("3)LinkedHashMap");
        String mapInput= sc.nextLine();
        if (mapInput.equals("1")){
            mapToUse=mapFactory.getMap("HASHMAP");
        }
        else if(mapInput.equals("2")){
            mapToUse=mapFactory.getMap("TREEMAP");
        }
        else if(mapInput.equals("3")){
            mapToUse=mapFactory.getMap("LINKEDHASHMAP");
        }
        else{
            System.out.println("Esta accion no existe en el menu");
        }
        try{
            Stream<String> lines= Files.lines(
                    Paths.get("cards_desc.txt"),
                    StandardCharsets.UTF_8
            );
            Map finalMapToUse = mapToUse;
            lines.forEach(s->{
                int separation = s.indexOf("|");
                String key = s.substring(0,separation);
                String value = s.substring(separation+1);
                finalMapToUse.put(key,value);

            });
        }catch (IOException exception){
            System.out.println("ERROR");
        }

    }
}
