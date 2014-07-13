/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lesueur.android.game.flowfree;

import java.util.ArrayList;
import java.util.HashMap;

import lesueur.android.game.flowfree.model.AllSolutions;
import lesueur.android.game.flowfree.model.Case;
import lesueur.android.game.flowfree.model.GrilleModel;


/**
 *
 * @author brunolesueur
 */
public class Grille 
{
    private Cell[][] grille ;
    private HashMap<Cell,Point> positions  = new HashMap<Cell,Point>();
    private int taille ;
    private ArrayList<Cell> departs = new ArrayList<Cell>() ;
    private ArrayList<ArrayList<Cell>> paths = new ArrayList<ArrayList<Cell>>() ;
    private GrilleModel solution ;
    
    public ArrayList<ArrayList<Cell>> getPaths() {
		return paths;
	}
	public boolean hasWon()
    {
    	for (int i = 0 ; i < this.taille ; i++)
    		for (int j = 0 ; j < this.taille ; j++)
    			if (this.getCellAt(i,j).getValue().equals(" "))
    					return false ;
    	return true ;
    }
    public boolean isDepart(Cell c)
    {
    	return departs.contains(c) ;
    }
    private void initGrille()
    {
        getPositions().clear();
        for(int i = 0 ; i < grille.length ; i++)
            for(int j = 0 ; j < grille[i].length ; j++)   
            {
                grille[i][j] = new Cell(' ') ;
                getPositions().put(grille[i][j], new Point(i,j)) ;
            }
    }
    
   
    public Grille(int taille)
    {
        positions.clear();
        this.taille = taille ;
        grille = new Cell[taille][taille] ;
        this.initGrille();
       
       
    }
    public ArrayList<Cell> getAjacentCells(Cell c)
    {
        Point p = this.getPositions().get(c) ;
        return this.getAdjacentCells(p.getX(),p.getY()) ;
    }
    
    public ArrayList<Cell> getAdjacentCells(int i, int j)
    {
        Cell c ; 
        ArrayList<Cell> adjacent = new ArrayList<Cell>() ;
        c = this.getCellAt(i-1, j) ;
        if (c != null)
            adjacent.add(c);
        c = this.getCellAt(i, j - 1) ;
        if (c != null)
            adjacent.add(c);        
        c = this.getCellAt(i, j + 1) ;
        if (c != null)
            adjacent.add(c);       
        c = this.getCellAt(i+1, j) ;
        if (c != null)
            adjacent.add(c);
        
        return adjacent ;
        
    }
    
    public Grille()
    {   
        this.taille = 5 ;
        grille = new Cell[5][5] ;
       
    }

    public Grille(ArrayList<Object> defGrille) 
    {
		GrilleModel g = (GrilleModel)(defGrille.get(0)) ;
		this.taille = g.getHauteur() ;
		grille = new Cell[g.getHauteur()][g.getLargeur()] ;
		this.initGrille() ;
        for(int i = 0 ; i < grille.length ; i++)
            for(int j = 0 ; j < grille[i].length ; j++)   
            {
            	Case c = g.getCaseAt(i, j) ;
            	if (c.getColor() == 0)
            		grille[i][j].setValue(' ') ;
            	else if (c.getColor() == 1)
            		grille[i][j].setValue('1') ;
            	else if (c.getColor() == 2)
            		grille[i][j].setValue('2') ;
            	else if (c.getColor() == 3)
            		grille[i][j].setValue('3') ;
            	else if (c.getColor() == 4)
            		grille[i][j].setValue('4') ;
            	else if (c.getColor() == 5)
            		grille[i][j].setValue('5') ;
            	else if (c.getColor() == 6)
            		grille[i][j].setValue('6') ;
            	else if (c.getColor() == 7)
            		grille[i][j].setValue('7') ;
            	else
            		grille[i][j].setValue(' ') ;
            }
			ArrayList<ArrayList<Case>> couples0 = (ArrayList<ArrayList<Case>>)(defGrille.get(1)) ;
			ArrayList<ArrayList<Case>> couples = new ArrayList<ArrayList<Case>>() ;
			for(ArrayList<Case> couple : couples0)
			{
				couples.add(couple) ;
			}
			for(ArrayList<Case> couple : couples)
			{
				Case c1 = couple.get(0) ;
				Case c2 = couple.get(1) ; 
				this.departs.add(grille[c1.getI()][c1.getJ()]) ;
				this.departs.add(grille[c2.getI()][c2.getJ()]) ;
			}
			ArrayList<GrilleModel> grilles = new ArrayList<GrilleModel>() ;
			grilles.add(g) ;
			AllSolutions sol = new AllSolutions() ;
			try
			{
				sol.getAllSolutions(grilles, couples);
			}
			catch(Exception e)
			{
				
			}
			if (sol.solutions.size() > 0)
				solution = sol.solutions.get(0) ;
			else
				solution = null ;
			
	}
	public Cell getCellAt(int i, int j) 
    {
        try
        {
            return grille[i][j] ;    
        }
        catch(Exception e)
        {
            return null ;
        }
        
    }

    /**
     * @return the taille
     */
    public int getTaille() 
    {
        return taille;
    }
    
    public boolean areAdjacent(Cell c1, int i, int j)
    {
        Cell c ;
        c = this.getCellAt(i-1, j) ;
        if (c == c1)
            return true ;
        c = this.getCellAt(i, j-1) ;
        if (c == c1)
            return true ;
        c = this.getCellAt(i, j+1) ;
        if (c == c1)
            return true ;
        c = this.getCellAt(i+1, j) ;       
        if (c == c1)
            return true ;
        return false ;
    }

    /**
     * @return the positions
     */
    public HashMap<Cell,Point> getPositions() {
        return positions;
    }
    
    public void getHelp(int niveau)
    {	
    	String path = "0" ;
    	if (niveau > 3)
    		return ;
    	if (niveau == 1)
    		path = "1" ;
    	if (niveau == 2)
    		path = "2" ;
    	if (niveau == 3)
    		path = "3";
    	this.removeAllCell(path) ;
    	this.setPath(niveau, path.toCharArray()[0]) ;
    }
    private void setPath(int numPath, char path)
    {
    	ArrayList<Cell> cellPath = new ArrayList<Cell>() ;
    	if (solution != null)
    	{
        for(int i = 0 ; i < grille.length ; i++)
            for(int j = 0 ; j < grille[i].length ; j++)   
            {
            	if (this.solution.getCaseAt(i, j).getColor() == numPath)
            	{
            		this.getCellAt(i, j).setValue(path) ;
            		cellPath.add(getCellAt(i, j)) ;
            	}
            }   	
        this.paths.add(cellPath) ;
    	}
    }
    
	private void removeAllCell(String path) {
        for(int i = 0 ; i < grille.length ; i++)
            for(int j = 0 ; j < grille[i].length ; j++)   
            {
            	if (this.getCellAt(i, j).getValue().equals(path))
            		if (!this.departs.contains(this.getCellAt(i, j)))
            			this.getCellAt(i, j).setValue(' ') ;
            }
		
	}
}
