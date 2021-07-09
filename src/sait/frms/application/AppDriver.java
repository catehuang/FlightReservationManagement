package sait.frms.application;

import sait.frms.manager.*;
import sait.frms.problemdomain.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import sait.frms.gui.*;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/* GUI invoked
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
		*/
		
		Flight f1 = new Flight("TB-8477", "Try a Bus Airways", "YWG", "YOW", "Tuesday", "10:15", 76, 399.00);
		Flight f2 = new Flight("CA-6637", "Conned Air", "DEL", "DFW", "Friday", "11:45", 174, 971.00);
		ReservationManager rsvmng = new ReservationManager();
		FlightManager fmng = new FlightManager();
		//System.out.println(fmng.getFlights());
		
		/*
		System.out.println("Make Reservations:");
		System.out.println(rsvmng.makeReservation(f1, "Alice Liddle", "Wonderland"));
		System.out.println(rsvmng.makeReservation(f2, "Alexander Hamilton", "Wonderland"));
		*/


		/*
		System.out.println("Find Reservation by code, airline name and name:");
		ArrayList<Reservation> matchList = rsvmng.findReservations("I6602", "Conned Ai", "Alexander Hamilton");
		for (Reservation m: matchList) {
			System.out.println(m);
		}
		System.out.println("Find Reservation by code");
		System.out.println(rsvmng.findReservationByCode("D9246"));
		*/
		
		
		
		
		/*  need to change private to public for rsvmnging
		# Flight 
		System.out.println(f1.isDomestic());
		# FlightManager
		
		
		# ReservationManager
		System.out.println(rsvmng.makeReservation(f1, "Alice Liddell", "Wonderland"));
		System.out.println(rsvmng.getAvailableSeats(f1));
		System.out.println(rsvmng.generateReservationCode(f1));
		*/
		
	}

}
