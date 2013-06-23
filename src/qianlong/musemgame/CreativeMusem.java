package qianlong.musemgame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

public class CreativeMusem extends Activity {
	GameView gv;		//game view
	View     current;	//current view
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gv = new GameView(this,1);
		setContentView(gv);
		current = gv;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getAction() == MotionEvent.ACTION_UP){
			int x = (int)event.getX();		//get x-coordinate of touch
			int y = (int)event.getY();		//get y-coordinate of touch
			if(current == gv){
				gv.touchEvent(x,y);	//for GameView to handle touch event
			}
		}
		
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creative_musem, menu);
		return true;
	}

}
