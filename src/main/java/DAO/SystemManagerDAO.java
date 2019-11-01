package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.Airline;
import DTO.Airport;
import DTO.Flight;
import DTO.Seat;

/**
 * IMPLEMENTATION OF ALL DAO INTERFACES
 */

public class SystemManagerDAO implements AirlineDAOInterface, AirportDAOInterface, 
												FlightDAOInterface, SeatDAOInterface {

	// connect to the database
	Connection connection = ConnectionManager.getInstance().getConnection();
	
	// create array lists to hold airlines, airports, flights and seats
	private List<Airline> listOfAirlines = new ArrayList<>();
	private List<Airport> listOfAirports = new ArrayList<>();
	private List<Flight> listOfFlights = new ArrayList<>();

	// IMPLEMENTATION OF AIRLINE INTERFACE
	
	@Override
	public List<Airline> getAllAirlines() throws SQLException {

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
				listOfAirlines.add(new Airline(rs.getString("name")));
			}
		}
		return listOfAirlines;
	}

	@Override
	public Airline getAirline(String airlineName) throws SQLException {

		// null airline
		Airline airline = new Airline();

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

	private String enterString() {
		// new Scanner
		java.util.Scanner input = new java.util.Scanner(System.in);
			String text = input.next();
		// close the scanner
		//input.close();
		return text;
	}

	@Override
	public void addAirline() throws SQLException {

		// create an SELECT SQL query
		String query = "INSERT INTO airline (name) VALUES (?)";

		System.out.print("Enter airline name: ");
		String name = enterString();
		
		if(validAirlineName(name) && !airlineExists(name)){
			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {

				// fill in the placeholders/parameters
				statement.setString(1, name.toUpperCase());

				// execute the query
				statement.executeUpdate();

				System.out.println("Airline " + name + " added to the database.");
			}
		}
	}

	private boolean airlineExists(String name) throws SQLException {
		if(getAirline(name) != null){
			System.out.println("Entered Airline name '" + name + "' already exists");
			return true;
		}
		return false;
	}

	private boolean validAirlineName(String name) throws SQLException {
		if(name.length() > 5){
			System.out.println("Length of the Airline name '" + name + "' is greater than 5 characters");
			return false;
		}
		else if(!onlyAlphabets(name)){
			System.out.println("Entered Airline name '" + name + "' does not contain all alphabets");
			return false;
		} else
		return true;
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
	
	@Override
	public void printAllAirlines(List<Airline> airlines) throws SQLException {
		for(Airline airline: airlines)
			printAirline(airline);
	}
	
	// AIRPORT INTERFACE IMPLEMENTATION

	@Override
	public List<Airport> getAllAirports() throws SQLException {

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
				listOfAirports.add(new Airport(rs.getString("name"), rs.getString("city")));
			}
		}
		return listOfAirports;
	}

	@Override
	public Airport getAirport(String airportName) throws SQLException {

		// new airport
		Airport airport = new Airport();

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
	public void addAirport() throws SQLException {

		// create an SELECT SQL query
		String query = "INSERT INTO airport (name, city) VALUES (?, ?)";

		System.out.print("Enter airport name: ");
		String name = enterString();
		
		System.out.print("Enter city: ");
		String city = enterString();

		if(validAirportName(name) && !airportExists(name)){
			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {

				// fill in the placeholders/parameters
				statement.setString(1, name.toUpperCase());
				statement.setString(2, city);

				// execute the query
				statement.executeUpdate();

				System.out.println("Airport " + name + " in the city " + city + " added to the database.");
			}
		}
	}

	private boolean validAirportName(String name) throws SQLException {
		if(name.length() != 3){
			System.out.println("Length of the Airport name '" + name + "' must be exactly 3 characters");
			return false;
		}
		else if(!onlyAlphabets(name)){
			System.out.println("Entered Airport name '" + name + "' does not contain all alphabets");
			return false;
		} else 
		return true;
	}

	private boolean airportExists(String name) throws SQLException {
			if(getAirport(name) != null){
				System.out.println("Entered Airport name '" + name + "' already exists");
				return true;
			}
		return false;
	}

	@Override
	public void printAirport(Airport airport) {
		if (airport != null) {
			System.out.println("Airport name: " + airport.getName() + ", city: " + airport.getCity());
		} else {
			System.out.println("No airport to print.");
		}
	}
	
	@Override
	public void printAllAirports(List<Airport> airports) throws SQLException {
		for(Airport airport: airports)
			printAirport(airport);
	}
	
	// FLIGHT INTERFACE IMPLEMENTATION

	@Override
	public List<Flight> getAllFlights() throws SQLException {

		// create an SELECT SQL query
		String query = "SELECT * FROM flight";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				Statement statement = connection.createStatement();) {

			// execute the query
			rs = statement.executeQuery(query);

			// add airports to the arrayList
			while (rs.next()) {
				listOfFlights.add(new Flight(rs.getInt("id"), rs.getString("flight_name"), rs.getString("origin"), 
						rs.getString("destination"), rs.getString("airport"), rs.getString("airline")));
			}
		}
		return listOfFlights;
	}

	@Override
	public Flight getFlight(Integer id) throws SQLException {

		// new flight
		Flight flight = new Flight();

		// create an SELECT SQL query
		String query = "SELECT * FROM flight WHERE id = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement pstatement = connection.prepareStatement(query);) {

			// fill in the placeholders/parameters
			pstatement.setInt(1, id);

			// execute the query
			rs = pstatement.executeQuery();

			// set the cursor
			if (rs.next()) {

				// populate flight
				flight = new Flight(rs.getInt("id"), rs.getString("flight_name"), rs.getString("origin"), 
						rs.getString("destination"), rs.getString("airport"), rs.getString("airline"));

				// close the ResultSet
				rs.close();
			}
		}
		return flight;
	}

	@Override
	public void addFlight() throws SQLException {
		// create an SELECT SQL query
		String query = "INSERT INTO flight (id, flight_name, origin, destination, airport, airline) "
						+ "VALUES (?, ?, ?, ?, ?, ?)";

		System.out.print("Enter unique ID of the flight: ");
		int flightId = enterInteger();
		
		// checking if flight ID already exists
		if(!flightIdExists(flightId)){
			System.out.print("Enter flight name: ");
			String flightName = enterString();
			// checking if flight name already exists
			if(!flightNameExists(flightName.toUpperCase())){
				System.out.print("Enter origin: ");
				String origin = enterString();
				// checking if origin location exists
				if(locationExists(origin)){
					System.out.print("Enter destination: ");
					String destination = enterString();
					// checking if destination location exists
					if(locationExists(destination)){
						System.out.print("Enter airport: ");
						String airport = enterString();
						// checking if airport exists
						Airport enteredAirport = getAirport(airport.toUpperCase());
						if(enteredAirport != null && originOfAirport(enteredAirport, origin)){
							System.out.print("Enter airline: ");
							String airline = enterString();
							// checking if airline exists
							if(getAirline(airline.toUpperCase()) != null){
								System.out.println("Please enter number of seats per row");
								int numSeatsInRow = enterInteger();
			// creating seats for the new created flight
			createSeats(numSeatsInRow, flightId);
			try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

					// fill in the placeholders/parameters
					statement.setInt(1, flightId);
					statement.setString(2, flightName.toUpperCase());
					statement.setString(3, origin);
					statement.setString(4, destination);
					statement.setString(5, airport);
					statement.setString(6, airline);

					// execute the query
					statement.executeUpdate();

			System.out.println("Flight with id " + flightId + " and flight name " + flightName + " "
									+ " origin " + origin + " and destination " + destination + " "
									+ "added to the database.");
										}	
										
									} else {
										System.out.println("Entered airline does not exist");
									}
								} else {
									System.out.println("Entered airport does not exist");
								}
							} else {
								System.out.println("Entered destination does not exist");
							}
						} else {
							System.out.println("Entered origin does not exist");
						}
					} else {
						System.out.println("Entered flight name already exist");
					}
				} else {
					System.out.println("Entered flight ID already exist");
				}
			}	
	
	private boolean flightIdExists(int flightId) throws SQLException {
		Flight flight = getFlight(flightId);
		if(flight.getId() == null)
			return false;
		else
		return true;
	}

	private boolean originOfAirport(Airport enteredAirport, String origin) {
		if(enteredAirport.getCity().equals(origin))
			return true;
		else{
			System.out.println("Entered airport and origin do not match");
			return false;
		}
	}

	private boolean flightNameExists(String flightName) throws SQLException {
		listOfFlights = getAllFlights();
		for(Flight flight: listOfFlights){
			if(flight.getFlight_name().equals(flightName))
				return true;
		}
		return false;
	}

	private boolean locationExists(String location) throws SQLException {
		listOfAirports = getAllAirports();
		for(Airport airport: listOfAirports){
			if(location.equals(airport.getCity()))
			return true;
		}
		return false;
	}

	private Integer enterInteger() {
		Scanner input = new Scanner(System.in);
		Integer value = input.nextInt();
		// input.close();
		return value;
	}

	@Override
	public void printFlight(Flight flight) {
		if (flight != null) {
			System.out.println("Flight name: " + flight.getFlight_name() + ", flight id: " + flight.getId() + ""
					+ ", airline: " + flight.getAirline() + ", origin: " + flight.getOrigin() + ""
							+ " and destination " + flight.getDestination());
		} else {
			System.out.println("No airport to print.");
		}
	}
	
	@Override
	public void printAllFlights(List<Flight> flights) throws SQLException {
		for(Flight flight: flights)
			printFlight(flight);
	}

	// SEAT INTERFACE IMPLEMENTATION

	@Override
	public List<Seat> getAllFlightSeats(Integer flightId) throws SQLException {

		List<Seat> listOfSeats = new ArrayList<>();
		
		// create an SELECT SQL query
		String query = "SELECT * FROM seats WHERE flightID = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setInt(1, flightId);
			// execute the query
			rs = statement.executeQuery();

			// add airports to the arrayList
			while (rs.next()) {
				listOfSeats.add(new Seat(rs.getInt("seatID"), rs.getString("rowLetter"), rs.getInt("seat_number"), 
						rs.getBoolean("available"), rs.getInt("flightID")));
			}
		}
		return listOfSeats;
	}

	@Override
	public Seat getSeatById(Integer id) throws SQLException {

		// Seat object
		Seat seat = null;

		// create an SELECT SQL query
		String query = "SELECT * FROM seats WHERE seatID = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setInt(1, id);
			// execute the query
			rs = statement.executeQuery();

			// add airports to the arrayList
			while (rs.next()) {
				seat = new Seat(rs.getInt("seatID"), rs.getString("rowLetter"), rs.getInt("seat_number"), 
						rs.getBoolean("available"), rs.getInt("flightID"));
			}
		}
		return seat;
	}

	@Override
	public Seat getSeatByOther(String row, int seatNum, int flightId) throws SQLException {

		// Seat object
		Seat seat = null;

		// create an SELECT SQL query
		String query = "SELECT * FROM seats WHERE rowLetter = ? AND seat_number = ? AND flightID = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, row);
			statement.setInt(2, seatNum);
			statement.setInt(3, flightId);
			
			// execute the query
			rs = statement.executeQuery();

			// add airports to the arrayList
			while (rs.next()) {
				seat = new Seat(rs.getInt("seatID"), rs.getString("rowLetter"), rs.getInt("seat_number"), 
						rs.getBoolean("available"), rs.getInt("flightID"));
			}
		}
		return seat;
	}

	@Override
	public void createSeats(int numSeatsInRow, Integer flightID) throws SQLException {
		
		String query = "INSERT INTO seats (rowLetter, seat_number, available, flightID) "
				+ "VALUES (?, ?, ?, ?)";
		
		String [] rowsSQL = {"A", "B", "C", "D", "E", "F"};
		for(int i = 0; i < rowsSQL.length; i++){
			for(int j = 1; j <= numSeatsInRow; j++){
				try(PreparedStatement pstat = connection.prepareStatement(query);){
					pstat.setString(1, rowsSQL[i]);
					pstat.setInt(2, j);
					pstat.setBoolean(3, true);
					pstat.setInt(4, flightID);
					pstat.executeUpdate();
				}
			}
		}
	}

	@Override
	public void bookSeat() throws SQLException {
		System.out.println("On what flight you would like to book a seat? Please enter flight id.");
		
		Integer flightId = enterInteger();
		
		if(getFlight(flightId) != null){
			List<Seat> seats = null;
			seats = getAllFlightSeats(flightId);
			System.out.println("     SeatID Row Seat# Available FlightID");
			for(Seat seat: seats){
				printSeat(seat);
			}
			System.out.println("Please choose from available seats. Enter seat id.");
			
			int seatId = enterInteger();
			if(getSeatById(seatId).isAvailable() == true){
				// create an SELECT SQL query
				String query = "UPDATE seats SET available = ? WHERE seatID = ?";
				try (
						// java.sql.Statement
						PreparedStatement statement = connection.prepareStatement(query);) {

					// fill in the placeholders/parameters
					statement.setBoolean(1, false);
					statement.setInt(2, seatId);

					// execute the query
					statement.executeUpdate();
				}
			} else
				System.out.println("Selected seat is not available");
		} else
			System.out.println("There is no flight with given flight id");
	}
	

	@Override
	public void printSeat(Seat seat) {
		System.out.println("Seat: " + seat.getSeatID() + ",    " + seat.getRow() + ""
				+ ",    " + seat.getSeatNumber() + ",    " + seat.isAvailable() + ","
						+ "    " + seat.getFlightID());
	}

}
