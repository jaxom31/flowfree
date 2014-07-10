/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lesueur.android.game.flowfree;

import android.app.Application;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Cette classe va servir de receptacle pour les informations 
 * globales de l'applications
 * @author brunolesueur
 */
public class FFApplication extends Application
{
    private Grille grille = new Grille(5) ;
    private TextView tv ;
    private int mode = 0 ;
    
    private ArrayList<Cell> searchPath ;
    public ArrayList<Cell> getSearchPath() {
		return searchPath;
	}

	private Cell lastCell ;
    /**
     * @return the grille
     */
    public Grille getGrille() 
    {
        return grille;
    }
    /**
     * @param grille the grille to set
     */
    public void setGrille(Grille grille) 
    {
        
        this.mode = 0 ;
        this.grille = grille;
    }

    /**
     * @return the tv
     */
    public TextView getTv() {
        return tv;
    }

    /**
     * @param tv the tv to set
     */
    public void setTv(TextView tv) {
        this.tv = tv;
    }


    /**
     * @return the mode
     */
    public int getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(int mode) {
        this.mode = mode;
    }
    
    public void switchMode()
    {
        if (mode == 0)
            mode = 1 ;
        else
            mode = 0 ;
    }
        /**
     * @return the lastCell
     */
    public Point getLastCell() 
    {
        if (this.lastCell == null)
            return new Point(-1,-1) ;
        return this.grille.getPositions().get(this.lastCell);
    }

    /**
     * @param lastCell the lastCell to set
     */
    public void setLastCell(Cell lastCell) 
    {
        this.lastCell = lastCell;
    }

 
}
