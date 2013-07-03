package qianlong.musemgame;

import android.util.Log;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameDescriptionView extends SurfaceView implements SurfaceHolder.Callback {
	CreativeMusem father;
	GameDescriptionDrawThread gddt;
	
	public GameDescriptionView(CreativeMusem father) {
		super(father);
		Log.d("View", "GameDescriptionView is created...");
		getHolder().addCallback(this);
		//initBitmap(father);
		//initRects();
		gddt = new GameDescriptionDrawThread(this, getHolder());
	}
	
	public void doDraw(Canvas canvas) {
		Paint paint = new Paint();
	}
	
	public void touchEvent(int x, int y) {
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!gddt.isAlive()) {
			gddt.start();
		}
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (gddt.isAlive()) {
			gddt.flag = false;
		}
	}
}
