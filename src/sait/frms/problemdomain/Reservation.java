package sait.frms.problemdomain;

public class Reservation {
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;
	static final String AIRLINE_NAME_OA = "Otto Airlines";
	static final String AIRLINE_NAME_CA = "Conned Air";
	static final String AIRLINE_NAME_TB = "Try a Bus Airways";
	static final String AIRLINE_NAME_VA = "Vertical Airways";
	
	public Reservation() {
		super();
	}

	public Reservation(String code, String flightCode, String name, String citizenship, double cost,
			boolean active) {
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
		this.airline = getAirline();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCitizenship() {
		return this.citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Get reservation code
	 * @return reservation code
	 */
	public String getCode() {
		return this.code;
	}

	public String getFlightCode() {
		return this.flightCode;
	}

	public String getAirline() {
		return parseCode(this.code);
	}
	
	private String parseCode(String code) {
		switch(this.flightCode.split("-")[0]) {
		case "OA":
			return AIRLINE_NAME_OA;
		case "CA":
			return AIRLINE_NAME_CA;
		case "TB":
			return AIRLINE_NAME_TB;

		case "VA":
			return AIRLINE_NAME_VA;
		default:
			return null;
		}
	}
	
	public double getCost() {
		return this.cost;
	}

	@Override
	public String toString() {
		return "Reservation Code=" + this.code + ", FlightCode=" + this.flightCode + ", Airline=" + this.airline + ", name=" + this.name
				+ ", Citizenship=" + this.citizenship + ", Cost=" + String.format("%-6.2f", this.cost) + ", isActive=" + this.active;
	}
	
}
