package qianlong.musemgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ResultsView extends SurfaceView implements SurfaceHolder.Callback {
	CreativeMusem father;
	ResultsDrawThread rdt;
	// String
	String strCollectedPic = "Collected Pic";
	String strMissedPic = "Missed Pic";
	// Bitmaps
	Bitmap [] bmpCollectedPics;
	Bitmap [] bmpMissedPics;
	Bitmap bmpButtonHome;
	Bitmap bmpButtonPlayAgain;
	// Positions : {left, top}
	int [] posButtonHome = {};
	int [] posButtonPlayAgain = {};
	// Rects
	Rect [] rectCollectedPics;
	Rect [] rectMissedPics;
	Rect rectButtonHome;
	Rect rectButtonPlayAgain;
	
	public ResultsView(CreativeMusem father) {
		super(father);
		Log.d("View", "ResultsView is created...");
		this.father = father;
		getHolder().addCallback(this);
		initBitmap(father);
		initRects();
		rdt = new ResultsDrawThread(this, getHolder());
	}
	
	public void doDraw(Canvas canvas) {
		Paint paint = new Paint();
		//canvas.drawColor(Color.WHITE);
		paint.setTextSize(28);
		paint.setColor(Color.RED);
		//paint.setTextAlign(Paint.Align.LEFT);
		canvas.drawText(strCollectedPic, 50, 50, paint);
		canvas.drawText(strMissedPic, 50, 500, paint);
	}
	
	public void initBitmap(Context context){
		
	}
	
	public void initRects() {
		
	}
	
	public void touchEvent(int x, int y) {
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!rdt.isAlive()) {
			rdt.start();
		}
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (rdt.isAlive()) {
			rdt.flag = false;
		}
	}
}
