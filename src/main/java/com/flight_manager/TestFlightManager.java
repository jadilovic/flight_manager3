package com.flight_manager;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import DAO.SystemManagerDAO;

public class TestFlightManager {
	
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		
		SystemManagerDAO manager = new SystemManagerDAO();
		
		int option = 0;
		
		while(option != 9){
		System.out.println("\nPlease select option: \n"
				+ "1 to create airport. \n"
				+ "2 to create airline. \n"
				+ "3 to create flight. \n"
				+ "4 to list all airports. \n"
				+ "5 to list all airlines. \n"
				+ "6 to list all flights. \n"
				+ "7 to find a flight. \n"
				+ "8 to book a seat. \n"
				+ "9 to exit the application.");
		
		try{
			option = input.nextInt();
		} catch(InputMismatchException ime){
			System.out.println("Wrong data entered. Please try again");
			option = 9;
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
				// list all airports
				manager.printAllAirports(manager.getAllAirports());
				break;
			case 5:
				// list all airlines
				manager.printAllAirlines(manager.getAllAirlines());
				break;
				case 6:
				// list all flights
				manager.printAllFlights(manager.getAllFlights());
				break;
			case 7:
				// find flight
				manager.enterOriginAndDestination();
				break;
			case 8:
				// Book Seat
				manager.bookSeat();
				break;
			case 9:
				break;
			default: 
				System.out.println("Wrong option. Please try again");
			break;
			}
		}
	}
}