/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lesueur.android.game.flowfree.ihm;

/**
 *
 * @author lesueurb
 */
public class Point
{
    private int x,y ;
    public Point(int x, int y)
    {
        this.x = x ;
        this.y = y ;
    }
    public int getX()
    {
        return this.x ;
    }

    public int getY()
    {
        return this.y ;
    }
    
    public double distance(Point p)
    {
        int diffX = this.x - p.getX() ;
        int diffY = this.y - p.getY() ;
        return Math.sqrt((diffX * diffX) + (diffY * diffY)) ;
    }

}
