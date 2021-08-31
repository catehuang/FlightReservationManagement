package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import sait.frms.gui.ReservationsTab.*;
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
	private DefaultListModel<Reservation> reservationsModel;
	private JList<Reservation> reservationsList;
	ArrayList<Reservation> reservationsRecord;
	ArrayList<Flight> flights = new ArrayList<>();
	Reservation reservationTempRecord = null;
	TextField textCode;
	TextField textAirline;
	TextField textName;
	TextField textFlight;
	TextField textCost;
	TextField textCitizenship;
	TextField searchTextCode;
	TextField searchTextAirline;
	TextField searchTextName;
	JComboBox<String> textStatus;

	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel labelTitle = new JLabel("Search", SwingConstants.CENTER);
		labelTitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(labelTitle, BorderLayout.NORTH);

		JPanel gridbag = new JPanel();
		gridbag.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel searchLabelCode = new JLabel("Code:");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.05;
		gridbag.add(searchLabelCode, c);

		searchTextCode = new TextField();
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.95;
		gridbag.add(searchTextCode, c);

		JLabel searchLabelAirline = new JLabel("Airline:");
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.05;
		gridbag.add(searchLabelAirline, c);

		searchTextAirline = new TextField();
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.95;
		gridbag.add(searchTextAirline, c);

		JLabel searchLabelName = new JLabel("Name:");
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.05;
		gridbag.add(searchLabelName, c);

		searchTextName = new TextField();
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.95;
		gridbag.add(searchTextName, c);

		panel.add(gridbag, BorderLayout.CENTER);
		JButton findReservationBT = new JButton("Find Reservations");
		panel.add(findReservationBT, BorderLayout.SOUTH);
		findReservationBT.addActionListener(new ButtonListener());

		return panel;
	}

	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel labelTitle = new JLabel("Reserve", SwingConstants.CENTER);
		labelTitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(labelTitle, BorderLayout.NORTH);

		JPanel gridbag = new JPanel();
		gridbag.setLayout(new GridBagLayout());
		gridbag.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel labelCode = new JLabel("Code:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 0;
		gridbag.add(labelCode, c);

		textCode = new TextField(10);
		textCode.setEditable(false);
		c.gridx = 1;
		c.gridy = 0;
		gridbag.add(textCode, c);

		JLabel labelFlight = new JLabel("Flight:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		gridbag.add(labelFlight, c);

		textFlight = new TextField(10);
		textFlight.setEditable(false);
		c.gridx = 1;
		c.gridy = 1;
		gridbag.add(textFlight, c);

		JLabel labelAirline = new JLabel("Airline:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		gridbag.add(labelAirline, c);

		textAirline = new TextField(10);
		textAirline.setEditable(false);
		c.gridx = 1;
		c.gridy = 2;
		gridbag.add(textAirline, c);

		JLabel labelCost = new JLabel("Cost:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 3;
		gridbag.add(labelCost, c);

		textCost = new TextField(10);
		textCost.setEditable(false);
		c.gridx = 1;
		c.gridy = 3;
		gridbag.add(textCost, c);

		JLabel labelName = new JLabel("Name:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 4;
		gridbag.add(labelName, c);

		textName = new TextField(10);
		c.gridx = 1;
		c.gridy = 4;
		gridbag.add(textName, c);

		JLabel labelCitizenship = new JLabel("Citizenship:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 5;
		gridbag.add(labelCitizenship, c);

		textCitizenship = new TextField(10);
		c.gridx = 1;
		c.gridy = 5;
		gridbag.add(textCitizenship, c);

		JLabel labelStatus = new JLabel("Status:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 0.1;
		gridbag.add(labelStatus, c);

		textStatus = new JComboBox<>();
		textStatus.insertItemAt("Active", 0);
		textStatus.insertItemAt("Inactive", 1);
		c.gridx = 1;
		c.gridy = 6;
		gridbag.add(textStatus, c);

		panel.add(gridbag, BorderLayout.CENTER);
		JButton updateBT = new JButton("Update");
		panel.add(updateBT, BorderLayout.SOUTH);
		updateBT.addActionListener(new UpdateButtonListener());
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
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		reservationsModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationsModel);
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// User can only select one item at a time
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);// Wrap JList in JScrollPane so it is scrollable.
		reservationsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane);
		return panel;
	}

	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (reservationsList.getSelectedValue() != null) {
				textCode.setText(reservationsList.getSelectedValue().getCode());
				textFlight.setText(reservationsList.getSelectedValue().getFlightCode());
				textAirline.setText(reservationsList.getSelectedValue().getAirline());
				textCost.setText("$" + reservationsList.getSelectedValue().getCost());
				textName.setText(reservationsList.getSelectedValue().getName());
				textCitizenship.setText(reservationsList.getSelectedValue().getCitizenship());	
				
				if (reservationsList.getSelectedValue().isActive() == true) {
					textStatus.setSelectedItem("Active");
				} else {
					textStatus.setSelectedItem("Inactive");
				}				
			}
		}
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// clear fields
			reservationsRecord = new ArrayList<>();
			Reservation tmp = null;
			ArrayList<Reservation> tmplist = new ArrayList<>();
			textCode.setText("");
			textFlight.setText("");
			textAirline.setText("");
			textCost.setText("");
			textName.setText("");
			textCitizenship.setText("");
			textStatus.setSelectedItem(null);
			reservationsModel.clear();

			String searchCode = searchTextCode.getText().toUpperCase();
			String searchAirline = searchTextAirline.getText().toUpperCase();
			String searchName = searchTextName.getText().toUpperCase();

			// There are existed methods - findReservationByCode and findReservations
			if (searchCode.length() == 0 && searchAirline.length() == 0 && searchName.length() == 0) {
				// Three search items are empty
				// System.out.println("Case 1 - All empty");
				JOptionPane.showMessageDialog(null, "Please enter at least one searching information");
			} else {
				// search by code
				if (searchAirline.isEmpty() && searchName.isEmpty()) {
					tmp = reservationManager.findReservationByCode(searchCode);
					if (tmp == null) {
						// System.out.println("Case 2 - Code was incorrectly (Airline & Name were
						// empty)");
						JOptionPane.showMessageDialog(null, "No matched results. Please check input code.");
					} else {
						// System.out.println("Case 3 - Code was correctly (Airline & Name were
						// empty)");
						reservationsRecord.add(tmp);
					}
				} else {
					// search by code, airline and name
					tmplist = reservationManager.findReservations(searchCode, searchAirline, searchName);
					if (tmplist.size() == 0) {
						// System.out.println("Case 4 - Information incorrect. Searched by Code,
						// Airline, and Name");
						JOptionPane.showMessageDialog(null, "No matched results. Please check input information.");
					} else {
						reservationsRecord.addAll(tmplist);
						// System.out.println("Case 5 - Information correct. Searched by Code, Airline,
						// and Name");
					}
				}
			}

			for (Reservation r : reservationsRecord) {
				reservationsModel.addElement(r);
				// System.out.println(r);
			}
		}
	}

	private class UpdateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = textName.getText().toUpperCase();
			String citizenship = textCitizenship.getText().toUpperCase();
			Reservation target;

			if (!name.isEmpty() && !citizenship.isEmpty()) {
				target = reservationManager.findReservationByCode(textCode.getText().toUpperCase());
				target.setName(name);
				target.setCitizenship(citizenship);

				if (textStatus.getSelectedItem().equals("Active")) {
					target.setActive(true);
				} else {
					// inactive as a soft delete,
					// the cancelled reservation will not be included in the number of seats used on a flight
					target.setActive(false);
					for (Flight f : flights) {
						if (f.getCode().toUpperCase().equals(textFlight.getText().toUpperCase())) {
							f = new Flight(f.getCode(), f.getFrom(), f.getTo(), f.getWeekday(), f.getTime(),
									f.getSeats() + 1, f.getCostPerSeat());

						}
					}
				}
				try {
					// update reservation record to binary file
					reservationManager.persist();
					System.out.println("Updated Successfully!");
					JOptionPane.showMessageDialog(null, "Updated Successfully!");

					// clear fields
					reservationsList.removeAll();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Name and Citizenship can not be empty, Please enter again");
			}

		}

	}
}
