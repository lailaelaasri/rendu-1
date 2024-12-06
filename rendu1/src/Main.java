import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/rendu1";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion reussie!");
            Utilisateur u1 = new Utilisateur();
            u1.ajouterUtilisateur(conn, "elaasri", "laila", "lola.elaasri@gmail.com", "ETUDIANT");
            /*u1.afficherUtilisateur(conn, "elaasri", "laila");
           u1.ajouterEvenement(conn, "Conference 2023", "2023-12-01", "Conference annuelle sur la technologie", );
           u1.afficherTousEvenements(conn); 
           u1.modifierNom(conn, 2, "Conference 2024 - Update");
           u1.modifierDate(conn, 1, Date.valueOf("2024-12-02"));
           u1.modifierDescription(conn, 1, "Conference annuelle sur la technologie avancee");
           u1.ajouterSalle(conn, "Salle A", 50);
           u1.afficherSalles(conn);
           u1.ajouterTerrain(conn, "Terrain 2", "Football");
           u1.afficherTerrains(conn);
           u1.reserversalle(conn, 5, 6, 1, "2024-12-01 10:00:00");
           u1.afficherReservations(conn);
           u1.modifierReservation(conn, 2, 5, 6, 1, 1, "2024-12-02 11:00:00");*/

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}