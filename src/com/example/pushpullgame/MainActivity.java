package com.example.pushpullgame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.DrawableContainer;
import android.view.InputEvent;

public class MainActivity extends Activity {

	AnimationDrawable frameAnimation;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = (ImageView)findViewById(R.id.imageView1);
        img.setBackgroundResource(R.drawable.approach_animation);
        frameAnimation = (AnimationDrawable) img.getBackground();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onWindowFocusChanged(boolean hasFocus) {
    	super.onWindowFocusChanged(hasFocus);
    	if (hasFocus)
    		frameAnimation.start();
    }
    
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//        	frameAnimation.start();
//        	return true;
//        }
//        return super.onTouchEvent(event);
//    }
}
