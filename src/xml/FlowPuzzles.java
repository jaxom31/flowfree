package xml;

import java.util.ArrayList;

import lesueur.android.game.flowfree.model.Case;
import lesueur.android.game.flowfree.model.GrilleModel;


public class FlowPuzzles {
	public ArrayList<FlowPuzzle> getPuzzles() {
		return puzzles;
	}

	public void setPuzzles(ArrayList<FlowPuzzle> puzzles) {
		this.puzzles = puzzles;
	}
	private int getIntFromStringColor(String color)
	{
		if (color.equals("red"))
			return 1 ;
		if (color.equals("green"))
			return 2 ;
		if (color.equals("bblue"))
			return 3 ;
		if (color.equals("yellow"))
			return 4 ;
		if (color.equals("orange"))
			return 5 ;		
		if (color.equals("white"))
			return 6 ;
		if (color.equals("black"))
			return 7 ;
		if (color.equals("brown"))
			return 8 ;
		return 0 ;
	}
		
	public ArrayList<Object> getGrille(int index)
	{
		FlowPuzzle fp = this.puzzles.get(index) ;
		ArrayList<ArrayList<Case>> couples = new ArrayList<ArrayList<Case>>() ; 
		GrilleModel g = new GrilleModel(fp.getWidth(),fp.getHeight()) ;
		
		for(Line line : fp.getLines())
		{	
			ArrayList<Case> couple = new ArrayList<Case>() ;
			Case src =  g.getCaseAt(line.getySrc(), line.getxSrc()) ;
			Case dest = g.getCaseAt(line.getyDest(), line.getxDest()) ;
			src.setColor(this.getIntFromStringColor(line.getColor()));
			dest.setColor(this.getIntFromStringColor(line.getColor()));
			couple.add(src) ;
			couple.add(dest) ;
			couples.add(couple) ;
		}
		ArrayList<Object> reponse = new ArrayList<Object>() ;
		reponse.add(g) ;
		reponse.add(couples) ;
		return reponse ;
	}
	
	
	private ArrayList<FlowPuzzle> puzzles = new ArrayList<FlowPuzzle>() ;
}
