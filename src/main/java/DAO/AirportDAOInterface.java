package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Airline;
import DTO.Airport;

public interface AirportDAOInterface {

	// method to get all airport
	public ArrayList<Airport> getAllAirports() throws SQLException;

	// method to get a specific airport
	public Airport getAirport(String airportName) throws SQLException;

	// method to update a specific airport
	public void updateAirport(Airport airport) throws SQLException;

	// method to delete a specific airport
	public void deleteAirport(Airport airport) throws SQLException;

	// method to add a airport
	public void addAirport() throws SQLException;

	// method to print a specific airport
	public void printAirport(Airport airport);
}
