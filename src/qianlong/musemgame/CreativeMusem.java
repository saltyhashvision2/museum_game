package qianlong.musemgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);	//設定全螢幕
        getWindow().setFlags(
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN
        		);
		mainview = new MainView(this);
		//gv = new GameView(this,1);
		setContentView(mainview);
		current = mainview;
		initBitmap();		//初始化用於匹配點擊事件的矩形框
	}
    //方法：初始化矩形框
    public void initBitmap(){
    	start = BitmapFactory.decodeResource(getResources(), R.drawable.start);
    	quit = BitmapFactory.decodeResource(getResources(), R.drawable.quit);
    	config = BitmapFactory.decodeResource(getResources(), R.drawable.config);
    }
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_UP){
			int x = (int)event.getX();		//get x-coordinate of touch
			int y = (int)event.getY();		//get y-coordinate of touch				
			if(current == mainview){//如果目前介面是歡迎介面
				if(x > 205 && x < start.getWidth()+205 &&
						y > 425 && y < start.getHeight()+425 ){
					Log.d("TAG", "This is rectStart!");
				}else if(x > 205 && x < quit.getWidth()+205 &&
						y > 525 && y < start.getHeight()+525 ){
					Log.d("TAG", "This is rectQuit!");
					//System.exit(0);
				}else if(x > 25 && x < config.getWidth()+25 &&
						y > 425 && y < config.getHeight()+425 ){
					Log.d("TAG", "This is rectConfig!");
				}
			}
			else if(current == gv){
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
