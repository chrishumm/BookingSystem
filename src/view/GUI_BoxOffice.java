package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import model.Performer;
import model.Event;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class GUI_BoxOffice extends JFrame {


	private static final long serialVersionUID = -8617657665399466269L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private JPanel contentPane;
	private JLabel lblHead;

	private JPanel pnlTop;

	private JLabel lblEvents;
	private JList<Event> lstEvents;

	private JPanel pnlEventDetails;
	private JPanel pnlButtons;
	private JPanel pnlData;

	private JLabel lblEventName;
	private JLabel lblEventDate;
	private JLabel lblEventDesc;
	private JLabel lblEventSeats;
	private JLabel lblEventAge;
	private JButton btnBookings;
	private JButton btnFeedback;

	private JPanel pnlBottom;

	private JList<Performer> lstPerformers;

	private JPanel pnlPerformerDetails;
	private JLabel lblPerformerName;
	private JLabel lblPerformerDesc;


	public GUI_BoxOffice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		// TOP
		lblHead = new JLabel("Comedy Venue - Box Office");
		this.setTitle("Booking System - Chris Humm");

		// TOP LEFT
		pnlTop = new JPanel();
		pnlTop.setLayout(new BoxLayout(pnlTop, BoxLayout.X_AXIS));


		lstEvents = new JList<Event>();
		JScrollPane spEvents = new JScrollPane(lstEvents);
		spEvents.setMaximumSize(new Dimension(500, 600));
		spEvents.setBorder(BorderFactory.createTitledBorder("Events"));

		// TOP RIGHT
		pnlEventDetails = new JPanel();
		pnlEventDetails.setBorder(BorderFactory
				.createTitledBorder("Event details"));

		
		pnlEventDetails.setLayout(new BorderLayout());
		pnlEventDetails.setMaximumSize(new Dimension(500, 600));

		lblEventName = new JLabel("NAME");
		lblEventName.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		lblEventDate = new JLabel("DATE");
		lblEventDate.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		lblEventDesc = new JLabel("DESCRIPTION");
		lblEventDesc.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		lblEventSeats = new JLabel("SEATS");
		lblEventSeats
				.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		lblEventAge = new JLabel("AGE");
		lblEventAge.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		pnlButtons = new JPanel();
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));

		btnBookings = new JButton("Bookings");
		btnFeedback = new JButton("Feedback");

		pnlButtons.add(btnBookings);
		pnlButtons.add(btnFeedback);

		pnlData = new JPanel();
		pnlData.setLayout(new BoxLayout(pnlData, BoxLayout.Y_AXIS));

		pnlData.add(lblEventName);
		pnlData.add(lblEventDate);
		pnlData.add(lblEventDesc);
		pnlData.add(lblEventSeats);
		pnlData.add(lblEventAge);

		pnlEventDetails.add(pnlData, BorderLayout.CENTER);
		pnlEventDetails.add(pnlButtons, BorderLayout.SOUTH);

		pnlTop.add(spEvents);
		pnlTop.add(pnlEventDetails);

		// BOTTOM
		pnlBottom = new JPanel();
		pnlBottom.setLayout(new BoxLayout(pnlBottom, BoxLayout.X_AXIS));

		// LEFT
		lstPerformers = new JList<Performer>();
		JScrollPane spPerformers = new JScrollPane(lstPerformers);
		spPerformers.setMaximumSize(new Dimension(500, 200));
		spPerformers.setBorder(BorderFactory.createTitledBorder("Performers"));

		// RIGHT
		pnlPerformerDetails = new JPanel();
		pnlPerformerDetails.setBorder(BorderFactory
				.createTitledBorder("Performer details"));
		pnlPerformerDetails.setLayout(new BoxLayout(pnlPerformerDetails,
				BoxLayout.Y_AXIS));
		pnlPerformerDetails.setMaximumSize(new Dimension(500, 200));

		lblPerformerName = new JLabel("Performer");
		lblPerformerName.setBorder(BorderFactory.createEmptyBorder(10, 20, 10,
				10));

		lblPerformerDesc = new JLabel("DESCRIPTION");
		lblPerformerDesc.setBorder(BorderFactory.createEmptyBorder(10, 20, 10,
				10));

		pnlPerformerDetails.add(lblPerformerName);
		pnlPerformerDetails.add(lblPerformerDesc);

		// PerformerS
		pnlBottom.add(spPerformers);
		pnlBottom.add(pnlPerformerDetails);

		contentPane.add(pnlTop);
		contentPane.add(pnlBottom);

	}


	public void setEvents(ArrayList<Event> e) {

		if (e != null) {
			lstEvents.setListData(e.toArray(new Event[0]));
		}

	}

	public void updateEvendData(Event e) {

		if (e != null) {
			lblEventName.setText(e.getName());
			lblEventDate.setText(dateFormat.format(e.getDate()));
			lblEventDesc.setText(e.getDesc());
			lblEventSeats.setText(Integer.toString(e.getSeats())
					+ " total seats.");
			lblEventAge.setText("Ages " + Integer.toString(e.getMinAge())
					+ " and up.");
		}

	}


	public void setPerformers(ArrayList<Performer> c) {

		if (c != null) {
			lstPerformers.setListData(c.toArray(new Performer[0]));
		}
	}


	public void updatePerformerData(Performer c) {

		if (c != null) {
			lblPerformerName.setText(c.getName());
			lblPerformerDesc.setText(c.getDesc());
		}

	}


	public void setBookingsListener(ActionListener l) {
		this.btnBookings.addActionListener(l);
	}

	public void setFeedbackListener(ActionListener l) {
		this.btnFeedback.addActionListener(l);
	}

	public void setBookingsSelectionListener(ListSelectionListener l) {
		this.lstEvents.addListSelectionListener(l);
	}

	public void setPerformersSelectionListener(ListSelectionListener l) {
		this.lstPerformers.addListSelectionListener(l);
	}

	public JList<Event> getLstEvents() {
		return lstEvents;
	}

	public void setLstEvents(JList<Event> lstEvents) {
		this.lstEvents = lstEvents;
	}

	public JList<Performer> getLstPerformers() {
		return lstPerformers;
	}

	public void setLstPerformers(JList<Performer> lstPerformers) {
		this.lstPerformers = lstPerformers;
	}

	public void setBtnBookingsEnabled(boolean b) {
		btnBookings.setEnabled(b);
	}

	public void setBtnFeedbackEnabled(boolean b) {
		btnFeedback.setEnabled(b);
	}

}
