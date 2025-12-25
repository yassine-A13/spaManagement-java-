package spa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class MonThread extends Thread{
	public void run() {
		System.out.println("Hello wworld");
	}
}


public class Main {	
    public static void main(String[] args) throws Exception {
    	
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Gérant");
            System.out.println("2 - Employé");
            System.out.println("3 - Client");
            System.out.println("0 - Quitter");
            System.out.print("Choisissez votre rôle : ");

            String choix = scan.next();

            switch (choix) {
                case "1":
                    menuGerant(scan);
                    break;

                case "2":
                    menuEmployee(scan);
                    break;

                case "3":
                    menuClient(scan);
                    break;

                case "0":
                    System.out.println("Au revoir !");
                    System.exit(0);

                default:
                    System.out.println("❌ Choix invalide !");
            }
        }
    }

    // ===================== MENU GERANT =====================
    public static void menuGerant(Scanner scan) throws Exception {
        UserManagement userService = new UserManagement();
        ServiceManagement serviceMgmt = new ServiceManagement();
        RendezVousManagement rdvService = new RendezVousManagement();

        while (true) {
            System.out.println("\n=== MENU GÉRANT ===");
            System.out.println("1 - Gérer utilisateurs");
            System.out.println("2 - Gérer services");
            System.out.println("3 - Gérer rendez-vous");
            System.out.println("0 - Retour");
            System.out.print("Votre choix : ");

            String choix = scan.next();

            switch (choix) {
                case "1":
                    menuGerantUsers(scan, userService);
                    break;
                case "2":
                    menuGerantServices(scan, serviceMgmt);
                    break;
                case "3":
                    menuGerantRdv(scan, rdvService);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌ Choix invalide !");
            }
        }
    }

    // ----------------- GERANT : USERS -----------------
    public static void menuGerantUsers(Scanner scan, UserManagement userService) throws Exception {
        while (true) {
            System.out.println("\n--- GÉRANT : Utilisateurs ---");
            System.out.println("1 - Afficher tous");
            System.out.println("2 - Ajouter");
            System.out.println("3 - Supprimer");
            System.out.println("4 - Modifier");
            System.out.println("0 - Retour");
            System.out.print("Choix : ");

            String choix = scan.next();
            switch (choix) {
                case "1":
                    System.out.print("Type (tous / client / gerant / employee) : ");
                    String type = scan.next();
                    userService.afficherTousLesUtilisateurs(type);
                    break;
                case "2":
                    ajouterUtilisateur(scan, userService);
                    break;
                case "3":
                    System.out.print("CIN à supprimer : ");
                    String cinDel = scan.next();
                    userService.deleteUserByCin(cinDel);
                    System.out.println("✔ Utilisateur supprimé !");
                    break;
                case "4":
                    modifierUtilisateur(scan, userService);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌ Choix invalide !");
            }
        }
    }

    // Méthodes pour ajouter / modifier utilisateur
    public static void ajouterUtilisateur(Scanner scan, UserManagement userService) throws Exception {
        System.out.print("Nom : ");
        String nom = scan.next();
        System.out.print("CIN : ");
        String cin = scan.next();
        System.out.print("Téléphone : ");
        int tel = scan.nextInt();
        System.out.print("Email : ");
        String email = scan.next();
        System.out.print("Password : ");
        String pass = scan.next();
        System.out.print("Role (client/gerant/employee) : ");
        String role = scan.next();

        user newUser;
        switch (role) {
            case "gerant":
                newUser = new gerant(nom, cin, tel, email, pass, role);
                userService.ajouterUser((gerant)newUser);
                break;
            case "employee":
                newUser = new employee(nom, cin, tel, email, pass, role);
                userService.ajouterUser((employee)newUser);
                break;
            case "client":
                newUser = new client(nom, cin, tel, email, pass, role);
                userService.ajouterUser((client)newUser);
                break;
            default:
                System.out.println("❌ Rôle invalide !");
                return;
        }
        System.out.println("✔ Utilisateur ajouté !");
    }

    public static void modifierUtilisateur(Scanner scan, UserManagement userService) throws Exception {
        System.out.print("CIN à modifier : ");
        String cin = scan.next();
        System.out.print("Nouveau nom : ");
        String nom = scan.next();
        System.out.print("Nouveau téléphone : ");
        int tel = scan.nextInt();
        System.out.print("Nouvel email : ");
        String email = scan.next();
        System.out.print("Nouveau password : ");
        String pass = scan.next();
        System.out.print("Nouveau rôle : ");
        String role = scan.next();
        user updateUser = new user(nom, cin, tel, email, pass, role);
        userService.updateByCin(cin, updateUser);
        System.out.println("✔ Utilisateur modifié !");
    }

    // ----------------- GERANT : SERVICES -----------------
    public static void menuGerantServices(Scanner scan, ServiceManagement serviceMgmt) throws Exception {
        while (true) {
            System.out.println("\n--- GÉRANT : Services ---");
            System.out.println("1 - Afficher");
            System.out.println("2 - Ajouter");
            System.out.println("3 - Supprimer");
            System.out.println("4 - afficher par Nom");
            System.out.println("0 - Retour");
            System.out.print("Choix : ");
            String choix = scan.next();
            switch (choix) {
                case "1":
                    serviceMgmt.affichertousLesServices();
                    break;
                case "2":
                    System.out.print("Nom service : ");
                    String nom = scan.next();
                    System.out.print("Prix : ");
                    double prix = scan.nextDouble();
                    serviceMgmt.ajouterService(new Services(nom, prix));
                    System.out.println("✔ Service ajouté !");
                    break;
                case "3":
                    System.out.print("Nom service à supprimer : ");
                    String nomSup = scan.next();
                    serviceMgmt.supprimerService(nomSup);
                    System.out.println("✔ Service supprimé !");
                    break;
                case "4":
                    System.out.print("Nom service à rechercher : ");
                    scan.nextLine(); 
                    String nomRe = scan.nextLine();
                    serviceMgmt.afficherParNom(nomRe);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌ Choix invalide !");
            }
        }
    }

    // ----------------- GERANT : RENDEZ-VOUS -----------------
    public static void menuGerantRdv(Scanner scan, RendezVousManagement rdvService) throws Exception {
        while (true) {
            System.out.println("\n--- GÉRANT : Rendez-vous ---");
            System.out.println("1 - Afficher");
            System.out.println("2 - Créer");
            System.out.println("3 - Supprimer");
            System.out.println("4 - Modifier");
            System.out.println("5 - Accepter / changer statut");
            System.out.println("0 - Retour");
            System.out.print("Choix : ");
            String choix = scan.next();
            switch (choix) {
                case "1":
                    rdvService.getAll();
                    break;
                case "2":
                    rdvService.create(saisirRdv(scan));
                    System.out.println("✔ Rendez-vous créé !");
                    break;
                case "3":
                    System.out.print("ID à supprimer : ");
                    int delId = scan.nextInt();
                    rdvService.deletebyid(delId);
                    System.out.println("✔ Rendez-vous supprimé !");
                    break;
                case "4":
                    System.out.print("ID à modifier : ");
                    int updId = scan.nextInt();
                    rdvService.updatebyid(updId, saisirRdv(scan, updId));
                    System.out.println("✔ Rendez-vous modifié !");
                    break;
                case "5":
                    System.out.print("ID du RDV : ");
                    int statId = scan.nextInt();
                    System.out.print("Nouveau statut : ");
                    String statut = scan.next();
                    rdvService.updateStatutbyid(statId, statut);
                    System.out.println("✔ Statut mis à jour !");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌ Choix invalide !");
            }
        }
    }

    // Méthode pour saisir un rendez-vous
    public static RendezVous saisirRdv(Scanner scan) {
        return saisirRdv(scan, -1);
    }

    public static RendezVous saisirRdv(Scanner scan, int id) {
        if (id == -1) {
            System.out.print("ID : ");
            id = scan.nextInt();
        }
        System.out.print("Date (yyyy-MM-dd) : ");
        LocalDate date = LocalDate.parse(scan.next());
        System.out.print("Nom client : ");
        String nomClient = scan.next();
        System.out.print("Nom employé : ");
        String nomEmployee = scan.next();
        System.out.print("Nom service : ");
        String nomService = scan.next();
        System.out.print("Prix total : ");
        double prixTotal = scan.nextDouble();
        return new RendezVous(id, date, nomClient, nomEmployee, nomService, prixTotal, "en_attente");
    }

    // ===================== MENU EMPLOYEE =====================
    public static void menuEmployee(Scanner scan) throws Exception {
        ServiceManagement serviceMgmt = new ServiceManagement();
        RendezVousManagement rdvService = new RendezVousManagement();

        while (true) {
            System.out.println("\n=== MENU EMPLOYÉ ===");
            System.out.println("1 - Afficher services");
            System.out.println("2 - Gérer rendez-vous");
            System.out.println("0 - Retour");
            System.out.print("Choix : ");

            String choix = scan.next();

            switch (choix) {
                case "1":
                    serviceMgmt.affichertousLesServices();
                    break;
                case "2":
                    menuEmployeeRdv(scan, rdvService);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌ Choix invalide !");
            }
        }
    }

    public static void menuEmployeeRdv(Scanner scan, RendezVousManagement rdvService) throws Exception {
        while (true) {
            System.out.println("\n--- EMPLOYÉ : Rendez-vous ---");
            System.out.println("1 - Afficher tous");
            System.out.println("2 - Modifier rendez-vous");
            System.out.println("0 - Retour");
            System.out.print("Choix : ");

            String choix = scan.next();
            switch (choix) {
                case "1":
                    rdvService.getAll();
                    break;
                case "2":
                    System.out.print("ID à modifier : ");
                    int updId = scan.nextInt();
                    rdvService.updatebyid(updId, saisirRdv(scan, updId));
                    System.out.println("✔ Rendez-vous modifié !");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌ Choix invalide !");
            }
        }
    }

    // ===================== MENU CLIENT =====================
    public static void menuClient(Scanner scan) throws Exception {
        ServiceManagement serviceMgmt = new ServiceManagement();
        RendezVousManagement rdvService = new RendezVousManagement();

        while (true) {
            System.out.println("\n=== MENU CLIENT ===");
            System.out.println("1 - Afficher services");
            System.out.println("2 - Créer rendez-vous");
            System.out.println("0 - Retour");
            System.out.print("Choix : ");

            String choix = scan.next();

            switch (choix) {
                case "1":
                    serviceMgmt.affichertousLesServices();
                    break;
                case "2":
                    rdvService.create(saisirRdv(scan));
                    System.out.println("✔ Rendez-vous créé !");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌ Choix invalide !");
            }
        }
    }
}