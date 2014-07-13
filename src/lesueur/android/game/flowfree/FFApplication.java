/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lesueur.android.game.flowfree;

import android.app.Application;
import android.widget.TextView;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import xml.FlowPuzzles;
import xml.PuzzleDecoder;

/**
 * Cette classe va servir de receptacle pour les informations 
 * globales de l'applications
 * @author brunolesueur
 */
public class FFApplication extends Application
{
    private Grille grille ;
    private TextView tv ;
    private int helpLevel = 1 ;
    private ArrayList<ArrayList<Object>> models = new ArrayList<ArrayList<Object>>() ;
    
    private ArrayList<Cell> searchPath ;
    public ArrayList<Cell> getSearchPath() {
		return searchPath;
	}
    public void onCreate()
    {
    	ArrayList<String> xmlPuzzles = new ArrayList<String>() ;
    	xmlPuzzles.add("<flowpuzzles><flowpuzzle width=\"5\" height=\"5\"><line><colors value=\"red\"/><src x=\"0\" y=\"4\"/><dst x=\"1\" y=\"0\"/></line><line><colors value=\"green\"/><src x=\"2\" y=\"4\"/><dst x=\"1\" y=\"1\"/></line><line><colors value=\"bblue\"/><src x=\"2\" y=\"3\"/><dst x=\"2\" y=\"0\"/></line><line><colors value=\"yellow\"/><src x=\"3\" y=\"1\"/><dst x=\"4\" y=\"4\"/></line><line><colors value=\"orange\"/><src x=\"3\" y=\"0\"/><dst x=\"4\" y=\"3\"/></line></flowpuzzle><flowpuzzle width=\"5\" height=\"5\"><line><colors value=\"red\"/><src x=\"0\" y=\"0\"/><dst x=\"2\" y=\"1\"/></line><line><colors value=\"bblue\"/><src x=\"0\" y=\"1\"/><dst x=\"4\" y=\"0\"/></line><line><colors value=\"green\"/><src x=\"1\" y=\"1\"/><dst x=\"2\" y=\"2\"/></line><line><colors value=\"yellow\"/><src x=\"0\" y=\"4\"/><dst x=\"4\" y=\"1\"/></line></flowpuzzle><flowpuzzle width=\"5\" height=\"5\"><line><colors value=\"bblue\"/><src x=\"0\" y=\"0\"/><dst x=\"2\" y=\"4\"/></line><line><colors value=\"orange\"/><src x=\"2\" y=\"0\"/><dst x=\"3\" y=\"1\"/></line><line><colors value=\"green\"/><src x=\"3\" y=\"0\"/><dst x=\"3\" y=\"4\"/></line><line><colors value=\"yellow\"/><src x=\"0\" y=\"1\"/><dst x=\"1\" y=\"4\"/></line><line><colors value=\"red\"/><src x=\"2\" y=\"2\"/><dst x=\"3\" y=\"3\"/></line></flowpuzzle><flowpuzzle width=\"5\" height=\"5\"><line><colors value=\"green\"/><src x=\"0\" y=\"0\"/><dst x=\"4\" y=\"4\"/></line><line><colors value=\"bblue\"/><src x=\"1\" y=\"0\"/><dst x=\"3\" y=\"1\"/></line><line><colors value=\"yellow\"/><src x=\"2\" y=\"0\"/><dst x=\"2\" y=\"2\"/></line><line><colors value=\"red\"/><src x=\"0\" y=\"3\"/><dst x=\"3\" y=\"4\"/></line></flowpuzzle><flowpuzzle width=\"5\" height=\"5\"><line><colors value=\"red\"/><src x=\"2\" y=\"0\"/><dst x=\"3\" y=\"4\"/></line><line><colors value=\"yellow\"/><src x=\"1\" y=\"3\"/><dst x=\"4\" y=\"0\"/></line><line><colors value=\"bblue\"/><src x=\"2\" y=\"3\"/><dst x=\"4\" y=\"1\"/></line><line><colors value=\"green\"/><src x=\"3\" y=\"3\"/><dst x=\"4\" y=\"4\"/></line></flowpuzzle><flowpuzzle width=\"5\" height=\"5\"><line><colors value=\"yellow\"/><src x=\"0\" y=\"4\"/><dst x=\"4\" y=\"0\"/></line><line><colors value=\"bblue\"/><src x=\"1\" y=\"1\"/><dst x=\"2\" y=\"4\"/></line><line><colors value=\"red\"/><src x=\"4\" y=\"1\"/><dst x=\"3\" y=\"4\"/></line><line><colors value=\"green\"/><src x=\"3\" y=\"2\"/><dst x=\"4\" y=\"4\"/></line></flowpuzzle></flowpuzzles>") ;
    	xmlPuzzles.add("<flowpuzzles><flowpuzzle width=\"5\" height=\"5\">" + 
    				"<line><colors value=\"red\"/><src x=\"0\" y=\"4\"/><dst x=\"1\" y=\"0\"/></line>" + 
    				"<line><colors value=\"green\"/><src x=\"2\" y=\"4\"/><dst x=\"1\" y=\"1\"/></line>" + 
    				"<line><colors value=\"bblue\"/><src x=\"2\" y=\"3\"/><dst x=\"2\" y=\"0\"/></line>" + 
    				"<line><colors value=\"yellow\"/><src x=\"3\" y=\"1\"/><dst x=\"4\" y=\"4\"/></line>" +
    				"<line><colors value=\"orange\"/><src x=\"3\" y=\"0\"/><dst x=\"4\" y=\"3\"/></line>" +
    				"</flowpuzzle></flowpuzzles>") ;
    	xmlPuzzles.add("<flowpuzzles><flowpuzzle width=\"5\" height=\"5\">" + 
				"<line><colors value=\"red\"/><src x=\"3\" y=\"3\"/><dst x=\"4\" y=\"4\"/></line>" + 
				"<line><colors value=\"green\"/><src x=\"2\" y=\"1\"/><dst x=\"2\" y=\"3\"/></line>" + 
				"<line><colors value=\"bblue\"/><src x=\"2\" y=\"2\"/><dst x=\"4\" y=\"0\"/></line>" + 
				"<line><colors value=\"yellow\"/><src x=\"4\" y=\"1\"/><dst x=\"4\" y=\"3\"/></line>" +
				"<line><colors value=\"orange\"/><src x=\"1\" y=\"1\"/><dst x=\"1\" y=\"3\"/></line>" +
				"</flowpuzzle></flowpuzzles>") ;
    	FlowPuzzles fp  = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document doc = null ;
		for(String xml : xmlPuzzles)
		{
			try {
				db = dbf.newDocumentBuilder();
	
					doc = db.parse(new InputSource(new StringReader(xml)));
			} catch (Exception e) 
			{
				e.printStackTrace();
			} 
			if (doc != null)
			{	
				PuzzleDecoder dec = new PuzzleDecoder(doc) ;
				dec.startDecoding();
				fp = dec.getRacine() ;
			}
			for(int i = 0 ; i < fp.getPuzzles().size() ; i++)
			{
					models.add(fp.getGrille(i)) ;
			}
		}
		this.setNewGrille();
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
	public void setNewGrille() {
		Random r = new Random() ;
		int ng = r.nextInt(this.models.size()) ;
		if (this.tv != null)
			this.tv.setText("Grille " + ng);
		Grille g = new Grille(this.models.get(ng)) ;
		this.grille = g ;
		helpLevel = 1 ;
		
	}
	public void getHelp() 
	{
		if (this.helpLevel < 4)
			this.getGrille().getHelp(this.helpLevel++) ;
	}

 
}
