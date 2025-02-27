package project;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightDAO flightDAO = new FlightDAO();

    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam String departure, @RequestParam String destination, @RequestParam String date) {
        return flightDAO.searchFlights(departure, destination, date);
    }
}
