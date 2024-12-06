import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SallesEtTerrains {

    // Method to add a room
    public void ajouterSalle(Connection conn, String nomSalle, int capacite) throws SQLException {
        String query = "INSERT INTO salles (nom_salle, capacite) VALUES (?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nomSalle);
            stmt.setInt(2, capacite);
            stmt.executeUpdate();
        }
    }

    // Method to add a field
    public void ajouterTerrain(Connection conn, String nomTerrain, String typeTerrain, String surface) throws SQLException {
        String query = "INSERT INTO terrains (nom_terrain, type_terrain, surface) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nomTerrain);
            stmt.setString(2, typeTerrain);
            stmt.setString(3, surface);
            stmt.executeUpdate();
        }
    }

    // Method to fetch all rooms
    public List<String> afficherSalles(Connection conn) throws SQLException {
        List<String> salles = new ArrayList<>();
        String query = "SELECT * FROM salles";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                salles.add("Salle: " + rs.getString("nom_salle") + ", Capacity: " + rs.getInt("capacite"));
            }
        }
        return salles;
    }

    // Method to fetch all fields
    public List<String> afficherTerrains(Connection conn) throws SQLException {
        List<String> terrains = new ArrayList<>();
        String query = "SELECT * FROM terrains";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                terrains.add("Terrain: " + rs.getString("nom_terrain") + ", Type: " + rs.getString("type_terrain") + ", Surface: " + rs.getString("surface"));
            }
        }
        return terrains;
    }

    // Method to delete a room by its id
    public void supprimerSalle(Connection conn, int idSalle) throws SQLException {
        String query = "DELETE FROM salles WHERE id_salle = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idSalle);
            stmt.executeUpdate();
        }
    }

    // Method to delete a field by its id
    public void supprimerTerrain(Connection conn, int idTerrain) throws SQLException {
        String query = "DELETE FROM terrains WHERE id_terrain = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idTerrain);
            stmt.executeUpdate();
        }
    }
}
