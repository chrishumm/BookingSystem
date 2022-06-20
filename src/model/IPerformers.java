package model;

import java.util.ArrayList;


public interface IPerformers {
	public ArrayList<Performer> getAllPerformers();
	public void addPerformer(Performer c);
	public void removePerformer(Performer c);
}
