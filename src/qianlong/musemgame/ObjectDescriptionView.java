package qianlong.musemgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ObjectDescriptionView extends SurfaceView implements SurfaceHolder.Callback {
	CreativeMusem father;
	ObjectDescriptionDrawThread odt;
	Mission mission_result;
	
	int intScreenWidth = 0;
	int intScreentHeight = 0;
	
	int intHeightOfObject = 75;
	int intWidthOfAntique = 90;
	int intHeightOfAntique = 150;
	
	// String: Object description
	String strAntique = "Antique Description";
	
	// Bitmaps
	Bitmap bmpAntique;
	Bitmap bmpReturn;
	
	// Positions : {left, top}
	int [] posAntique = {10, 10};
	int [] posAntiqueDescr = {10, 500};
	int [] posReturn = {10, 715};
	// Rects
	Rect rectReturn;
	
	public ObjectDescriptionView(CreativeMusem father, Mission mission) {
		super(father);
		Log.d("View", "ObjectDescriptionView is created...");
		this.father = father;
		this.mission_result = mission;
		getHolder().addCallback(this);
		initBitmap(father);
		initRects();
		odt = new ObjectDescriptionDrawThread(this, getHolder());
	}
	
	public void doDraw(Canvas canvas) {
		Paint paint = new Paint();
		
		paint.setTextSize(28);
		paint.setColor(Color.WHITE);
		// Default x:left y:baseline
		canvas.drawText(strAntique, posAntiqueDescr[0], posAntiqueDescr[1] + intHeightOfObject, paint);
		if (bmpAntique != null) {
			canvas.drawBitmap(bmpAntique , posAntique[0], posAntique[1], paint);
		}
		//Return Button
		canvas.drawBitmap(bmpReturn, posReturn[0], posReturn[1], paint);
	}
	
	public void initBitmap(Context context){
		Resources r = context.getResources();
		bmpReturn = BitmapFactory.decodeResource(r, R.drawable.object_description_return);
		bmpAntique = BitmapFactory.decodeResource(r, R.drawable.antique1);
		bmpAntique = Bitmap.createScaledBitmap(bmpAntique, 400, 300, false);
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
			father.changeResultsView(mission_result);
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
