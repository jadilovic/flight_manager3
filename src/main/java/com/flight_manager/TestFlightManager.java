package com.flight_manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import DAO.ConnectionManager;
import DAO.SystemManagerDAO;
import DTO.Airline;
import DTO.Airport;

public class TestFlightManager {

	public static void main(String[] args) throws SQLException {
		
		Scanner input = new Scanner(System.in);
		SystemManagerDAO manager = new SystemManagerDAO();
		
		int option = 0;
		
		while(option != 6){
		System.out.println("\nPlease select option: \n"
				+ "1 to create airport. \n"
				+ "2 to create airline. \n"
				+ "3 to create flight. \n"
				+ "4 to find flight. \n"
				+ "5 to book a seat on a flight. \n"
				+ "6 to exit the application.");
		
		try{
			option = input.nextInt();
		} catch(InputMismatchException ime){
			System.out.println("Wrong data entered. Please try again");
			option = 6;
		}
			switch(option){
			case 1: 
				// Create Airport
				manager.addAirport();
				break;
			case 2: 
				// Create Airline
				manager.addAirline();
				break;
			case 3:
				// Create Flight
				manager.addFlight();;
				break;
			case 4:
				// Find Flight
				// enterOriginAndDestination();
				break;
			case 5:
				// Book Seat
				// enterDataToBookSeat();
				break;
			case 6:
				break;
			default: 
				System.out.println("Wrong option. Please try again");
			break;
			}
		}
		input.close();
		// get all airlines
		//ArrayList<Airline> airlines = (ArrayList<Airline>) manager.getAllAirlines();
		
		// get all airports
		//ArrayList<Airport> airports = (ArrayList<Airport>) manager.getAllAirports();

		// print all airlines
		//for (Airline airline : airlines) {
		//	manager.printAirline(airline);
		//}
		
		// print all airports
		//for(Airport airport: airports){
		//	manager.printAirport(airport);
		//}
		//System.out.println();
		// get the airline
		//manager.printAirline(manager.getAirline("vat"));
		//manager.addAirport();
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
		// System.out.println();
		// System.out.println();
		
		// add flight
		// manager.addFlight();
		// print flight
		//manager.printFlight(manager.getFlight(1));
		
		// print seats
	/*	manager.getAllFlightSeats(3);

		System.out.println();
		manager.printSeat(manager.getSeatById(35));
		System.out.println();
		manager.getSeatById(32);
		System.out.println();
		manager.printSeat(manager.getSeatByOther("B", 2, 1));
		// manager.addFlight();
		manager.bookSeat();

		// close the connection
		ConnectionManager.getInstance().close();
		*/
	}
}