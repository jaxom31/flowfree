package lesueur.android.game.flowfree;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import lesueur.android.game.flowfree.R;
import lesueur.android.game.flowfree.ihm.SurfaceViewFF;

public class FFActivity extends Activity 
{
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        FFApplication app ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv = (TextView)(this.findViewById(R.id.shaked));
        tv.setText("") ;
        app = ((FFApplication)(this.getApplicationContext())) ;
        app.setTv(tv);
        
        
    }
        
    @Override
    protected void onResume() 
    {
        super.onResume();
       // FFApplication app = ((FFApplication)(this.getApplicationContext())) ;
       
       
    }

    @Override
    protected void onPause() 
    {
        super.onPause();
        //FFApplication app = ((FFApplication)(this.getApplicationContext())) ;
        
    }

   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        //Intent serverIntent ;
        FFApplication app = ((FFApplication)(getApplicationContext())) ;
        switch (item.getItemId()) 
        {
        case R.id.reset:
        	app.getTv().setText("") ;
        	app.setNewGrille() ;
        	SurfaceViewFF sv = (SurfaceViewFF)(this.findViewById(R.id.plateau));
        	sv.changeRatio();
            return true; 
        case R.id.help:
        	app.getTv().setText("help") ;
        	app.getHelp() ;
            return true;   
        case R.id.exit:
        	moveTaskToBack(true);
        	android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        return false;
    }
    
}
