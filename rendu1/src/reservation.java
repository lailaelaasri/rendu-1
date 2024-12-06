import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class reservation {

    // Method to reserve a room
    public void reserverSalle(Connection conn, int idUser, int idSalle, Date dateReservation, Time heureDebut, Time heureFin) throws SQLException {
        String query = "INSERT INTO reservations (id_user, id_salle, date_reservation, heure_debut, heure_fin) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUser);
            stmt.setInt(2, idSalle);
            stmt.setDate(3, dateReservation);
            stmt.setTime(4, heureDebut);
            stmt.setTime(5, heureFin);
            stmt.executeUpdate();
        }
    }

    // Method to reserve a field
    public void reserverTerrain(Connection conn, int idUser, int idTerrain, Date dateReservation, Time heureDebut, Time heureFin) throws SQLException {
        String query = "INSERT INTO reservations (id_user, id_terrain, date_reservation, heure_debut, heure_fin) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUser);
            stmt.setInt(2, idTerrain);
            stmt.setDate(3, dateReservation);
            stmt.setTime(4, heureDebut);
            stmt.setTime(5, heureFin);
            stmt.executeUpdate();
        }
    }

    // Method to check availability of a room
    public boolean verifierDisponibiliteSalle(Connection conn, int idSalle, Date dateReservation, Time heureDebut, Time heureFin) throws SQLException {
        String query = "SELECT COUNT(*) FROM reservations WHERE id_salle = ? AND date_reservation = ? AND ((heure_debut < ? AND heure_fin > ?) OR (heure_debut < ? AND heure_fin > ?))";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idSalle);
            stmt.setDate(2, dateReservation);
            stmt.setTime(3, heureDebut);
            stmt.setTime(4, heureDebut);
            stmt.setTime(5, heureFin);
            stmt.setTime(6, heureFin);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; // If count is 0, the room is available
                }
            }
        }
        return false; // Room is not available
    }

    // Method to check availability of a field
    public boolean verifierDisponibiliteTerrain(Connection conn, int idTerrain, Date dateReservation, Time heureDebut, Time heureFin) throws SQLException {
        String query = "SELECT COUNT(*) FROM reservations WHERE id_terrain = ? AND date_reservation = ? AND ((heure_debut < ? AND heure_fin > ?) OR (heure_debut < ? AND heure_fin > ?))";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idTerrain);
            stmt.setDate(2, dateReservation);
            stmt.setTime(3, heureDebut);
            stmt.setTime(4, heureDebut);
            stmt.setTime(5, heureFin);
            stmt.setTime(6, heureFin);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; // If count is 0, the field is available
                }
            }
        }
        return false; // Field is not available
    }

    // Method to display all reservations
    public List<String> afficherReservations(Connection conn) throws SQLException {
        List<String> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                StringBuilder reservation = new StringBuilder();
                reservation.append("ID: ").append(rs.getInt("id_reservation"))
                           .append(", User ID: ").append(rs.getInt("id_user"))
                           .append(", Date: ").append(rs.getDate("date_reservation"))
                           .append(", Start: ").append(rs.getTime("heure_debut"))
                           .append(", End: ").append(rs.getTime("heure_fin"));
                
                if (rs.getInt("id_salle") != 0) {
                    reservation.append(", Room ID: ").append(rs.getInt("id_salle"));
                }
                if (rs.getInt("id_terrain") != 0) {
                    reservation.append(", Field ID: ").append(rs.getInt("id_terrain"));
                }
                
                reservations.add(reservation.toString());
            }
        }
        return reservations;
    }

    // Method to modify a reservation
    public void modifierReservation(Connection conn, int idReservation, Date dateReservation, Time heureDebut, Time heureFin) throws SQLException {
        String query = "UPDATE reservations SET date_reservation = ?, heure_debut = ?, heure_fin = ? WHERE id_reservation = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, dateReservation);
            stmt.setTime(2, heureDebut);
            stmt.setTime(3, heureFin);
            stmt.setInt(4, idReservation);
            stmt.executeUpdate();
        }
    }

    // Method to delete a reservation
    public void supprimerReservation(Connection conn, int idReservation) throws SQLException {
        String query = "DELETE FROM reservations WHERE id_reservation = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idReservation);
            stmt.executeUpdate();
        }
    }
}