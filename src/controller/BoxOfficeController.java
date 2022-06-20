package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Booking;
import model.DBBookings;
import model.DBPerformer;
import model.DBEvent;
import model.Event;
import model.IBooking;
import model.IPerformers;
import model.IEventsList;
import view.GUI_Bookings;
import view.GUI_BoxOffice;


public class BoxOfficeController {

	private GUI_BoxOffice viewMain;

	public BoxOfficeController() {
		this.viewMain = new GUI_BoxOffice();

		addMainListener();
		loadEvents();
	}

	
	public void showView() {
		this.viewMain.setVisible(true);
		this.viewMain.setBtnBookingsEnabled(false);
		this.viewMain.setBtnFeedbackEnabled(false);
	}


	private void addMainListener() {
		this.viewMain.setBookingsListener(new BookingsListener());
		this.viewMain.setFeedbackListener(new FeedbackListener());
		this.viewMain.setBookingsSelectionListener(new EventsListener());
		this.viewMain.setPerformersSelectionListener(new PerformersListener());
	}


	private void loadEvents() {

		IEventsList events = new DBEvent();
		this.viewMain.setEvents(events.getAllEvents());
	}


	class BookingsListener implements ActionListener {

		private GUI_Bookings viewBookings;
		private IBooking modelBookings;


		
		public void actionPerformed(ActionEvent e) {

			modelBookings = new DBBookings();

			viewBookings = new GUI_Bookings();
			viewBookings.setVisible(true);
			addBookingsListener();
			loadBookings(viewMain.getLstEvents().getSelectedValue());

			viewBookings.setBtnRemoveEnabled(false);
		}


		private void addBookingsListener() {

			this.viewBookings.setSaveListener(new SaveListener());
			this.viewBookings.setRemoveListener(new RemoveListener());
			this.viewBookings.setClearListener(new ClearListener());
			this.viewBookings.setReturnListener(new ReturnListener());
			this.viewBookings
					.setCustomerBookingsListener(new CustomerBookingsListener());

		}

		private void loadBookings(Event e) {

			if (e != null)
				this.viewBookings.setBookings(modelBookings
						.getBookingsByEvent(e));
		}


		class SaveListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {

				if (viewBookings.getLstBookings().getSelectedValue() != null) {
					Booking selectedBooking = viewBookings.getLstBookings()
							.getSelectedValue();
					modelBookings.removeBooking(selectedBooking);

				}

				String newName = viewBookings.getName();
				int newSeats = viewBookings.getSeats();

				if (!newName.equals("") && newSeats > 0) {
					Event currentEvent = viewMain.getLstEvents()
							.getSelectedValue();

					modelBookings.addBooking(currentEvent, newSeats, newName);

					loadBookings(viewMain.getLstEvents().getSelectedValue());
				} else
					viewBookings.setErrorText("Invalid input!");

			}
		}


		class RemoveListener implements ActionListener {


			public void actionPerformed(ActionEvent e) {

				if (viewBookings.getLstBookings().getSelectedValue() != null) {
					Booking selectedBooking = viewBookings.getLstBookings()
							.getSelectedValue();
					modelBookings.removeBooking(selectedBooking);

					loadBookings(viewMain.getLstEvents().getSelectedValue());

				}
			}
		}


		class ClearListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// clear the view
				viewBookings.clearView();
			}
		}


		class ReturnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// close the window
				viewBookings.dispose();
			}
		}


		class CustomerBookingsListener implements ListSelectionListener {


			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				// get selected value
				if (viewBookings.getLstBookings().getSelectedValue() != null) {
					// Set the data in the bookings popup window
					Booking selectedBooking = viewBookings.getLstBookings()
							.getSelectedValue();
					viewBookings.setData(selectedBooking);
					viewBookings.setBtnRemoveEnabled(true);
				} else
					viewBookings.setBtnRemoveEnabled(false);

			}
		}
	}


	class FeedbackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}


	class EventsListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {

			// get selected value
			if (viewMain.getLstEvents().getSelectedValue() != null) {
				// get selected value
				Event selectedEvent = viewMain.getLstEvents()
						.getSelectedValue();

				IPerformers Performers = new DBPerformer();

				viewMain.setPerformers(selectedEvent.getPerformers());
				viewMain.updateEvendData(selectedEvent);

				viewMain.setBtnBookingsEnabled(true);
			} else
				viewMain.setBtnBookingsEnabled(false);
		}
	}


	class PerformersListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {

			if (viewMain.getLstPerformers().getSelectedValue() != null) {
				viewMain.updatePerformerData(viewMain.getLstPerformers()
						.getSelectedValue());
			}

		}
	}

}
