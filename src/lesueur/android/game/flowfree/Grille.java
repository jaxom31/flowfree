/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lesueur.android.game.flowfree;

import java.util.ArrayList;
import java.util.HashMap;

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
        this.getCellAt(0,4).setValue('1') ;
        this.getCellAt(1,0).setValue('1') ;
        this.getCellAt(2,4).setValue('2') ;
        this.getCellAt(1,1).setValue('2') ; 
        this.getCellAt(2,3).setValue('3') ;
        this.getCellAt(2,0).setValue('3') ;
        this.getCellAt(3,1).setValue('4') ;
        this.getCellAt(4,4).setValue('4') ;         
        this.getCellAt(3,0).setValue('5') ;
        this.getCellAt(4,3).setValue('5') ;
        
        this.departs.add(this.getCellAt(0,4)) ;
        this.departs.add(this.getCellAt(1,0)) ;
        this.departs.add(this.getCellAt(2,4)) ;
        this.departs.add(this.getCellAt(1,1)) ; 
        this.departs.add(this.getCellAt(2,3)) ;
        this.departs.add(this.getCellAt(2,0)) ;
        this.departs.add(this.getCellAt(3,1)) ;
        this.departs.add(this.getCellAt(4,4)) ; 
        this.departs.add(this.getCellAt(3,0)) ;
        this.departs.add(this.getCellAt(4,3)) ; 
       
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
}
