/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lesueur.android.game.flowfree.ihm;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import lesueur.android.game.flowfree.FFApplication;
import lesueur.android.game.flowfree.Cell;
import lesueur.android.game.flowfree.Grille;

/**
 *
 * @author brunolesueur
 */
public class SurfaceViewFF extends SurfaceView implements SurfaceHolder.Callback
{
    private TGestionAffichage threadAffichage;
    private int cellWidth = 20 ;
    private int cellHeight = 20 ;
    private ArrayList<Cell> selected = new ArrayList<Cell>() ;
    private ArrayList<Point> points = new ArrayList<Point>() ;
    private ArrayList<ArrayList<Cell>> paths = new ArrayList<ArrayList<Cell>>() ;
    private FFApplication app = ((FFApplication)(this.getContext().getApplicationContext())) ;
    private boolean modeDelete = false;
    
    
    private void addPath()
    {
    	ArrayList<Cell> copy = new ArrayList<Cell>() ;
    	for(Cell c : selected)
    	{
    		copy.add(c) ;
    	}
    	paths.add(copy) ;
    }
    private ArrayList<Cell> isPathExistForThisVal(String val)
    {
    	for(ArrayList<Cell> path : this.paths)
    	{
    		if (path.get(0).getValue().equals(val))
    			return path ;
    	}
    	return null ;
    }
    
    private void deletePath(String val)
    {
    	ArrayList<Cell> toDelete = this.isPathExistForThisVal(val) ;
    	if (toDelete == null)
    		return ;
    	for(int i = 1 ; i < toDelete.size() - 1 ; i++)
    	{
    		toDelete.get(i).setValue(' ') ;
    	}
    	this.paths.remove(toDelete) ;
    }
    private int getColorFromFirstPoint()
    {
    	if (selected.size() == 0)
    		return Color.BLACK ;
    	return getColorFromFirstPoint(selected.get(0).getValue()) ;
    }
    private int getColorFromFirstPoint(String val)
    {
    	if (val.equals("1"))
    		return Color.RED ;
    	if (val.equals("2"))
    		return Color.BLUE ;   	
    	if (val.equals("3"))
    		return Color.MAGENTA ;
    	if (val.equals("4"))
    		return Color.GREEN ;
    	if (val.equals("5"))
    		return Color.WHITE ;    	
    	return  Color.BLACK ;
    }    
    
    public SurfaceViewFF(Context context, AttributeSet attrs) 
    {
	super(context, attrs);
        getHolder().addCallback(this);
	setFocusable(true);
    }
      
    public SurfaceViewFF(Context context) 
    {
	super(context);
        getHolder().addCallback(this);
	setFocusable(true);
    }
    
    private void drawPolyline(Canvas c, Paint p)
    {
        if (this.points.isEmpty())
            return ;
        p.setStrokeWidth(4);
        p.setColor(this.getColorFromFirstPoint()) ;
        Point old_pt = this.points.get(0) ;
        for(Point pt : this.points)
        {
            c.drawLine(old_pt.getX(), old_pt.getY(), pt.getX(), pt.getY(), p);
            old_pt = pt ;
        }
        p.reset();
    }    
    
