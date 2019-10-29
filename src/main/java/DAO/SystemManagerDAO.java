package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Airline;
import DTO.Airport;

/**
 * Airline Implementation
 */

public class SystemManagerDAO implements AirlineDAOInterface, AirportDAOInterface {

	// connect to the database
	Connection connection = ConnectionManager.getInstance().getConnection();

	@Override
	public ArrayList<Airline> getAllAirlines() throws SQLException {

		// create an array list to hold airlines
		ArrayList<Airline> airlines = new ArrayList<>();

		// create an SELECT SQL query
		String query = "SELECT * FROM airline";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				Statement statement = connection.createStatement();) {

			// execute the query
			rs = statement.executeQuery(query);

			// add airlines to the arrayList
			while (rs.next()) {
				airlines.add(new Airline(rs.getString("name")));
				System.out.println("Airline: " + rs.getString("name") + " added to airlines");
			}
		}
		return airlines;
	}

	@Override
	public Airline getAirline(String airlineName) throws SQLException {

		// null airline
		Airline airline = null;

		// create an SELECT SQL query
		String query = "SELECT * FROM airline WHERE name = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement pstatement = connection.prepareStatement(query);) {

			// fill in the placeholders/parameters
			pstatement.setString(1, airlineName);

			// execute the query
			rs = pstatement.executeQuery();

			// set the cursor
			if (rs.next()) {

				// populate airline
				airline = new Airline(rs.getString("name"));

				// close the ResultSet
				rs.close();
			}
		}
		return airline;
	}

	@Override
	public void updateAirline(Airline airline) throws SQLException {
		if (airline != null) {

			// create an SELECT SQL query
			String query = "UPDATE airline SET name = ? WHERE name = ?";

			// new Scanner
			java.util.Scanner input = new java.util.Scanner(System.in);

			System.out.print("Set a new name for airline (current: " + airline.getName() + " ): ");
			String name = input.next();

			// close the scanner
			input.close();

			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {

				// fill in the placeholders/parameters
				statement.setString(1, name);
				statement.setString(2, airline.getName());

				// execute the query
				statement.executeUpdate();

				System.out.println("Airline " + airline.getName() + " updated in the database.");
			}
		}
	}

	@Override
	public void deleteAirline(Airline airline) throws SQLException {
		if (airline != null) {
			// create an SELECT SQL query
			String query = "DELETE FROM airline WHERE name = ?";

			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {

				// Name of airline to be deleted
				String airlineNameDeleted = airline.getName();
				
				// fill in the placeholders/parameters
				statement.setString(1, airline.getName());

				// execute the query
				statement.executeUpdate();

				System.out.println("Airline " + airlineNameDeleted + " deleted from the database.");
			}
		}
	}

	@Override
	public void addAirline() throws SQLException {

		// create an SELECT SQL query
		String query = "INSERT INTO airline (name) VALUES (?)";

		// new Scanner
		java.util.Scanner input = new java.util.Scanner(System.in);

		System.out.print("Enter airline name: ");
		String name = input.next();
		
		if(getAirline(name) != null){
			System.out.println("Entered Airline name '" + name + "' already exists");
		}
		else if(name.length() > 5){
			System.out.println("Length of the Airline name '" + name + "' is greater than 5 characters");
		}
		else if(!onlyAlphabets(name)){
			System.out.println("Entered Airline name '" + name + "' does not contain all alphabets");
		} else {
			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {

				// fill in the placeholders/parameters
				statement.setString(1, name);

				// execute the query
				statement.executeUpdate();

				System.out.println("Airline " + name + " added to the database.");
			}
		}
		// close the scanner
		input.close();
	}

	// Checking if given name is all alphabets
	private static boolean onlyAlphabets(String name) {
        return ((name != null) 
                && (!name.equals("")) 
                && (name.matches("^[a-zA-Z]*$")));
	}

	@Override
	public void printAirline(Airline airline) {
		if (airline != null) {
			System.out.println("Airline: " + airline.getName());
		} else {
			System.out.println("No airline to print.");
		}
	}
	
	// AIRPORT INTERFACE IMPLEMENTATION

	@Override
	public ArrayList<Airport> getAllAirports() throws SQLException {

		// create an array list to hold airports
		ArrayList<Airport> airports = new ArrayList<>();

		// create an SELECT SQL query
		String query = "SELECT * FROM airport";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				Statement statement = connection.createStatement();) {

			// execute the query
			rs = statement.executeQuery(query);

			// add airports to the arrayList
			while (rs.next()) {
				airports.add(new Airport(rs.getString("name"), rs.getString("city")));
				System.out.println("Airport: " + rs.getString("name") + " in the city of "
						+ rs.getString("city") + " added to airports");
			}
		}
		return airports;
	}

	@Override
	public Airport getAirport(String airportName) throws SQLException {

		// null airport
		Airport airport = null;

		// create an SELECT SQL query
		String query = "SELECT * FROM airport WHERE name = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement pstatement = connection.prepareStatement(query);) {

			// fill in the placeholders/parameters
			pstatement.setString(1, airportName);

			// execute the query
			rs = pstatement.executeQuery();

			// set the cursor
			if (rs.next()) {

				// populate airport
				airport = new Airport(rs.getString("name"), rs.getString("city"));

				// close the ResultSet
				rs.close();
			}
		}
		return airport;
	}

	@Override
	public void updateAirport(Airport airport) throws SQLException {
		if (airport != null) {

			// create an SELECT SQL query
			String query = "UPDATE airport SET name = ?, city = ? WHERE name = ?";

			// new Scanner
			java.util.Scanner input = new java.util.Scanner(System.in);

			System.out.print("Set a new name for airport (current: " + airport.getName() + " ): ");
			String name = input.next();
			
			System.out.print("Set a new name for city (current: " + airport.getCity() + " ): ");
			String city = input.next();

			// close the scanner
			input.close();

			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {

				// fill in the placeholders/parameters
				statement.setString(1, name);
				statement.setString(2, city);
				statement.setString(3, airport.getName());

				// execute the query
				statement.executeUpdate();

				System.out.println("Airport " + name + " updated in the database.");
			}
		}
	}

	@Override
	public void deleteAirport(Airport airport) throws SQLException {
		if (airport != null) {
			// create an SELECT SQL query
			String query = "DELETE FROM airport WHERE name = ?";

			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {

				// Name of airport to be deleted
				String airportNameDeleted = airport.getName();
				
				// fill in the placeholders/parameters
				statement.setString(1, airportNameDeleted);

				// execute the query
				statement.executeUpdate();

				System.out.println("Airline " + airportNameDeleted + " deleted from the database.");
			}
		}
	}

	@Override
	public void addAirport() throws SQLException {

		// create an SELECT SQL query
		String query = "INSERT INTO airport (name, city) VALUES (?, ?)";

		// new Scanner
		java.util.Scanner input = new java.util.Scanner(System.in);

		System.out.print("Enter airport name: ");
		String name = input.next();
		
		System.out.print("Enter city: ");
		String city = input.next();

		// close the scanner
		input.close();

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			// fill in the placeholders/parameters
			statement.setString(1, name);
			statement.setString(2, city);

			// execute the query
			statement.executeUpdate();

			System.out.println("Airport " + name + " in the city " + city + " added to the database.");
		}
	}

	@Override
	public void printAirport(Airport airport) {
		if (airport != null) {
			System.out.println("Airport name: " + airport.getName() + ", city: " + airport.getCity());
		} else {
			System.out.println("No airport to print.");
		}
	}

}
