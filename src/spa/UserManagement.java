package spa;

public class UserManagement implements IUserManagement{

	@Override
	public void afficherTousLesUtilisateurs(String type) throws Exception {
		if (!type.equals("client") && 
			    !type.equals("gerant") && 
			    !type.equals("tous") &&
			    !type.equals("employee")) {
			    System.out.println("choix invalide");
			}
		
		System.out.println("=== Liste des utilisateurs ===");
        for (user u : UserDAO.getAll()) {
        	if(type.equals("tous")) {
        		System.out.println(u.nom + " | " + u.cin + " | " + u.email);
        	}
        	if(u.role.equals(type)) {
        		System.out.println(u.nom + " | " + u.cin + " | " + u.email);
        	}
        }
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
