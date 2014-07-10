package lesueur.android.game.flowfree.model;
import java.util.ArrayList;


public class FindPath {
	
	private ArrayList<Grille> solutions = new ArrayList<Grille>() ;
	
	public ArrayList<Case> getCasesAdjacentes(Grille g, Case c)
	{
		ArrayList<Case> adj = new ArrayList<Case>() ;
		Case cc = g.getCaseHaut(c) ;
		if ((cc != null) )
			adj.add(cc) ;
		cc = g.getCaseBas(c) ;
		if ((cc != null) )
			adj.add(cc) ;
		cc = g.getCaseDroite(c) ;
		if ((cc != null) )
			adj.add(cc) ;
		cc = g.getCaseGauche(c) ;
		if ((cc != null))
			adj.add(cc) ;
		return adj ;
	}
	public void findPath(Grille g, Case depart, Case arrivee, Case caseCourante) throws Exception
	{
		
		ArrayList<Case> adj = this.getCasesAdjacentes(g, caseCourante) ;
		for(Case c : adj)
		{
			if (c.equals(caseCourante))
				System.out.println("ERREUR") ;
			if (c.equals(arrivee))
			{
				this.addSolution(g.copy()) ;
			}
			else
			{
				if (c.getColor() == 0)
				{
					c.setColor(depart.getColor());
					findPath( g,  depart,  arrivee,  c) ;
					c.setColor(0);
				}
			}
		}
	}
	private void addSolution(Grille copy) {
/*		for(Grille g : this.solutions)
		{
			if (copy.equals(g))
				return ;
		}*/
		this.solutions.add(copy) ;
		
	}
	public ArrayList<Grille> getSolutions()
	{
		return solutions ;
	}
	

}
