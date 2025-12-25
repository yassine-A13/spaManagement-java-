package spa;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ServicesDAO {
	public static Path path = Paths.get("src/spa/services.txt");

    public static void create(Services s) throws Exception {
        Files.write(
            path, 
            (s.toLine() + "\n").getBytes(),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        );
    }
    
    public static List<Services> getAll() throws Exception {
    	if(!Files.exists(path)) return new ArrayList<>();
    	
    	List<String> lines = Files.readAllLines(path);
    	List<Services> s = new ArrayList<>();
    	
    	for(String line : lines) {
            s.add(Services.fromLine(line));
            
            
    	}
    	return s;
    }
    
    public static void getByNom(String nom) throws Exception {

        if (!Files.exists(path)) {
            System.out.println("File not found");
            return;
        }

        List<String> lines = Files.readAllLines(path);

        boolean found = lines.stream()
                .filter(line -> line.contains(nom)) 
                .peek(System.out::println)
                .findAny()
                .isPresent();

        if (!found) {
            System.out.println("Service not found");
        }
    }
    
    
    public static void deletebynom(String nom)throws Exception{
    	if(!Files.exists(path)) return;
    	
    	List<String> lines = Files.readAllLines(path);
    	
    	lines.removeIf(line -> {
    		Services s = Services.fromLine(line);
            return s.nom.equals(nom);
        });
    	
    	Files.write(path, lines);
    	
    }
}
