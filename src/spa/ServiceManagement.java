package spa;

public class ServiceManagement implements IServiceManagement{
	public void affichertousLesServices() throws Exception {
		for(Services s : ServicesDAO.getAll()) {
			System.out.println("Nom : "+ s.nom + "\nPrix : "+s.prix+" DH");
		}
	}
	public void afficherParNom(String nomRe) throws Exception{
		ServicesDAO.getByNom(nomRe);
	}
	public void ajouterService(Services s) throws Exception{
		ServicesDAO.create(s);
	}
	public void supprimerService(String nom) throws Exception{
		ServicesDAO.deletebynom(nom);
	}

}
