package lesueur.android.game.flowfree.model;

public class Case {
	private int couleur, i,j ;
	public Case(int couleur, int i, int j)
	{
		this.couleur = couleur ;
		this.setI(i) ;
		this.setJ(j) ;
	}
	/**
	 * @return the couleur
	 */
	public int getColor() {
		return couleur;
	}
	/**
	 * @param couleur the couleur to set
	 */
	public void setColor(int couleur) {
		this.couleur = couleur;
	}
	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}
	/**
	 * @param i the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}
	/**
	 * @return the j
	 */
	public int getJ() {
		return j;
	}
	/**
	 * @param j the j to set
	 */
	public void setJ(int j) {
		this.j = j;
	}
	
	public boolean equals(Case c)
	{
		return ((this.i == c.getI()) && (this.j == c.getJ()));
		
	}
}
