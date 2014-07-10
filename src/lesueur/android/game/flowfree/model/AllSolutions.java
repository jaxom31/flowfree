package lesueur.android.game.flowfree.model;
import java.util.ArrayList;


public class AllSolutions {
	
	public ArrayList<Grille> solutions = new ArrayList<Grille>() ;
	
	private void addToSolution(Grille g)
	{
		for(Grille g1 : this.solutions)
		{
			if (g.equals(g1))
				return ;
		}
		this.solutions.add(g) ;		
	}
	
	private void filtreSolution(ArrayList<Grille> grilles)
	{
		double debut = System.currentTimeMillis() ;
		for(Grille g : grilles)
		{
			if (g.isFull())
				this.addToSolution(g);
		}
		System.out.println("Temps prit pas le filtrage des solutions :" + (System.currentTimeMillis() - debut) + "ms");
	}
	public void getAllSolutions(ArrayList<Grille> grilles, ArrayList<ArrayList<Case>> couples) throws Exception
	{  
		ArrayList<Grille> gg = new ArrayList<Grille>() ;
		double debut = System.currentTimeMillis() ;
		FindPath fp ;
		ArrayList<Case> cases = couples.remove(0) ;
		for(Grille g : grilles)
		{
			fp = new FindPath() ;
			fp.findPath(g, cases.get(0), cases.get(1), cases.get(0));
			if (fp.getSolutions().size() > 0)
				gg.addAll(fp.getSolutions()) ;
		}
		System.out.println("Nombre de chemins trouves : " + gg.size()) ;
		if (couples.isEmpty())
		{
			System.out.println("Fin chemins : " + (System.currentTimeMillis()  - debut) + "ms") ;
			this.filtreSolution(gg) ;
			System.out.println("Nombre de solutions : " + this.solutions.size());
		}
		else
		{	
			System.out.println("Fin chemins : " + (System.currentTimeMillis()  - debut)  + "ms") ;
			this.getAllSolutions(gg, couples) ;
		}
		
	}

}
