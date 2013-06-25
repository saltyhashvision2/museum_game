package qianlong.musemgame;

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
	Rect rectStart;				//開始按鈕的矩形框
	Rect rectQuit;				//退出按鈕的矩形框
	Rect rectConfig;			//設定按鈕的矩形框
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);	//設定全螢幕
        //getWindow().setFlags(
        //		WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //		WindowManager.LayoutParams.FLAG_FULLSCREEN
        //		);
		mainview = new MainView(this);
		//gv = new GameView(this,1);
		setContentView(mainview);
		current = mainview;
        initRects();		//初始化用於匹配點擊事件的矩形框
	}
    //方法：初始化矩形框
    public void initRects(){
    	rectStart = new Rect(205,425,295,475);//pixel 90*50
    	rectQuit = new Rect(205,525,295,575);//pixel 90*50
    	rectConfig = new Rect(25,425,61,461);//pixel 36*36
    }
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_UP){
			int x = (int)event.getX();		//get x-coordinate of touch
			int y = (int)event.getY();		//get y-coordinate of touch				
			if(current == mainview){//如果目前介面是歡迎介面
				if(rectStart.contains(x, y)){
					Log.d("TAG", "This is rectStart!");
				}else if(rectConfig.contains(x, y)){
					Log.d("TAG", "This is rectConfig!");
				}else if(rectQuit.contains(x, y)){		//按下退出鍵
					Log.d("TAG", "This is rectQuit!");
					System.exit(0);						//程式退出
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
