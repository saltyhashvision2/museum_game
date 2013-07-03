package qianlong.musemgame;

import android.util.Log;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameConfigurationView extends SurfaceView implements SurfaceHolder.Callback {
	CreativeMusem father;
	GameConfigurationDrawThread gcdt;
	
	public GameConfigurationView(CreativeMusem father) {
		super(father);
		Log.d("View", "GameConfigurationView is created...");
		this.father = father;
		getHolder().addCallback(this);
		//initBitmap(father);
		//initRects();
		gcdt = new GameConfigurationDrawThread(this, getHolder());
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
		if (!gcdt.isAlive()) {
			gcdt.start();
		}
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (gcdt.isAlive()) {
			gcdt.flag = false;
		}
	}
}
