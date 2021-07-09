package sait.frms.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import sait.frms.manager.*;
import sait.frms.problemdomain.*;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	private JList<Reservation> reservationsList;
	
	private DefaultListModel<Reservation> reservationsModel;
	
	private String searchCode;
	private String searchAirline;
	private String searchName;
	private String reserveName;
	private String reserveCitizenship;
	private String reserveStatus;
	
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel labelTitle = new JLabel("Search", SwingConstants.CENTER);
		labelTitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(labelTitle, BorderLayout.NORTH);
		
		JPanel gridbag = new JPanel();
		gridbag.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel labelCode = new JLabel("Code:");
		c.gridx = 0;
		c.gridy = 0;
		gridbag.add(labelCode, c);
		
		JTextField textCode = new JTextField(50);
		c.gridx = 1;
		c.gridy = 0;
		gridbag.add(textCode, c);
		
		JLabel labelAirline = new JLabel("Airline:");
		c.gridx = 0;
		c.gridy = 1;
		gridbag.add(labelAirline, c);
		
		JTextField textAirline = new JTextField(50);
		c.gridx = 1;
		c.gridy = 1;
		gridbag.add(textAirline, c);
		
		JLabel labelName = new JLabel("Name:");
		c.gridx = 0;
		c.gridy = 2;
		gridbag.add(labelName, c);
		
		JTextField textName = new JTextField(50);
		c.gridx = 1;
		c.gridy = 2;
		gridbag.add(textName, c);
		
		panel.add(gridbag, BorderLayout.CENTER);
		panel.add(new JButton("Find Reservations"), BorderLayout.SOUTH);

		return panel;
	}

	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		
		JLabel labelTitle = new JLabel("Reserve", SwingConstants.CENTER);
		labelTitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(labelTitle, BorderLayout.NORTH);
		
		JPanel gridbag = new JPanel();
		gridbag.setLayout(new GridBagLayout());
		gridbag.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel labelCode = new JLabel("Code:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 0;
		gridbag.add(labelCode, c);
		
		TextField textFlight = new TextField(10);
		textFlight.setEditable(false);
		c.gridx = 1;
		c.gridy = 0;
		gridbag.add(textFlight, c);
		
		JLabel labelFlight = new JLabel("Flight:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		gridbag.add(labelFlight, c);
		
		TextField textAirline = new TextField(10);
		textAirline.setEditable(false);
		c.gridx = 1;
		c.gridy = 1;
		gridbag.add(textAirline, c);
		
		JLabel labelAirline = new JLabel("Airline:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		gridbag.add(labelAirline, c);
		
		TextField textDay = new TextField(10);
		textDay.setEditable(false);
		c.gridx = 1;
		c.gridy = 2;
		gridbag.add(textDay, c);
		
		JLabel labelCost = new JLabel("Cost:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 3;
		gridbag.add(labelCost, c);
		
		TextField textTime = new TextField(10);
		textTime.setEditable(false);
		c.gridx = 1;
		c.gridy = 3;
		gridbag.add(textTime, c);

		JLabel labelName = new JLabel("Name:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 4;
		gridbag.add(labelName, c);
		
		TextField textCost = new TextField(10);
		c.gridx = 1;
		c.gridy = 4;
		gridbag.add(textCost, c);
		
		JLabel labelCitizenship = new JLabel("Citizenship:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 5;
		gridbag.add(labelCitizenship, c);
		
		TextField textName = new TextField(10);
		c.gridx = 1;
		c.gridy = 5;
		gridbag.add(textName, c);
		
		JLabel labelStatus= new JLabel("Status:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 6;
		gridbag.add(labelStatus, c);
		
		JComboBox textStatus = new JComboBox();
		c.gridx = 1;
		c.gridy = 6;
		gridbag.add(textStatus, c);
		
		panel.add(gridbag, BorderLayout.CENTER);
		
		panel.add(new JButton("Update"), BorderLayout.SOUTH);

		return panel;
	}
	
	
	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		
		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);
		
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		
		reservationsModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationsModel);
		
		// User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);
		
		reservationsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane);
		
		return panel;
		
	}	
	
	
	public ReservationManager getReservationManager() {
		return reservationManager;
	}

	public void setReservationManager(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
	}

	public JList<Reservation> getReservationsList() {
		return reservationsList;
	}

	public void setReservationsList(JList<Reservation> reservationsList) {
		this.reservationsList = reservationsList;
	}

	public DefaultListModel<Reservation> getReservationsModel() {
		return reservationsModel;
	}

	public void setReservationsModel(DefaultListModel<Reservation> reservationsModel) {
		this.reservationsModel = reservationsModel;
	}

	public String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	public String getSearchAirline() {
		return searchAirline;
	}

	public void setSearchAirline(String searchAirline) {
		this.searchAirline = searchAirline;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getReserveName() {
		return reserveName;
	}

	public void setReserveName(String reserveName) {
		this.reserveName = reserveName;
	}

	public String getReserveCitizenship() {
		return reserveCitizenship;
	}

	public void setReserveCitizenship(String reserveCitizenship) {
		this.reserveCitizenship = reserveCitizenship;
	}

	public String getReserveStatus() {
		return reserveStatus;
	}

	public void setReserveStatus(String reserveStatus) {
		this.reserveStatus = reserveStatus;
	}


	private class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
		}
		
	}
}
