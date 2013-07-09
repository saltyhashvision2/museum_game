package qianlong.musemgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ObjectDescriptionView extends SurfaceView implements SurfaceHolder.Callback {
	CreativeMusem father;
	ObjectDescriptionDrawThread odt;
	// Bitmaps
	Bitmap bmpReturn;
	// Positions : {left, top}
	int [] posReturn = {10, 10};
	// Rects
	Rect rectReturn;
	
	public ObjectDescriptionView(CreativeMusem father) {
		super(father);
		Log.d("View", "ObjectDescriptionView is created...");
		this.father = father;
		getHolder().addCallback(this);
		initBitmap(father);
		initRects();
		odt = new ObjectDescriptionDrawThread(this, getHolder());
	}
	
	public void doDraw(Canvas canvas) {
		Paint paint = new Paint();
		canvas.drawBitmap(bmpReturn, posReturn[0], posReturn[1], paint);
	}
	
	public void initBitmap(Context context){
		Resources r = context.getResources();
		bmpReturn = BitmapFactory.decodeResource(r, R.drawable.object_description_return);
	}
	
	//initialize touch rectangular range 
	public void initRects(){
		rectReturn = new Rect(posReturn[0], posReturn[1],
				posReturn[0]+bmpReturn.getWidth(),
				posReturn[1]+bmpReturn.getHeight());
    }
	
	public void touchEvent(int x, int y) {
		// Log touch position
		Log.d("ObjectDescriptionView", "Touch Position = (" + x + ", " + y + ")");
		if (rectReturn.contains(x, y)) {
			Log.d("ObjectDescriptionView", "Touch - Return");
			father.changeResultsView();
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!odt.isAlive()) {
			odt.start();
		}
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (odt.isAlive()) {
			odt.flag = false;
		}
	}
}
