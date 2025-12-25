package spa;

public class user {
    
    public String nom;
    public String cin;
    public String role;
    public int telephone;
    public String email;
    public String password;
    
    

    public user (String nom, String cin, int telephone, String email, String password, String role) {
        this.nom = nom;
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String toLine() {
        return this.cin + ";" + this.nom + ";" + this.role + ";" + this.telephone + ";" + this.email + ";" + this.password;
    }
    

    public static user fromLine(String line) {
        String[] t = line.split(";");

        String cin = t[0];
        String nom = t[1];
        String role = t[2];
        int telephone = Integer.parseInt(t[3]);
        String email = t[4];
        String password = t[5];

        return new user(nom, cin, telephone, email, password, role);
    }
}
