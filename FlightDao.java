package project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    public List<Flight> searchFlights(String departure, String destination, String date) {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM flights WHERE departure = ? AND destination = ? AND DATE(departure_time) = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, departure);
            stmt.setString(2, destination);
            stmt.setString(3, date);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight(
                    rs.getInt("flight_id"),
                    rs.getString("flight_number"),
                    rs.getString("departure"),
                    rs.getString("destination"),
                    rs.getString("departure_time"),
                    rs.getString("arrival_time"),
                    rs.getInt("available_seats"),
                    rs.getDouble("price")
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public void makeReservation(int customerId, int flightId, String seatClass, double price) {
        String query = "INSERT INTO reservations (customer_id, flight_id, seat_class, price, reservation_time) VALUES (?, ?, ?, ?, NOW())";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, flightId);
            stmt.setString(3, seatClass);
            stmt.setDouble(4, price);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

