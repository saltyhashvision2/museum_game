package qianlong.musemgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ResultsView extends SurfaceView implements SurfaceHolder.Callback {
	public ResultsView(CreativeMusem father) {
		super(father);
		getHolder().addCallback(this);
	}
	
	public void doDraw(Canvas canvas) {
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
}
