package spa;

import java.util.Scanner;

public class main {

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        UserManagement userService = new UserManagement();

        while (true) {

           

            System.out.println("\n=== MENU UTILISATEURS ===");
            System.out.println("1 - Afficher utilisateurs");
            System.out.println("2 - Ajouter utilisateur");
            System.out.println("3 - Supprimer utilisateur par CIN");
            System.out.println("4 - Modifier utilisateur par CIN");
            System.out.println("0 - Quitter");
            System.out.print("Votre choix : ");

            String choix = scan.next();

            switch (choix) {

                case "1":
                    
                    System.out.print("Type (tous / client / gerant / employee) : ");
                    String type = scan.next();
                    userService.afficherTousLesUtilisateurs(type);
                    waitEnter(scan);
                    break;

                case "2":
                    
                    System.out.println("\n=== Ajouter utilisateur ===");

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
                            userService.ajouterUser((gerant) newUser);
                            break;

                        case "employee":
                            newUser = new employee(nom, cin, tel, email, pass, role);
                            userService.ajouterUser((employee) newUser);
                            break;

                        case "client":
                            newUser = new client(nom, cin, tel, email, pass, role);
                            userService.ajouterUser((client) newUser);
                            break;

                        default:
                            System.out.println("❌ Rôle invalide !");
                            waitEnter(scan);
                            continue;
                    }
                    
                    if (newUser instanceof gerant) {
                        System.out.println("➡ C’est un gérant");
                    } else if (newUser instanceof employee) {
                        System.out.println("➡ C’est un employé");
                    } else if (newUser instanceof client) {
                        System.out.println("➡ C’est un client");
                    }
                    
                    System.out.println("✔ Utilisateur ajouté !");
                    waitEnter(scan);
                    break;

                case "3":
                   
                    System.out.print("Entrer le CIN à supprimer : ");
                    String cinDelete = scan.next();
                    userService.deleteUserByCin(cinDelete);
                    System.out.println("✔ Utilisateur supprimé !");
                    waitEnter(scan);
                    break;

                case "4":
                   
                    System.out.println("\n=== Modifier utilisateur ===");

                    System.out.print("Entrer CIN de l'utilisateur à modifier : ");
                    String targetCin = scan.next();

                    System.out.print("Nouveau nom : ");
                    String newNom = scan.next();

                    System.out.print("Nouveau téléphone : ");
                    int newTel = scan.nextInt();

                    System.out.print("Nouvel email : ");
                    String newEmail = scan.next();

                    System.out.print("Nouveau mot de passe : ");
                    String newPass = scan.next();

                    System.out.print("Nouveau rôle (client/gerant/employee) : ");
                    String newRole = scan.next();

                    user updateUser = new user(newNom, targetCin, newTel, newEmail, newPass, newRole);

                    userService.updateByCin(targetCin, updateUser);
                    System.out.println("✔ Utilisateur modifié !");
                    waitEnter(scan);
                    break;

                case "0":
                    System.out.println("Au revoir !");
                    System.exit(0);

                default:
                    System.out.println("❌ Choix invalide !");
                    waitEnter(scan);
            }
        }
    }

    public static void waitEnter(Scanner scan) {
        System.out.println("\nAppuyez sur Entrer pour continuer...");
        scan.nextLine();
        scan.nextLine();
    }
}
