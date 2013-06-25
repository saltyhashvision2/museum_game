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
	Rect rectStart;				//�}�l���s���x�ή�
	Rect rectQuit;				//�h�X���s���x�ή�
	Rect rectConfig;			//�]�w���s���x�ή�
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);	//�]�w���ù�
        //getWindow().setFlags(
        //		WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //		WindowManager.LayoutParams.FLAG_FULLSCREEN
        //		);
		mainview = new MainView(this);
		//gv = new GameView(this,1);
		setContentView(mainview);
		current = mainview;
        initRects();		//��l�ƥΩ�ǰt�I���ƥ󪺯x�ή�
	}
    //��k�G��l�Ưx�ή�
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
			if(current == mainview){//�p�G�ثe�����O�w�虜��
				if(rectStart.contains(x, y)){
					Log.d("TAG", "This is rectStart!");
				}else if(rectConfig.contains(x, y)){
					Log.d("TAG", "This is rectConfig!");
				}else if(rectQuit.contains(x, y)){		//���U�h�X��
					Log.d("TAG", "This is rectQuit!");
					System.exit(0);						//�{���h�X
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
