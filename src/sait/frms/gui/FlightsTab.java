package sait.frms.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import sait.frms.gui.FlightsTab.*;
import sait.frms.manager.*;
import sait.frms.problemdomain.*;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase {
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	/**
	 * Creates the components for flights tab.
	 */
	ArrayList<Flight> flightObject = null;
	ArrayList<String> airportObject = null;
	Reservation reservations = null;
	JComboBox<String> comboboxTo;
	JComboBox<String> comboboxFrom;
	JComboBox<String> comboboxDay;
	JPanel textArea;
	TextField textFlight;
	TextField textAirline;
	TextField textDay;
	TextField textTime;
	TextField textCost;
	TextField textName;
	TextField textCitizenship;
	JScrollPane scrollPane;

	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;

		panel.setLayout(new BorderLayout());

		panel.add(createNorthPanel(), BorderLayout.NORTH);
		panel.add(createCenterPanel(), BorderLayout.CENTER);
		panel.add(createSouthPanel(), BorderLayout.SOUTH);
		panel.add(createEastPanel(), BorderLayout.EAST);
	}

	private JPanel createSouthPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel labelTitle = new JLabel("Flight Finder", SwingConstants.CENTER);
		labelTitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(labelTitle, BorderLayout.NORTH);

		JPanel gridbag = new JPanel();
		gridbag.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel labelFrom = new JLabel("From:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.05;
		gridbag.add(labelFrom, c);

		
		String[] array = flightManager.getAirports().toArray(new String[flightManager.getAirports().size()]);
		comboboxFrom = new JComboBox<>(array);
		comboboxFrom.insertItemAt(" - ", 0);	// the first entry is empty entry
		comboboxFrom.setSelectedItem(" - ");	// default selected item
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.95;
		gridbag.add(comboboxFrom, c);

		JLabel labelTo = new JLabel("To:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.05;
		gridbag.add(labelTo, c);

		comboboxTo = new JComboBox<>(array);
		comboboxTo.insertItemAt(" - ", 0);	// the first entry is empty entry
		comboboxTo.setSelectedItem(" - ");	// default selected item
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.95;
		gridbag.add(comboboxTo, c);

		JLabel labelDay = new JLabel("Day:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.05;
		gridbag.add(labelDay, c);

		comboboxDay = new JComboBox<>(flightManager.getDayArray());
		comboboxDay.insertItemAt(" - ", 0);	// the first entry is empty entry
		comboboxDay.setSelectedItem(" - ");	// default selected item
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.95;
		gridbag.add(comboboxDay, c);

		panel.add(gridbag, BorderLayout.CENTER);
		JButton findFlightsBT = new JButton("Find Flights");
		findFlightsBT.addActionListener(new ButtonListener());
		panel.add(findFlightsBT, BorderLayout.SOUTH);

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

		JLabel labelFlight = new JLabel("Flight:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 0;
		gridbag.add(labelFlight, c);

		textFlight = new TextField(10);
		textFlight.setEditable(false);
		c.gridx = 1;
		c.gridy = 0;
		gridbag.add(textFlight, c);

		JLabel labelAirline = new JLabel("Airline:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		gridbag.add(labelAirline, c);

		textAirline = new TextField(10);
		textAirline.setEditable(false);
		c.gridx = 1;
		c.gridy = 1;
		gridbag.add(textAirline, c);

		JLabel labelDay = new JLabel("Day:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		gridbag.add(labelDay, c);

		textDay = new TextField(10);
		textDay.setEditable(false);
		c.gridx = 1;
		c.gridy = 2;
		gridbag.add(textDay, c);

		JLabel labelTime = new JLabel("Time:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 3;
		gridbag.add(labelTime, c);

		textTime = new TextField(10);
		textTime.setEditable(false);
		c.gridx = 1;
		c.gridy = 3;
		gridbag.add(textTime, c);

		JLabel labelCost = new JLabel("Cost:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 4;
		gridbag.add(labelCost, c);

		textCost = new TextField(10);
		textCost.setEditable(false);
		c.gridx = 1;
		c.gridy = 4;
		gridbag.add(textCost, c);

		JLabel labelName = new JLabel("Name:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 5;
		gridbag.add(labelName, c);

		textName = new TextField(10);
		c.gridx = 1;
		c.gridy = 5;
		gridbag.add(textName, c);

		JLabel labelCitizenship = new JLabel("Citizenship:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 6;
		gridbag.add(labelCitizenship, c);

		textCitizenship = new TextField();
		c.gridx = 1;
		c.gridy = 6;
		gridbag.add(textCitizenship, c);

		panel.add(gridbag, BorderLayout.CENTER);
		JButton reserveBT = new JButton("Reserve");
		reserveBT.addActionListener(new ReserveButtonListener());
		panel.add(reserveBT, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		textArea = new JPanel();
		textArea.setLayout(new BorderLayout());
		textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new MyListSelectionListener());
		textArea.add(scrollPane);

		return textArea;
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String flightFrom = (String) comboboxFrom.getSelectedItem();
			String flightTo = (String) comboboxTo.getSelectedItem();
			String flightDay = (String) comboboxDay.getSelectedItem();

			flightsModel.clear();
			// clear all information of flight and client information
			textFlight.setText("");
			textAirline.setText("");
			textDay.setText("");
			textTime.setText("");
			textCost.setText("");
			textName.setText("");
			textCitizenship.setText("");
			
			if (flightFrom.contains(" ") || flightTo.contains(" ") || flightDay.contains(" ")) {
				JOptionPane.showMessageDialog(null, "Please select a departure, a destination and a departure day!");
				
			} else {
				flightObject = flightManager.getFlights();		
				if(flightObject.size() == 0) {
					flightsModel.addElement(null);
				} else {
					flightsModel.addAll(flightManager.findFlights(flightFrom, flightTo, flightDay));
				}
			}
		}
	}
	
	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (flightsList.getSelectedValue() != null) {
				textFlight.setText(flightsList.getSelectedValue().getCode());
				textAirline.setText(flightsList.getSelectedValue().getAirlineName());
				textDay.setText(flightsList.getSelectedValue().getWeekday());
				textTime.setText(flightsList.getSelectedValue().getTime());
				textCost.setText("$" + flightsList.getSelectedValue().getCostPerSeat());				
			}
		}
	}

	private class ReserveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (flightsList.getSelectedValue() != null) {
				String flightCode = textFlight.getText();
				String name = textName.getText();
				String citizenship = textCitizenship.getText();
				// client name & citizenship cannot be empty
				if (!name.isEmpty() && !citizenship.isEmpty()) {
					try {
						reservations = reservationManager.makeReservation(flightsList.getSelectedValue(), name,
								citizenship);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Reservation created. Your code is " + reservations.getCode());
				} else {
					JOptionPane.showMessageDialog(null, "Name and Citizenship can not be empty, Please enter again");
				}
			}
		}
	}
}