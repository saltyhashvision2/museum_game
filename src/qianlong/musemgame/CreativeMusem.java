package qianlong.musemgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.util.Log;


public class CreativeMusem extends Activity {
	GameView gv;		//game view
	MainView mainview;  //main view
	View     current;	//current view
	Bitmap start;
	Bitmap quit;
	Bitmap config;
	boolean wantMusic = true;
	boolean wantSound = true;
	MediaPlayer mpBackGroundMusic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initBackGroundMusic(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);	//³]©w¥þ¿Ã¹õ
        getWindow().setFlags(
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN
        		);
		mainview = new MainView(this);
		//gv = new GameView(this,1);
		setContentView(mainview);
		current = mainview;
		if(wantMusic && mpBackGroundMusic!=null){
			mpBackGroundMusic.setLooping(true);
			mpBackGroundMusic.start();
		}
	}
	public void initBackGroundMusic(Context context){
		mpBackGroundMusic = MediaPlayer.create(context, R.raw.back_ground_music);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_UP){
			int x = (int)event.getX();		//get x-coordinate of touch
			int y = (int)event.getY();		//get y-coordinate of touch				
			if(current == mainview) {
				switch(mainview.view){
				case 0://MainView
					if(x > mainview.start_x && x < (mainview.start_x + mainview.bmpStart.getWidth()) &&
							y > mainview.start_y && y < (mainview.start_y + mainview.bmpStart.getHeight()) ) {
						Log.d("TAG", "Start in Main View!");
						mainview.view = 1;//change to Game Description View
					}else if(x > mainview.config_x && x < (mainview.config_x + mainview.bmpConfig.getWidth()) &&
							y > mainview.config_y && y < (mainview.config_y + mainview.bmpConfig.getHeight()) ) {
						Log.d("TAG", "Config in Main View!");
						mainview.view = 4;//change to Game Configuration View
					}else if(x > mainview.quit_x  && x < (mainview.quit_x + mainview.bmpQuit.getWidth()) &&
							y > mainview.quit_y && y < (mainview.quit_y + mainview.bmpQuit.getHeight())) {
						Log.d("TAG", "Quit in Main View!");
						System.exit(0);
					}else {
						Log.d("TAG", "else in Main View!");
					}
					break;
				case 1://Game Description View
					if(x > mainview.next_x && x < (mainview.next_x + mainview.bmpNext.getWidth()) &&
							y > mainview.next_y && y < (mainview.next_y + mainview.bmpNext.getHeight()) ) {
						Log.d("TAG", "Next in Game Description View!");
						mainview.view = 2;//change to Character Description View
					}else if(x > mainview.exit_x && x < (mainview.exit_x + mainview.bmpExit.getWidth()) &&
							y > mainview.exit_y && y < (mainview.exit_y + mainview.bmpExit.getHeight()) ) {
						Log.d("TAG", "Exit in Game Description View!");
						mainview.view = 0;//change to Main View
					}else {
						Log.d("TAG", "else in Game Description View!");
					}
					break;
				case 2://Character Description View
					if(x > mainview.next_x && x < (mainview.next_x + mainview.bmpNext.getWidth()) &&
							y > mainview.next_y && y < (mainview.next_y + mainview.bmpNext.getHeight()) ) {
						Log.d("TAG", "Next in Character Description View!");
						mainview.view = 3;//change to Game Room View
					}else if(x > mainview.exit_x && x < (mainview.exit_x + mainview.bmpExit.getWidth()) &&
							y > mainview.exit_y && y < (mainview.exit_y + mainview.bmpExit.getHeight()) ) {
						Log.d("TAG", "Exit in Character Description View!");
						mainview.view = 0;//change to Main View
					}else {
						Log.d("TAG", "else in Character Description View!");
					}
					break;
				case 3://Game Room View
					if(x > mainview.start_x && x < (mainview.start_x + mainview.bmpStart.getWidth()) &&
							y > mainview.start_y && y < (mainview.start_y + mainview.bmpStart.getHeight()) ) {
						Log.d("TAG", "Start in Game Room View!");
						//ToDo: change to game view ?
					}else if(x > mainview.exit_x && x < (mainview.exit_x + mainview.bmpExit.getWidth()) &&
							y > mainview.exit_y && y < (mainview.exit_y + mainview.bmpExit.getHeight()) ) {
						Log.d("TAG", "Exit in Game Room View!");
						mainview.view = 0;//change to Main View
					}else {
						Log.d("TAG", "else in Game Room View!");
					}
					break;
				case 4://Game Configuration View
					if(x > mainview.music_x && x < (mainview.music_x + mainview.bmpMusicOn.getWidth()) &&
							y > mainview.music_y && y < (mainview.music_y + mainview.bmpMusicOn.getHeight()) ) {
						Log.d("TAG", "On/Off Music in Game Configuration View!");
						wantMusic = !(wantMusic);
						if(!wantMusic && mpBackGroundMusic.isPlaying()){
							mpBackGroundMusic.pause();
						}else if(wantMusic && !mpBackGroundMusic.isPlaying()){
							mpBackGroundMusic.start();
						}
					}else if(x > mainview.sound_x && x < (mainview.sound_x + mainview.bmpSoundOn.getWidth()) &&
							y > mainview.sound_y && y < (mainview.sound_y + mainview.bmpSoundOn.getHeight()) ) {
						Log.d("TAG", "On/Off Sound in Game Configuration View!");
						wantSound = !(wantSound);
					}else if(x > mainview.exit_x && x < (mainview.exit_x + mainview.bmpExit.getWidth()) &&
							y > mainview.exit_y && y < (mainview.exit_y + mainview.bmpExit.getHeight()) ) {
						Log.d("TAG", "Exit in Game Configuration View!");
						mainview.view = 0;//change to Main View
					}else {
						Log.d("TAG", "This is else in Game Configuration View!");
					}
					break;
				}
			}else if(current == gv){
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
