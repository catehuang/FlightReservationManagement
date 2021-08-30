package sait.frms.manager;

import java.io.*;
import java.util.ArrayList;
import sait.frms.problemdomain.*;
import sait.frms.manager.*;

public class ReservationManager {

	private static final String RESERVATION_RECORDS = "res/reservation.dat";
	private ArrayList<Reservation> reservations;
	private static final String MODE = "rw";
	RandomAccessFile raf;
	final int RERSERVATION_BYTE_SIZE = 109;

	public ReservationManager() throws Exception {
		reservations = new ArrayList<Reservation>();
		populateFromBinary();
		raf = new RandomAccessFile(RESERVATION_RECORDS, MODE);
	}

	/**
	 * Make reservation
	 * @param flight the light code
	 * @param name the customer's name
	 * @param citizenship	the customer's citizenship
	 * @return	reservation object
	 * @throws IOException when the reservation records cannot be saved to binary file 
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship) throws IOException {
		// check the flight code is valid
		if (this.getAvailableSeats(flight) > 0) {
			if (name == "") {
				System.out.println("Name is required");
				return null;
			} else if (citizenship == "") {
				System.out.println("citizenship is required");
				return null;
			} else {
				// Reservation record will have Reservation code, Flight code, Airline, Name,
				// Citizenship, Cost, active
				String generatedCode = generateReservationCode(flight);
				System.out.println("Reservation created. Your code is " + generatedCode + ".");

				// Available seat - 1
				flight = new Flight(flight.getCode(), flight.getFrom(), flight.getTo(),
						flight.getWeekday(), flight.getTime(), this.getAvailableSeats(flight) - 1,
						flight.getCostPerSeat());
				Reservation rsv = new Reservation(generatedCode, flight.getCode(), name,
						citizenship, flight.getCostPerSeat(), false);

				// write reservation info to binary file
				reservations.add(rsv);
				persist();
				return rsv;
			}
		} else {
			System.out.println("This flight is not available. No available seats.");
			return null;
		}
	}

	/**
	 * A travel agent can find existing flight reservations using the reservation
	 * code, airline, and traveler name. The criteria can match any combination of
	 * the three fields.
	 * 
	 * @param code reservation code
	 * @param airline reservation airline name
	 * @param name customer's name
	 * @return found all reservation records
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) throws IOException {
		ArrayList<Reservation> findMatchReservation = new ArrayList<Reservation>();

		for (Reservation r : reservations) {
			if (r.getCode().equals(code.toUpperCase()) || r.getAirline().equals(airline.toUpperCase()) || r.getName().equals(name.toUpperCase())) {
				findMatchReservation.add(r);
				//System.out.println(r);
			}
		}
		return findMatchReservation;
	}

	/**
	 * Find reservation record by reservation code 
	 * @param code reservation code 
	 * @return found reservation object
	 */
	public Reservation findReservationByCode(String code) {
		for (Reservation r : reservations) {
			if (r.getCode().equals(code.toUpperCase())) {
				//System.out.println(r);
				return r;
			}
		}
		return null;
	}

	/**
	 * Saves all Reservation objects to a binary file
	 * @throws IOException if the file cannot be written
	 */
	public void persist() throws IOException {
		for (int i = 0; i < reservations.size(); i++) {
			String generatedCodeBinary = String.format("%-5s", reservations.get(i).getCode()); // LDDDD (i.e.: I1234) 7 bytes
																								
			raf.writeUTF(generatedCodeBinary);

			String flightcode = String.format("%-7s", reservations.get(i).getFlightCode()); // LL-DDDD (I.e.: GA-1234) 9 bytes
																							
			raf.writeUTF(flightcode);

			String name = String.format("%-50s", reservations.get(i).getName());// 52bytes
			raf.writeUTF(name);

			String citizenship = String.format("%-30s", reservations.get(i).getCitizenship());// 32bytes
			raf.writeUTF(citizenship);

			raf.writeDouble(reservations.get(i).getCost()); // 8 bytes
			raf.writeBoolean(reservations.get(i).isActive()); // 1byte
		}
	}
	
	/**
	 * Get available seats
	 * @param flight customer wants to reserved flight
	 * @return available seats
	 */
	private int getAvailableSeats(Flight flight) {
		return flight.getSeats();
	}

	/**
	 * The travel agent can make a flight reservation for a traveler. A reservation
	 * code will be generated and assigned to the traveler’s name and citizenship.
	 * The reservation code must use the format LDDDD, where L is either D for
	 * Domestic or I for International and DDDD is a random number between 1000 and
	 * 9999.
	 * 
	 * @param flight customer wants to reserved flight
	 * @return generated reservation code
	 */
	private String generateReservationCode(Flight flight) {
		String generatedCode = "";
		int number = (int) (Math.random() * 9000 + 1000);
		if (flight.getFrom().startsWith("Y") && flight.getTo().startsWith("Y")) {
			generatedCode = "D" + number;
		} else {
			generatedCode = "I" + number;
		}
		return generatedCode;
	}

	/**
	 * Read binary file which saves reservation records
	 * @throws IOException if the binary file doesn't exist
	 */
	private void populateFromBinary() throws IOException {
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(RESERVATION_RECORDS));
			boolean endOfFile = false;
			
			while (!endOfFile) {
				String generatedCodeBinary = in.readUTF().trim();
				String flightCodeBinary = in.readUTF().trim();
				String nameBinary = in.readUTF().trim();
				String citizenshipBinary = in.readUTF().trim();
				double costPerSeat = in.readDouble();
				in.readBoolean(); // boolean part
				reservations.add(new Reservation(generatedCodeBinary, flightCodeBinary, nameBinary,
						citizenshipBinary, costPerSeat, true));
			}
		} 
		catch (FileNotFoundException e) {
			// The reservation records haven't created and saved into a binary file.
		}
		catch (EOFException e) {
			// endOfFile = true;
			in.close();
		}
		
		
		// Print out reservations
		System.out.println("Populate reservation records from binary:");
		for (Reservation r: reservations) {
			System.out.println(r);
		}
		
	}

}
