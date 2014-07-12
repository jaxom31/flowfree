package lesueur.android.game.flowfree.model;
import java.util.ArrayList;


public class AllSolutions {
	
	public ArrayList<GrilleModel> solutions = new ArrayList<GrilleModel>() ;
	
	private void addToSolution(GrilleModel g)
	{
		for(GrilleModel g1 : this.solutions)
		{
			if (g.equals(g1))
				return ;
		}
		this.solutions.add(g) ;		
	}
	
	private void filtreSolution(ArrayList<GrilleModel> grilles)
	{
		for(GrilleModel g : grilles)
		{
			if (g.isFull())
				this.addToSolution(g);
		}
	}
	public void getAllSolutions(ArrayList<GrilleModel> grilles, ArrayList<ArrayList<Case>> couples) throws Exception
	{  
		ArrayList<GrilleModel> gg = new ArrayList<GrilleModel>() ;
		FindPath fp ;
		ArrayList<Case> cases = couples.remove(0) ;
		for(GrilleModel g : grilles)
		{
			fp = new FindPath() ;
			fp.findPath(g, cases.get(0), cases.get(1), cases.get(0));
			if (fp.getSolutions().size() > 0)
				gg.addAll(fp.getSolutions()) ;
		}
		if (couples.isEmpty())
		{
			this.filtreSolution(gg) ;
		}
		else
		{	
			this.getAllSolutions(gg, couples) ;
		}
		
	}

}
