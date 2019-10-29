package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Airline;

public interface AirlineDAOInterface {

	// method to get all airlines
	public ArrayList<Airline> getAllAirlines() throws SQLException;

	// method to get a specific airline
	public Airline getAirline(String airlineName) throws SQLException;

	// method to update a specific airline
	public void updateAirline(Airline airline) throws SQLException;

	// method to delete a specific airline
	public void deleteAirline(Airline airline) throws SQLException;

	// method to add a airline
	public void addAirline() throws SQLException;

	// method to print a specific airline
	public void printAirline(Airline airline);
}
