package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.problemdomain.*;

public class FlightManager {
	private final String FLIGHTS_CSV_PATH = "res/flights.csv";
	private final String AIRPORTS_CSV_PATH = "res/airports.csv";
	static final String WEEKDAY_ANY = "Any";
	static final String WEEKDAY_SUNDAY = "Sunday";
	static final String WEEKDAY_MONDAY = "Monday";
	static final String WEEKDAY_TUESDAY = "Tuesday";
	static final String WEEKDAY_WEDNESDAY = "Wendesday";
	static final String WEEKDAY_THURSDAY = "Thursday";
	static final String WEEKDAY_FRIDAY = "Friday";
	static final String WEEKDAY_SATURDAY = "Staturday";

	private ArrayList<Flight> flights;
	private ArrayList<String> airports;
	
	public FlightManager() throws FileNotFoundException {
		flights = new ArrayList<>();
		airports = new ArrayList<>();
		populateFlights();
		populateAirports();
	}

	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public ArrayList<String> getAirports() {
		return airports;
	}

	public String findAirportByCode(String code) {
		for (int i = 0; i < airports.size(); i++) {
			if (airports.get(i).equals(code)) {
				return airports.get(i+1);
			}
		}
		return "";
	}

	public Flight findFlightByCode(String code) {
		for (Flight f: flights) {
			if (f.getCode().equals(code)) {
				return f;
			}
		}
		return null;
	}
	
	/**
	 * The method returns an ArrayList of any matching Flight objects. If no matches are found, the list control will be empty.
	 * @param from		the originating airport
	 * @param to		the destination airport
	 * @param weekday	the day of week
	 * @return			an ArrayList of any matching Flight objects.
	 */
	/* Flights tab*/
	public ArrayList<Flight> findFlights (String from, String to, String weekday) {
		ArrayList<Flight> foundFlights = new ArrayList<Flight>();
		
		for (Flight f : flights) {
			if (f.getFrom().equals(from) && f.getTo().equals(to)) {
				if (weekday.equals(WEEKDAY_ANY)) {
					foundFlights.add(new Flight(f.getCode(), f.getFrom(), f.getTo(), f.getWeekday(), f.getTime(), f.getSeats(), f.getCostPerSeat()));
				}
				else if (f.getWeekday().equals(weekday)){
					foundFlights.add(new Flight(f.getCode(), f.getFrom(), f.getTo(), f.getWeekday(), f.getTime(), f.getSeats(), f.getCostPerSeat()));
				}
			}
		}
		return foundFlights;
	}
	
	private void populateFlights() throws FileNotFoundException {
		Scanner in = new Scanner(new File(FLIGHTS_CSV_PATH));
		while (in.hasNext()) {
			String[] fields = in.nextLine().split(",");
			String code = fields[0];
			
			/* Filtering possible airlines */
			//String[] possibleFlights = {"OA", "CA", "TB", "VA"};
			//for (String s : possibleFlights) {
				//if (code.startsWith(s)) {
					String from = fields[1];
					String to = fields[2];
					String weekday = fields[3];
					String time = fields[4];
					int seats = Integer.parseInt(fields[5]);
					double costPerSeat = Double.parseDouble(fields[6]);
					flights.add(new Flight(code, from, to, weekday, time, seats, costPerSeat));					
				//}
			//}
		}
	}
	
	private void populateAirports() throws FileNotFoundException {
		Scanner in = new Scanner(new File(AIRPORTS_CSV_PATH));
		while (in.hasNext()) {
			String[] fields = in.nextLine().split(",");
			String code = fields[0];
			airports.add(code);
			//String airport = fields[1];
			//airports.add(airport);
		}
	}
	
	public String [] getDayArray() {
		String [] day = {WEEKDAY_ANY, WEEKDAY_SUNDAY, WEEKDAY_MONDAY, WEEKDAY_TUESDAY, WEEKDAY_WEDNESDAY, WEEKDAY_THURSDAY, WEEKDAY_FRIDAY, WEEKDAY_SATURDAY};
		return day;
	}
}
