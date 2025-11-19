package spa;

public class RendezVousManagement implements IRendezVousManagement{
	public void getAll() throws Exception {
		for(RendezVous r : RendezVousDAO.getAll()) {
			System.out.println(r.toLine());
		}
	}
	
	

	@Override
	public void create(RendezVous r) throws Exception {
		RendezVousDAO.create(r);
		
	}



	@Override
	public void deletebyid(int id) throws Exception {
		RendezVousDAO.deletebyid(id);
		
	}

	@Override
	public void updatebyid(int id , RendezVous updatedr ) throws Exception {
		RendezVousDAO.updatebyid(id, updatedr);
		
	}

	@Override
	public void updateStatutbyid(int id,String statut) throws Exception {
		RendezVousDAO.updateStatutbyid(id, statut);
	}
}
