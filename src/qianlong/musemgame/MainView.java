package qianlong.musemgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;	
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements SurfaceHolder.Callback {
	CreativeMusem father;
	MainDrawThread mdt;
	// Bitmaps
	Bitmap bmpGameStartFirst;
	Bitmap bmpGameStartAgain;
	Bitmap bmpGameConfiguration;
	// Positions : {left, top}
	int [] posGameStartFirst = {10, 10};
	int [] posGameStartAgain = {10, 80};
	int [] posGameConfiguration = {10, 150};
	// Rects
	Rect rectGameStartFirst;
	Rect rectGameStartAgain;
	Rect rectGameConfiguration;
	
	public MainView(CreativeMusem father) {
		super(father);
		this.father = father;
		getHolder().addCallback(this);
		initBitmap(father);
		mdt = new MainDrawThread(this, getHolder());
	}
	
	public void doDraw(Canvas canvas) {
		initRects();
		Paint paint = new Paint();
		canvas.drawBitmap(bmpGameStartFirst, posGameStartFirst[0], posGameStartFirst[1], paint);
		canvas.drawBitmap(bmpGameStartAgain, posGameStartAgain[0], posGameStartAgain[1], paint);
		canvas.drawBitmap(bmpGameConfiguration, posGameConfiguration[0], posGameConfiguration[1], paint);
	}
	
	public void initBitmap(Context context){
		Resources r = context.getResources();
		bmpGameStartFirst = BitmapFactory.decodeResource(r, R.drawable.game_start_first);
		bmpGameStartAgain = BitmapFactory.decodeResource(r, R.drawable.game_start_again);
		bmpGameConfiguration = BitmapFactory.decodeResource(r, R.drawable.game_configuration);
	}
	
	public void initRects() {
		rectGameStartFirst = new Rect(posGameStartFirst[0], posGameStartFirst[1],
				posGameStartFirst[0]+bmpGameStartFirst.getWidth(),
				posGameStartFirst[1]+bmpGameStartFirst.getHeight());
		rectGameStartAgain = new Rect(posGameStartAgain[0], posGameStartAgain[1],
				posGameStartAgain[0]+bmpGameStartAgain.getWidth(),
				posGameStartAgain[1]+bmpGameStartAgain.getHeight());
		rectGameConfiguration = new Rect(posGameConfiguration[0], posGameConfiguration[1],
				posGameConfiguration[0]+bmpGameConfiguration.getWidth(),
				posGameConfiguration[1]+bmpGameConfiguration.getHeight());
	}
	
	public void touchEvent(int x, int y) {
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!mdt.isAlive()) {
			mdt.start();
		}
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mdt.isAlive()) {
			mdt.flag = false;
		}
	}
}
