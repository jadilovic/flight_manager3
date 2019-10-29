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
		manager.printAirline(manager.getAirline("bat"));
		// add a airline
		manager.addAirline();
		// update airline
		manager.updateAirline(manager.getAirline("baair"));
		// print airport
		// manager.printAirport(manager.getAirport("det"));

		// System.out.println();
		// update airport
		// studentDAO.updateStudent(studentDAO.getStudent(6));

		// System.out.println();
		// delete student
		//studentDAO.deleteStudent(studentDAO.getStudent(7));

		System.out.println();


		// close the connection
		ConnectionManager.getInstance().close();
	}

}
