package spa;

public class ServiceManagement implements IServiceManagement{
	public void affichertousLesServices() throws Exception {
		for(Services s : ServicesDAO.getAll()) {
			System.out.println("Nom : "+ s.nom + "Prix : "+s.prix);
		}
	}
	public void ajouterService(Services s) throws Exception{
		ServicesDAO.create(s);
	}
	public void supprimerService(String nom) throws Exception{
		ServicesDAO.deletebynom(nom);
	}
}
