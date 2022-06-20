package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Event implements Serializable{

	 private int id;
	 private String name;
	 private float price;
	 private String desc;
	 private int minAge;
	 private Timestamp date;
	 private int seats;
	 private ArrayList<Performer> Performers;



	public Event(int id, String name, float price, String desc, int minAge, Timestamp date, int seats, ArrayList<Performer> Performers)
	 {
		 super();
		 this.id = id;
		 this.name=name;
		 this.price = price;
		 this.desc=desc;
		 this.minAge=minAge;
		 this.date=date;
		 this.seats=seats;
		 this.Performers=Performers;
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	 public ArrayList<Performer> getPerformers() {
		return Performers;
	}

	public void setPerformers(ArrayList<Performer> Performers) {
		this.Performers = Performers;
	}

	public void addPerformer(Performer Performer) {
		this.Performers.add(Performer);
	}


	@Override
	public String toString() {
		
		return name;
	}
	 
	 
}
