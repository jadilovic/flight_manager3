package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Flight;

public interface FlightDAOInterface {

	// method to get all flights
	public ArrayList<Flight> getAllFlights() throws SQLException;

	// method to get a specific flight
	public Flight getFlight(Integer id) throws SQLException;

	// method to add a flight
	public void addFlight() throws SQLException;

	// method to print a specific flight
	public void printFlight(Flight flight);
}
