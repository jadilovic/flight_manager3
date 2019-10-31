package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Airport;

public interface AirportDAOInterface {

	// method to get all airport
	public List<Airport> getAllAirports() throws SQLException;

	// method to get a specific airport
	public Airport getAirport(String airportName) throws SQLException;

	// method to add a airport
	public void addAirport() throws SQLException;

	// method to print a specific airport
	public void printAirport(Airport airport);
}
