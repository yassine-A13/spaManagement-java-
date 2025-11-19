package spa;

public interface IServiceManagement {
	
	void affichertousLesServices() throws Exception;
	void ajouterService(Services s) throws Exception;
	void supprimerService(String nom) throws Exception;

}
