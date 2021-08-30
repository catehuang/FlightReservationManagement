package sait.frms.problemdomain;

public class Flight {
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;
	static final String AIRLINE_NAME_OA = "Otto Airlines";
	static final String AIRLINE_NAME_CA = "Conned Air";
	static final String AIRLINE_NAME_TB = "Try a Bus Airways";
	static final String AIRLINE_NAME_VA = "Vertical Airways";
	
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flight(String code, String from, String to, String weekday, String time, int seats,
			double costPerSeat) {
		super();
		this.code = code;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	public String getCode() {
		return code;
	}

	public String getAirlineName() {
		parseCode(this.code);
		return airlineName;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getWeekday() {
		return weekday;
	}

	public String getTime() {
		return time;
	}

	public int getSeats() {
		return seats;
	}

	public double getCostPerSeat() {
		return costPerSeat;
	}

	public boolean isDomestic() {
		//Only the Canadian airports start with the letter Y
		if (getFrom().charAt(0) == 'Y' && getTo().charAt(0) == 'Y') {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void parseCode(String code) {
		switch(this.code.split("-")[0]) {
		case "OA":
			this.airlineName = AIRLINE_NAME_OA;
			break;
		case "CA":
			this.airlineName = AIRLINE_NAME_CA;
			break;
		case "TB":
			this.airlineName = AIRLINE_NAME_TB;
			break;
		case "VA":
			this.airlineName = AIRLINE_NAME_VA;
			break;
		default:
			// Invalid code
			break;
		}
	}
	
	@Override
	public String toString() {
		return  this.code + ", From:" + this.from + ", To:" +
				 this.to+ ", Day:" + this.weekday +", Cost:" + this.costPerSeat;
	}
}