    private void drawGrid(Canvas c, Paint p) 
    {
        Grille grille = ((FFApplication)(this.getContext().getApplicationContext())).getGrille() ;
        Cell cell ;
        for (int i = 0 ; i < grille.getTaille() ; i++)
            for(int j = 0 ; j < grille.getTaille(); j++)
            {
                try 
                {                
                    cell = grille.getCellAt(i, j) ;
                    if (!cell.getValue().equals(" "))
                    {
                        float x = (i * this.cellWidth)  ;
                        float y = (j * this.cellHeight)  ;
                        p.setColor(this.getColorFromFirstPoint( cell.getValue())) ;  
                        c.drawRect(x,y,x + cellWidth, y + cellHeight,p) ;
                    }
                } 
                catch (Exception ex) 
                {
                    Logger.getLogger(SurfaceViewFF.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } 
        p.setColor(Color.YELLOW);
    }
    
    private Cell getCellAt(float i , float j)
    {
        Grille grille = ((FFApplication)(this.getContext().getApplicationContext())).getGrille() ;
        try {
            int posx = (int)(i / this.cellWidth)  ;
            int posy = (int)(j / this.cellHeight) ;
            
            return grille.getCellAt(posx, posy) ;
            
            
        } catch (Exception ex) {
            return null ;
        }
    }
 
    private Point getCenterAt(float i , float j)
    {
       // Grille grille = ((FFApplication)(this.getContext().getApplicationContext())).getGrille() ;
        try {
            int posx = (int)(i / this.cellWidth)  ;
            int posy = (int)(j / this.cellHeight) ;
            
            return new Point((posx * this.cellWidth)+ (this.cellWidth / 2), (posy * this.cellHeight) + (this.cellHeight / 2)) ;
            
            
        } catch (Exception ex) {
            return null ;
        }
    }
    
    
    @Override
    public void draw(Canvas c) 
    {
        Paint peinture = new Paint() ;
        
        peinture.setAntiAlias(true);
        peinture.setColor(Color.WHITE) ;
        peinture.setStrokeCap(Cap.ROUND);
        peinture.setStrokeJoin(Join.ROUND);
        peinture.setStrokeWidth(4);
        c.drawColor(Color.BLACK);
        peinture.setColor(Color.WHITE) ;
        this.drawGrid(c, peinture);
        Grille grille = app.getGrille() ;
        for (int i = 0 ; i < grille.getTaille() + 1 ; i++)
        {
            c.drawLine(i * this.cellWidth, 0 , i * this.cellWidth, grille.getTaille() * this.cellHeight, peinture);
            c.drawLine(0, i * this.cellHeight, grille.getTaille() * this.cellWidth , i * this.cellHeight, peinture);
        }       
        this.drawPolyline(c, peinture);
   
    }
    /**
     * permet de trapper les mouvements du curseur (tactile ou clavier)
     * @param event : type d'evenement qui nous arrive, on ne s'occupe que d'ACTION_UP
     * @return : retourne un booleun pour signifier le traitement est effectué
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) 
    {
        float touched_x = event.getX();
        float touched_y = event.getY();
        int action = event.getAction();
        switch(action)
        {
            case MotionEvent.ACTION_DOWN:
	                points.clear() ;
	                selected.clear() ;
	                Cell c1 = this.getCellAt(touched_x, touched_y) ;
	                if (c1 != null)
	                {
	                	if (this.isPathExistForThisVal(c1.getValue()) != null)
	                	{
	                		this.modeDelete = true ;
	                		this.selected.add(c1) ;
	                	}
	                	else if (app.getGrille().isDepart(c1))
		                {
			                //app.getTv().setText("");
			                if (this.addCell(this.getCellAt(touched_x, touched_y),(int)touched_x, (int)touched_y)) ;
			                    points.add(this.getCenterAt(touched_x, touched_y)) ;
		                }
	                }
            break;
            case MotionEvent.ACTION_MOVE:
                if (!selected.isEmpty())
                {
                	if (!modeDelete)
                	{
	                    Cell c = this.getCellAt(touched_x, touched_y) ;
	                    if ((c.getValue().equals(" ") || ((c.getValue().equals(selected.get(0).getValue())))))
	                    {
		                    if (this.canSelectNextCell(c))
		                    {
		                        if (this.addCell(c,(int)touched_x,(int)touched_y))
		                        {
		                            points.add(this.getCenterAt(touched_x, touched_y)) ;
		                        }
		                    }
	                    }
                	}
                }
            break;
            case MotionEvent.ACTION_UP:
                if (!selected.isEmpty())
                {   
                	if (modeDelete)
                	{
                		this.deletePath(this.selected.get(0).getValue()) ;
                	}
                	else if (!(this.selected.get(0).getValue()).equals(this.selected.get(this.selected.size() - 1).getValue()))
	            	{	
	            	}
	            	else
	            	{
	            		this.addPath() ;
	            		char val = selected.get(0).getValue().toCharArray()[0] ;
	            		for(Cell c : selected)
	            		{
	            			c.setValue(val) ;
	            		}
	            	}	            		
                	points.clear();
            		selected.clear();
	            	this.modeDelete = false ;
                }
            break;
            case MotionEvent.ACTION_CANCEL:

            break;
            case MotionEvent.ACTION_OUTSIDE:

            break;
            default:
        }
        return true ; 
    }
    
    private boolean addCell(Cell c,int x, int y)
    {
        Cell lastCell ;
        if (c == null)
            return false ;
        int posx = (int)(x / this.cellWidth)  ;
        int posy = (int)(y / this.cellHeight) ;
        try
        {
            lastCell = this.selected.get(this.selected.size() - 1) ;
        }
        catch(Exception e)
        {
            lastCell = null ;
        }
        if (lastCell != null)
            if (app.getGrille().areAdjacent(lastCell, posx, posy))
            {
                this.selected.add(c) ;
                //app.getTv().setText(app.getTv().getText() + c.getValue());
                return true ;
            }
            else
            {
                return false ;
            }
        else
        {
                this.selected.add(c) ;
                //app.getTv().setText(app.getTv().getText() + c.getValue());
                return true ;
        }
    }

    private boolean canSelectNextCell(Cell c)
    {
        return !selected.contains(c) ;
    }
    
    public void surfaceCreated(SurfaceHolder holder)    
    {
        threadAffichage = new TGestionAffichage(getHolder(), this);
        threadAffichage.setRunning(true);
        threadAffichage.start();       
    }

    public void surfaceChanged(SurfaceHolder holder,  int format, int width,int height) 
    {
        threadAffichage = new TGestionAffichage(getHolder(), this);
        int val1 = (int)((float)height / (float)(app.getGrille().getTaille())) ;
        int val2 = (int)((float)width / (float)(app.getGrille().getTaille())) ;
        this.cellHeight = Math.min(val2, val1) ;
        this.cellWidth = this.cellHeight ;
        threadAffichage.setRunning(true);
        threadAffichage.start();        
    }

    public void surfaceDestroyed(SurfaceHolder arg0) 
    {
        boolean retry = true;
        threadAffichage.setRunning(false);
        while (retry) 
        {
            try 
            {
                threadAffichage.join();
                retry = false;
            } 
            catch (InterruptedException e) 
            {
            }
        }        
    }

    private boolean isCellTouched(int i, int j) 
    {      
        int posx = (int)(i / this.cellWidth)  ;
        int posy = (int)(j / this.cellHeight) ;
        float decX = ((float)this.cellWidth) / 3.0f ;
        float decY = ((float)this.cellHeight) / 3.0f ;
        Rect rect = new Rect((int)((posx * this.cellWidth) + decX),
                             (int)((posy * this.cellHeight) + decY),
                             (int)((posx * this.cellWidth) + (2 * decX)),
                             (int)((posy * this.cellHeight) + (2 * decY))) ;
        return rect.contains(i, j) ;
    }
    
    public void clear()
    {
        app.setGrille(new Grille(5));
     
    }
    
}
