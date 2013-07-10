package qianlong.musemgame;

import android.util.Log;
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
	int [] posGameStartAgain = {10, 113};
	int [] posGameConfiguration = {10, 215};
	// Rects
	Rect rectGameStartFirst;
	Rect rectGameStartAgain;
	Rect rectGameConfiguration;
	
	public MainView(CreativeMusem father) {
		super(father);
		Log.d("View", "MainView is created...");
		this.father = father;
		getHolder().addCallback(this);
		initBitmap(father);
		initRects();
		mdt = new MainDrawThread(this, getHolder());
	}
	
	public void doDraw(Canvas canvas) {
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
		Log.d("MainView", "Bitmap-Game Start First (width = " + bmpGameStartFirst.getWidth() +
				", height = " + bmpGameStartFirst.getHeight() + ")");
		rectGameStartFirst = new Rect(posGameStartFirst[0], posGameStartFirst[1],
				posGameStartFirst[0]+bmpGameStartFirst.getWidth(),
				posGameStartFirst[1]+bmpGameStartFirst.getHeight());
		Log.d("MainView", "Rect-Game Start First (left = " + rectGameStartFirst.left +
				", right = " + rectGameStartFirst.right + ", top = " + rectGameStartFirst.top +
				", bottom = " + rectGameStartFirst.bottom + ")");
		
		Log.d("MainView", "Bitmap-Game Start Again (width = " + bmpGameStartAgain.getWidth() +
				", height = " + bmpGameStartAgain.getHeight() + ")");
		rectGameStartAgain = new Rect(posGameStartAgain[0], posGameStartAgain[1],
				posGameStartAgain[0]+bmpGameStartAgain.getWidth(),
				posGameStartAgain[1]+bmpGameStartAgain.getHeight());
		Log.d("MainView", "Rect-Game Start Again (left = " + rectGameStartAgain.left +
				", right = " + rectGameStartAgain.right + ", top = " + rectGameStartAgain.top +
				", bottom = " + rectGameStartAgain.bottom + ")");
		
		Log.d("MainView", "Bitmap-Game Configuration (width = " + bmpGameConfiguration.getWidth() +
				", height = " + bmpGameConfiguration.getHeight() + ")");
		rectGameConfiguration = new Rect(posGameConfiguration[0], posGameConfiguration[1],
				posGameConfiguration[0]+bmpGameConfiguration.getWidth(),
				posGameConfiguration[1]+bmpGameConfiguration.getHeight());
		Log.d("MainView", "Rect-Game Configuration (left = " + rectGameConfiguration.left +
				", right = " + rectGameConfiguration.right + ", top = " + rectGameConfiguration.top +
				", bottom = " + rectGameConfiguration.bottom + ")");
	}
	
	public void touchEvent(int x, int y) {
		// Log touch position
		Log.d("MainView", "Touch Position = (" + x + ", " + y + ")");
		if (rectGameStartFirst.contains(x, y)) {
			Log.d("MainView", "Touch - Game Start First");
			//father.changeGameDescriptionView();
		} else if (rectGameStartAgain.contains(x, y)) {
			Log.d("MainView", "Touch - Game Start Again");
			// TODO: get previous mission ID
			father.changeGameView(1);
		} else if (rectGameConfiguration.contains(x, y)) {
			Log.d("MainView", "Touch - Game Configuration");
			//father.changeGameConfigurationView();
		} else {
			// touch other position
		}
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
