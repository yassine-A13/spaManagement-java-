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
    
    public static Services getbynom(String nom)throws Exception {
    	if(!Files.isExecutable(path)) return null;
    	
    	List<String> lines = Files.readAllLines(path);
    	
    	for(String line : lines) {
    		Services s = Services.fromLine(line);
    		if(s.nom.equals(nom)) {
    			System.out.print(s.toLine());
    			return s;
    		}
    	}
    	System.out.print("user not found");
    	return null;
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
