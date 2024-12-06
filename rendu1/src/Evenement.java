import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Evenement {

    // Method to add an event
    public void ajouterEvenement(Connection conn, String nom, Date date, String description, int idUser) throws SQLException {
        String query = "INSERT INTO evenements (nom_event, date_event, description, id_user) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setDate(2, new java.sql.Date(date.getTime()));
            stmt.setString(3, description);
            stmt.setInt(4, idUser);
            stmt.executeUpdate();
        }
    }

    // Method to fetch all events
    public List<String> afficherTousEvenements(Connection conn) throws SQLException {
        List<String> evenements = new ArrayList<>();
        String query = "SELECT * FROM evenements";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                StringBuilder evenement = new StringBuilder();
                evenement.append("ID: ").append(rs.getInt("id_event"))
                         .append(", Name: ").append(rs.getString("nom_event"))
                         .append(", Date: ").append(rs.getDate("date_event"))
                         .append(", Description: ").append(rs.getString("description"))
                         .append(", User ID: ").append(rs.getInt("id_user"));
                
                evenements.add(evenement.toString());
            }
        }
        return evenements;
    }

    // Method to modify only the event's name (nom_event)
    public void modifierNom(Connection conn, int idEvent, String nom) throws SQLException {
        String query = "UPDATE evenements SET nom_event = ? WHERE id_event = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setInt(2, idEvent);
            stmt.executeUpdate();
        }
    }

    // Method to modify only the event's date (date_event)
    public void modifierDate(Connection conn, int idEvent, Date date) throws SQLException {
        String query = "UPDATE evenements SET date_event = ? WHERE id_event = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(date.getTime()));
            stmt.setInt(2, idEvent);
            stmt.executeUpdate();
        }
    }

    // Method to modify only the event's description (description)
    public void modifierDescription(Connection conn, int idEvent, String description) throws SQLException {
        String query = "UPDATE evenements SET description = ? WHERE id_event = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, description);
            stmt.setInt(2, idEvent);
            stmt.executeUpdate();
        }
    }

    // Method to delete an event
    public void supprimerEvenement(Connection conn, int idEvent) throws SQLException {
        String query = "DELETE FROM evenements WHERE id_event = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idEvent);
            stmt.executeUpdate();
        }
    }
}
