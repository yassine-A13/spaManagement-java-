package spa;

import java.util.Comparator;
import java.util.Set;

public class UserManagement implements IUserManagement{

	@Override
	public void afficherTousLesUtilisateurs(String role) throws Exception {
	    Set<String> typesValides = Set.of("client", "gerant", "employee", "tous");

	    if (!typesValides.contains(role)) {
	        System.out.println("choix invalide");
	        return;
	    }

	    System.out.println("=== Liste des utilisateurs ===");

	    
	    UserDAO.getAll().stream()
	        .filter(u -> role.equals("tous") || u.role.equals(role))
	        .sorted(Comparator.comparing(u -> u.nom))
	        .forEach(u -> System.out.printf("%s | %s | %s%n", u.nom, u.cin, u.email));

	    
	    long total = UserDAO.getAll().stream()
	        .filter(u -> role.equals("tous") || u.role.equals(role))
	        .count();
	    System.out.println("\nTotal utilisateurs : " + total);

	}



	@Override
	public void deleteUserByCin(String cin) throws Exception {
		UserDAO.deletebycin(cin);
	}

	@Override
	public void ajouterUser(user u) throws Exception {
		UserDAO.create(u);
	}

	@Override
	public void updateByCin(String cin, user updateUser) throws Exception {
		UserDAO.updatebycin(cin, updateUser);
	}
	
}
