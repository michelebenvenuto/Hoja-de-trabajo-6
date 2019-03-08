import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
/*Silvio Orozco 18282
 * Michele Benvenuto
 * Hoja de Trabajo 6
 * Algoritmos y Estructura de datos
 * 08 de marzo del 2019*/
public class Main {
    public static void main(String[] args){
        MapFactory mapFactory = new MapFactory();
        Map<String,String> mapToUse=null;
        Map<String,String> userMap=null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido Escoja el numero del tipo de mapa que desea utilizar:");
        String mapInput = "";
        // Se da la bienvenida al usuario y se utiliza mapFactory para obtener un objeto de tipo map segun lo desee el usuario para crear su mapa y el mapa de todas las cartas.
        while(mapToUse==null) {
	        if (mapInput.equals("1")){
	            mapToUse=mapFactory.getMap("HASHMAP");
	            userMap=mapFactory.getMap("HASHMAP");
	        }
	        else if(mapInput.equals("2")){
	            mapToUse=mapFactory.getMap("TREEMAP");
	            userMap=mapFactory.getMap("TREEMAP");
	        }
	        else if(mapInput.equals("3")){
	            mapToUse=mapFactory.getMap("LINKEDHASHMAP");
	            userMap=mapFactory.getMap("LINKEDHASHMAP");
	        }
	        else{
	            System.out.println("Esta accion no existe en el menu");
	            System.out.println("Ingrese una opcion de mapa a utilizar: ");
	            System.out.println("1)HashMap");
	            System.out.println("2)TreeMap");
	            System.out.println("3)LinkedHashMap");
	             mapInput= sc.nextLine();
	        }
        }
        //Se establece el finalMapToUse que utilizara en el programa para todas las cartas.
        Map<String,String> finalMapToUse= mapToUse;
        //Se lee el archivo de todas las cartas y con el metodo put(key, value) se ingresan las cartas al mapa
        // Key sera el nombre de la carta y value sera el tipo.
        try{
            Stream<String> lines= Files.lines(
                    Paths.get("cards_desc.txt"),
                    StandardCharsets.UTF_8
            );
            
            lines.forEach(s->{
                int separation = s.indexOf("|");
                String key = s.substring(0,separation);
                String value = s.substring(separation+1);
                finalMapToUse.put(key,value);
                

            });
        }catch (IOException exception){
            System.out.println("ERROR");
        }
        String option;
        //Se muestran las opciones al usuario y se lee la opcion de lo que desea realizar.
        do {	
	        System.out.println("Ingrese una opción para utilizar con las cartas: ");
	        System.out.println("1)Agregar una carta a la colección a mi coleccion de usuario.");
	        System.out.println("2)Mostrar el tipo de una carta específica.");
	        System.out.println("3)Mostrar el nombre, tipo y cantidad de cada carta de mi coleccion de usuario.");
	        System.out.println("4)Mostrar el nombre, tipo y cantidad de cada carta que el usuario tiene en su colección, ordenadas por tipo.");
	        System.out.println("5)Mostrar el nombre y tipo de todas las cartas existentes.");
	        System.out.println("6)Mostrar el nombre y tipo de todas las cartas existentes, ordenadas por tipo.");
	        System.out.println("7)Salir.");
	        option = sc.nextLine() ;
	        String card;
	        switch (option) {
	        //Se agrega la carta a la coleccion de usuario si existe.
            case ("1"):  
            	System.out.println("Ingrese el nombre de una carta para ser agregada a la coleccion: ");
	            card= sc.nextLine();
	            if(finalMapToUse.containsKey(card)) {
	            	
	            	if(userMap.containsKey(card)) {
	            		System.out.println("La carta ya esta en su coleccion.");
	            	}else {
	            		System.out.println("La carta ha sido agregada a su coleccion.");
	            		userMap.put(card, finalMapToUse.get(card));
	            	}
	            }else {
	            	System.out.println("Error, la carta ingresada no existe.");
	            }
	    		break;
	    	//Busca en el mapa la carta un key y luego muestra su value (tipo)
            case ("2"):  
        		System.out.println("Ingrese el nombre para mostrar su tipo: ");
                card= sc.nextLine();
                if(finalMapToUse.containsKey(card)) {
                	System.out.println("La carta " + card+ " es de tipo " + finalMapToUse.get(card));
                }else {
                	System.out.println("Error, la carta ingresada no existe.");
                }
        		break;
        	//Se muestra el mapa de usuario
            case ("3"):  
            	if(userMap.size()==0) {
            		System.out.println("Su coleccion de usuario esta vacia.");
            	}
            	userMap.forEach((key, value) -> System.out.println("Nombre: " +key + ",Tipo: " + value));
        		break;
        	//Se muestra el mapa de usuario ordenado por value
            case ("4"): 
            	//Referencia de https://www.concretepage.com/java/jdk-8/java-8-stream-sorted-example
            	if(userMap.size()==0) {
            		System.out.println("Su coleccion de usuario esta vacia.");
            	}
            	userMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
    	          .forEach(crd -> System.out.println("Nombre: "+ crd.getKey() +", Tipo: "+ crd.getValue()));
        		break;
        	//Se muestra el mapa de cartas ordenado 
            case ("5"):  
            	finalMapToUse.forEach((key, value) -> System.out.println("Nombre: " +key + ", Tipo: " + value));
        	break;
        	//Se muestra el mapa de cartas ordenado por value
            case ("6"):  
            	//Referencia de https://www.concretepage.com/java/jdk-8/java-8-stream-sorted-example
            	finalMapToUse.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
  	          .forEach(e -> System.out.println("Nombre: "+ e.getKey() +", Tipo: "+ e.getValue()));

            	break;
            //Se termina el programa
            case ("7"): System.out.println("Gracias por utilizar el programa.");
            	break;
            default: System.out.println("Error, opcion ingresada no valida.");
                     break;
	        }
	        sc.nextLine();
        }
        while((option.equals("7")==false));
        
        
    }
}
