package model;


import java.util.ArrayList;


public interface IEventsList {
	public ArrayList<Event> getAllEvents();
	public void addEvent(Event e);
	public void removeEvent(Event e);
}
