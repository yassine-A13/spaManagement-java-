package spa;

import java.time.LocalDate;

public class RendezVous {
	int id;
	public LocalDate date;
	public String nomClient;
	public String nomEmployee;
	public String nomService;
	public double prixTotal;
	public String statut;
	
	public RendezVous(int id,LocalDate date , String nomClient,String nomEmployee,String nomService,double prixTotal,String statut) {
		this.id=id;
		this.date=date;
		this.nomClient=nomClient;
		this.nomEmployee=nomEmployee;
		this.nomService=nomService;
		this.prixTotal=prixTotal;
		this.statut=statut;
		
	}
	
	public String toLine() {
        return this.id + ";" + this.date + ";" + this.nomClient + ";" + this.nomEmployee + ";" + this.nomService + ";" + this.prixTotal +";" + this.statut;
    }
    

    public static RendezVous fromLine(String line) {
        String[] t = line.split(";");

        int id =Integer.parseInt(t[0]);
        LocalDate date = LocalDate.parse(t[1]);
        String nomClient = t[2];
        String nomEmployee = t[3];
        String nomService = t[4];
        double prixTotal =Double.parseDouble(t[5]);
        String statut = t[6];
        

        return new RendezVous(id, date, nomClient, nomEmployee, nomService, prixTotal,statut);
    }
}
