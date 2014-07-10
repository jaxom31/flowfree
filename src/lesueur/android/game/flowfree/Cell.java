/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lesueur.android.game.flowfree;

/**
 *
 * @author brunolesueur
 */
public class Cell 
{
    public static final int NONE = 0 ;
    public static final int NOT_SURE = 1 ;
    public static final int SURE = 2 ;
    public static final int IMPOSSIBLE = 3 ;
    
    private char value ;
    
    public Cell(char val)
    {
        this.value = val ;
    }
    public void setValue(char c)
    {
    	this.value = c ;
    }
    
    public String getValue()
    {
        if (this.value != 'f')
            return "" + this.value ;
        else
            return null ;
    }
 
}
