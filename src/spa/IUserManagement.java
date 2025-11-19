package spa;

public interface IUserManagement {
	
	void afficherTousLesUtilisateurs(String type) throws Exception;

    void deleteUserByCin(String cin) throws Exception;

    void ajouterUser(user u) throws Exception;
    
    void updateByCin(String cin , user updateUser) throws Exception;
    
}
