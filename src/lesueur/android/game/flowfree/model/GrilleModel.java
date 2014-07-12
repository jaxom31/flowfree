package lesueur.android.game.flowfree.model;
import java.util.ArrayList;


public class GrilleModel {

	private ArrayList<ArrayList<Case>> cases = new ArrayList<ArrayList<Case>>() ;
	private int largeur = 0 ;
	private int hauteur = 0 ;
	
	public GrilleModel(int largeur, int hauteur)
	{
		this.largeur = largeur ;
		this.hauteur = hauteur ;
		for (int i = 0 ; i < largeur ; i++)
		{
			for (int j = 0 ; j < hauteur ; j++)
			{
				this.setCase(i, j);
			}
		}
	}
	public int getLargeur() {
		return largeur;
	}
	public int getHauteur() {
		return hauteur;
	}
	private void setCase(int ligne, int colonne, int color)
	{
		ArrayList<Case> l ;
		try
		{
			l = this.cases.get(ligne) ;
		}
		catch(Exception e)
		{
			this.cases.add(ligne,new ArrayList<Case>()) ;
			l = this.cases.get(ligne) ; 
		}
		try
		{
			l.get(colonne).setColor(color);
		}
		catch(Exception e)
		{
			l.add(colonne, new Case(color, ligne, colonne)) ;
		}		
	}	
	private void setCase(int ligne, int colonne)
	{
		ArrayList<Case> l ;
		try
		{
			l = this.cases.get(ligne) ;
		}
		catch(Exception e)
		{
			this.cases.add(ligne,new ArrayList<Case>()) ;
			l = this.cases.get(ligne) ; 
		}
		try
		{
			l.get(colonne).setColor(0);
		}
		catch(Exception e)
		{
			l.add(colonne, new Case(0, ligne, colonne)) ;
		}		
	}
	
	public Case getCaseAt(int ligne, int colonne)
	{
		try
		{
			return this.cases.get(ligne).get(colonne) ;
		}
		catch(Exception e)
		{
			return null ;
		}
	}
	
	public void setColor(int ligne, int colonne, int color)
	{
		this.getCaseAt(ligne, colonne).setColor(color) ;
	}
	
	
	public String toString()
	{
		String str = "" ;
		for (int i = 0 ; i < largeur ; i++)
		{
			for (int j = 0 ; j < hauteur ; j++)
			{
				str += this.getCaseAt(i, j).getColor() ;
				str += "|" ;
			}
			
			str += "\n" ;
		}		
		return str ;
	}

	public Case getCaseHaut(Case c) {
		return this.getCaseAt(c.getI() + 1 , c.getJ() ) ;
		
	}

	public Case getCaseBas(Case c) {
		return this.getCaseAt(c.getI() - 1 , c.getJ() ) ;
	}

	public Case getCaseDroite(Case c) {
		return this.getCaseAt(c.getI()  , c.getJ() + 1 ) ;
	}

	public Case getCaseGauche(Case c) {
		return this.getCaseAt(c.getI() , c.getJ() - 1) ;
	}

	public GrilleModel copy() {
		GrilleModel g = new GrilleModel(largeur, hauteur) ;
		for (int i = 0 ; i < largeur ; i++)
		{
			for (int j = 0 ; j < hauteur ; j++)
			{
				g.setCase(i, j, this.getCaseAt(i, j).getColor());
			}
		}
		return g;
	}
	public boolean isFull() {
		for (int i = 0 ; i < largeur ; i++)
		{
			for (int j = 0 ; j < hauteur ; j++)
			{
				if (this.getCaseAt(i, j).getColor() == 0)
					return false ;
			}
		}
		return true;
	}	
	public boolean equals(GrilleModel g)
	{
		for (int i = 0 ; i < largeur ; i++)
		{
			for (int j = 0 ; j < hauteur ; j++)
			{
				if (this.getCaseAt(i, j).getColor() != g.getCaseAt(i, j).getColor())
					return false ;
			}
		}
		return true;		
	}
}
