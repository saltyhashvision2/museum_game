package qianlong.musemgame;

import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class CreativeMusem extends Activity {
	MainView mv;
	GameDescriptionView gdv;
	CharacterDescriptionView cdv;
	GameRoomView grv;
	GameConfigurationView gcv;
	GameView gv; //game view
	ResultsView rv;
	ObjectDescriptionView odv;
	View current; //current view
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN
        		);
		mv = new MainView(this);
		setContentView(mv);
		current = mv;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getAction() == MotionEvent.ACTION_UP){
			int x = (int)event.getX();		//get x-coordinate of touch
			int y = (int)event.getY();		//get y-coordinate of touch
			if(current == mv) {
				mv.touchEvent(x,y);	//for MainView to handle touch event
			} else if (current == gdv) {
				
			} else if (current == cdv) {
				
			} else if (current == grv) {
				
			} else if (current == gcv) {
				
			} else if (current == gv) {
				gv.touchEvent(x, y);
			} else if (current == rv) {
				rv.touchEvent(x, y);
			} else if (current == odv) {
				odv.touchEvent(x, y);
			} else {
				// current is fail !
			}
		}
		
		return true;
	}
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creative_musem, menu);
		return true;
	}
	*/
	public void changeMainView() {
		mv = new MainView(this);
		this.setContentView(mv);
		this.current = mv;
	}
	
	public void changeGameDescriptionView() {
		gdv = new GameDescriptionView(this);
		this.setContentView(gdv);
		this.current = gdv;
	}
	
	public void changeGameConfigurationView() {
		gcv = new GameConfigurationView(this);
		this.setContentView(gcv);
		this.current = gcv;
	}
	
	public void changeGameView(int missionID) {
		gv = new GameView(this, missionID);
		this.setContentView(gv);
		this.current = gv;
	}
	
	public void changeResultsView(Mission mission) {
		rv = new ResultsView(this, mission);
		this.setContentView(rv);
		this.current = rv;
	}
	
	public void changeObjectDescriptionView(Mission mission) {
		odv = new ObjectDescriptionView(this, mission);
		this.setContentView(odv);
		this.current = odv;
	}
}
