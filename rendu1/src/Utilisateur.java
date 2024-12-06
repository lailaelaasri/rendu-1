import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class Utilisateur {
    public void ajouterUtilisateur(Connection conn, String nom, String prenom, String email, String type) throws SQLException {
        String query = "INSERT INTO utilisateurs (nom, prenom, email, type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, type);
            stmt.executeUpdate();
        }
    }

    public List<String> afficherUtilisateur(Connection conn, String nom, String prenom) throws SQLException {
        List<String> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateurs WHERE nom = ? AND prenom = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    utilisateurs.add(rs.getString("nom") + " " + rs.getString("prenom"));
                }
            }
        }
        return utilisateurs; 
    }

    public List<String> afficherTousUtilisateurs(Connection conn) throws SQLException {
        List<String> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateurs";
        
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                utilisateurs.add(rs.getString("nom") + " " + rs.getString("prenom"));
            }
        }
        return utilisateurs; 
    }
    public void supprimerUtilisateur(Connection conn, int idUser) throws SQLException {
        String query = "DELETE FROM utilisateurs WHERE id_user = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUser);
            stmt.executeUpdate();
        }
    }
}
