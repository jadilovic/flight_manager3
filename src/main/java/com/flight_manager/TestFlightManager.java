package com.flight_manager;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ConnectionManager;
import DAO.SystemManagerDAO;
import DTO.Airline;
import DTO.Airport;

public class TestFlightManager {

	public static void main(String[] args) throws SQLException {

		SystemManagerDAO manager = new SystemManagerDAO();

		// get all airlines
		ArrayList<Airline> airlines = manager.getAllAirlines();
		
		// get all airports
		ArrayList<Airport> airports = manager.getAllAirports();

		// print all airlines
		for (Airline airline : airlines) {
			manager.printAirline(airline);
		}
		
		// print all airports
		for(Airport airport: airports){
			manager.printAirport(airport);
		}
		System.out.println();
		// get the airline
		// manager.printAirline(manager.getAirline("vat"));
		// add a airline
		// manager.addAirline();
		// print airline
		// manager.printAirline(manager.getAirline("emirat"));

		// get the airport
		// manager.printAirport(manager.getAirport("bi"));
		// add airport
		// manager.addAirport();
		// print airport
		//manager.printAirport(manager.getAirport("det"));
		System.out.println();
		// System.out.println();
		
		// add flight
		// manager.addFlight();
		// print flight
		//manager.printFlight(manager.getFlight(1));
		
		// print seats
		manager.getAllFlightSeats(1);

		System.out.println();


		// close the connection
		ConnectionManager.getInstance().close();
	}

}
