package spa;

public interface IRendezVousManagement {
	 void create(RendezVous r) throws Exception;
	 void getAll() throws Exception;
	 void deletebyid(int id) throws Exception;
	 void updatebyid(int id , RendezVous updatedr) throws Exception;
	 void updateStatutbyid(int id, String statut) throws Exception;
}
