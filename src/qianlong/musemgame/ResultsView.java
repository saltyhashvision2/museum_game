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

public class ResultsView extends SurfaceView implements SurfaceHolder.Callback {
	CreativeMusem father;
	ResultsDrawThread rdt;
	int intScreenWidth = 0;
	int intScreentHeight = 0;
	int intCollectedPicNumber = 0;
	int intMissedPicNumber = 0;
	// Custom Layout
	// TODO: According to screen resolution to calculate
	int intGapBetweenObject = 5;
	int intHeightOfObject = 75;
	int intWidthOfAntique = 90;
	int intHeightOfAntique = 150;
	// String
	String strMission = "Mission";
	String strScore = "Score";
	String strCollectedPic = "Collected Pic";
	String strMissedPic = "Missed Pic";
	// Bitmaps
	Bitmap bmpMissionResults;
	Bitmap [] bmpCollectedPics;
	Bitmap [] bmpMissedPics;
	Bitmap bmpButtonPlayAgain;
	Bitmap bmpButtonPlayNext;
	Bitmap bmpButtonHome;
	// Positions : {left, top}
	// TODO: According to screen resolution to calculate
	int [] posBmpMissionResults = {5, 5};
	int [] posStrMission = {5, 85};
	int [] posStrScore = {240, 85};
	int [] posStrCollectedPic = {5, 165};
	int [] posBmpCollectedPics = {5, 245}; // First antique
	int [] posStrMissedPic = {5, 400};
	int [] posBmpMissedPics = {5, 480}; // First antique
	int [] posButtonPlayAgain = {5, 635};
	int [] posButtonPlayNext = {240, 635};
	int [] posButtonHome = {5, 715};
	// Size : {width, height}
	int [] sizeBmpMissionResults = {470, 75};
	int [] sizeButtonPlayAgain = {230, 75};
	int [] sizeButtonPlayNext = {230, 75};
	int [] sizeButtonHome = {470, 75};
	// Rects
	Rect [] rectCollectedPics;
	Rect [] rectMissedPics;
	Rect rectButtonPlayAgain;
	Rect rectButtonPlayNext;
	Rect rectButtonHome;
	
	public ResultsView(CreativeMusem father) {
		// TODO: Input Mission
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
		
		intScreenWidth = canvas.getWidth();
		intScreentHeight = canvas.getHeight();
		
		canvas.drawColor(Color.WHITE);
		
		canvas.drawBitmap(bmpMissionResults, posBmpMissionResults[0], posBmpMissionResults[1], paint);
		
		paint.setTextSize(28);
		paint.setColor(Color.RED);
		// TODO: Print mission id & score
		// Default x:left y:baseline
		canvas.drawText(strMission, posStrMission[0], posStrMission[1] + intHeightOfObject, paint);
		canvas.drawText(strScore, posStrScore[0], posStrScore[1] + intHeightOfObject, paint);
		canvas.drawText(strCollectedPic, posStrCollectedPic[0], posStrCollectedPic[1] + intHeightOfObject, paint);
		canvas.drawText(strMissedPic, posStrMissedPic[0], posStrMissedPic[1] + intHeightOfObject, paint);
		
		// Draw collected pic
		if (bmpCollectedPics != null) {
			for (int collected_pic_index = 0 ; collected_pic_index < intCollectedPicNumber ; collected_pic_index++) {
				canvas.drawBitmap(bmpCollectedPics[collected_pic_index], posBmpCollectedPics[0]+(collected_pic_index*(intWidthOfAntique+intGapBetweenObject)), posBmpCollectedPics[1], paint);
			}
		}
		
		// Draw missed pic
		if (bmpMissedPics != null) {
			for (int missed_pic_index = 0 ; missed_pic_index < intMissedPicNumber ; missed_pic_index++) {
				canvas.drawBitmap(bmpMissedPics[missed_pic_index], posBmpMissedPics[0]+(missed_pic_index*(intWidthOfAntique+intGapBetweenObject)), posBmpMissedPics[1], paint);
			}
		}
		
		canvas.drawBitmap(bmpButtonPlayAgain, posButtonPlayAgain[0], posButtonPlayAgain[1], paint);
		canvas.drawBitmap(bmpButtonPlayNext, posButtonPlayNext[0], posButtonPlayNext[1], paint);
		canvas.drawBitmap(bmpButtonHome, posButtonHome[0], posButtonHome[1], paint);
	}
	
	public void initBitmap(Context context){
		Resources r = context.getResources();
		bmpMissionResults = BitmapFactory.decodeResource(r, R.drawable.mission_results);
		bmpMissionResults = Bitmap.createScaledBitmap(bmpMissionResults, sizeBmpMissionResults[0], sizeBmpMissionResults[1], false);
		// TODO: Read mission results
		intCollectedPicNumber = 5;
		bmpCollectedPics = new Bitmap[intCollectedPicNumber];
		bmpCollectedPics[0] = BitmapFactory.decodeResource(r, R.drawable.antique1);
		bmpCollectedPics[0] = Bitmap.createScaledBitmap(bmpCollectedPics[0], intWidthOfAntique, intHeightOfAntique, false);
		bmpCollectedPics[1] = BitmapFactory.decodeResource(r, R.drawable.antique2);
		bmpCollectedPics[1] = Bitmap.createScaledBitmap(bmpCollectedPics[1], intWidthOfAntique, intHeightOfAntique, false);
		bmpCollectedPics[2] = BitmapFactory.decodeResource(r, R.drawable.antique3);
		bmpCollectedPics[2] = Bitmap.createScaledBitmap(bmpCollectedPics[2], intWidthOfAntique, intHeightOfAntique, false);
		bmpCollectedPics[3] = BitmapFactory.decodeResource(r, R.drawable.antique4);
		bmpCollectedPics[3] = Bitmap.createScaledBitmap(bmpCollectedPics[3], intWidthOfAntique, intHeightOfAntique, false);
		bmpCollectedPics[4] = BitmapFactory.decodeResource(r, R.drawable.antique5);
		bmpCollectedPics[4] = Bitmap.createScaledBitmap(bmpCollectedPics[4], intWidthOfAntique, intHeightOfAntique, false);
		/*
		for (int collected_pic_index = 0 ; collected_pic_index < intCollectedPicNumber ; collected_pic_index++) {
			// TODO: Read antique name ?
			bmpCollectedPics[collected_pic_index] = BitmapFactory.decodeResource(r, R.drawable.);
		}
		*/
		intMissedPicNumber = 5;
		bmpMissedPics = new Bitmap[intMissedPicNumber];
		bmpMissedPics[0] = BitmapFactory.decodeResource(r, R.drawable.antique6);
		bmpMissedPics[0] = Bitmap.createScaledBitmap(bmpMissedPics[0], intWidthOfAntique, intHeightOfAntique, false);
		bmpMissedPics[1] = BitmapFactory.decodeResource(r, R.drawable.antique7);
		bmpMissedPics[1] = Bitmap.createScaledBitmap(bmpMissedPics[1], intWidthOfAntique, intHeightOfAntique, false);
		bmpMissedPics[2] = BitmapFactory.decodeResource(r, R.drawable.antique8);
		bmpMissedPics[2] = Bitmap.createScaledBitmap(bmpMissedPics[2], intWidthOfAntique, intHeightOfAntique, false);
		bmpMissedPics[3] = BitmapFactory.decodeResource(r, R.drawable.antique9);
		bmpMissedPics[3] = Bitmap.createScaledBitmap(bmpMissedPics[3], intWidthOfAntique, intHeightOfAntique, false);
		bmpMissedPics[4] = BitmapFactory.decodeResource(r, R.drawable.antique10);
		bmpMissedPics[4] = Bitmap.createScaledBitmap(bmpMissedPics[4], intWidthOfAntique, intHeightOfAntique, false);
		/*
		for (int missed_pic_index = 0 ; missed_pic_index < intMissedPicNumber ; missed_pic_index++) {
			
		}
		*/
		bmpButtonPlayAgain = BitmapFactory.decodeResource(r, R.drawable.play_again);
		bmpButtonPlayAgain = Bitmap.createScaledBitmap(bmpButtonPlayAgain, sizeButtonPlayAgain[0], sizeButtonPlayAgain[1], false);
		bmpButtonPlayNext = BitmapFactory.decodeResource(r, R.drawable.play_next);
		bmpButtonPlayNext = Bitmap.createScaledBitmap(bmpButtonPlayNext, sizeButtonPlayNext[0], sizeButtonPlayNext[1], false);
		bmpButtonHome = BitmapFactory.decodeResource(r, R.drawable.results_home);
		bmpButtonHome = Bitmap.createScaledBitmap(bmpButtonHome, sizeButtonHome[0], sizeButtonHome[1], false);
	}
	
	public void initRects() {
		// TODO: Read mission results
		intCollectedPicNumber = 5;
		rectCollectedPics = new Rect[intCollectedPicNumber];
		for (int collected_pic_index = 0 ; collected_pic_index < intCollectedPicNumber ; collected_pic_index++) {
			rectCollectedPics[collected_pic_index] = new Rect(
					posBmpCollectedPics[0]+(collected_pic_index*(intWidthOfAntique+intGapBetweenObject)),
					posBmpCollectedPics[1],
					posBmpCollectedPics[0]+(collected_pic_index*(intWidthOfAntique+intGapBetweenObject))+intWidthOfAntique,
					posBmpCollectedPics[1]+intHeightOfAntique);
		}
		intMissedPicNumber = 5;
		rectMissedPics = new Rect[intMissedPicNumber];
		for (int missed_pic_index = 0 ; missed_pic_index < intMissedPicNumber ; missed_pic_index++) {
			rectMissedPics[missed_pic_index] = new Rect(
					posBmpMissedPics[0]+(missed_pic_index*(intWidthOfAntique+intGapBetweenObject)),
					posBmpMissedPics[1],
					posBmpMissedPics[0]+(missed_pic_index*(intWidthOfAntique+intGapBetweenObject))+intWidthOfAntique,
					posBmpMissedPics[1]+intHeightOfAntique);
		}
		rectButtonPlayAgain = new Rect(posButtonPlayAgain[0], posButtonPlayAgain[1],
				posButtonPlayAgain[0]+sizeButtonPlayAgain[0],
				posButtonPlayAgain[1]+sizeButtonPlayAgain[1]);
		rectButtonPlayNext = new Rect(posButtonPlayNext[0], posButtonPlayNext[1],
				posButtonPlayNext[0]+sizeButtonPlayNext[0],
				posButtonPlayNext[1]+sizeButtonPlayNext[1]);
		rectButtonHome = new Rect(posButtonHome[0], posButtonHome[1],
				posButtonHome[0]+sizeButtonHome[0],
				posButtonHome[1]+sizeButtonHome[1]);
	}
	
	public void touchEvent(int x, int y) {
		// Log touch position
		Log.d("ResultsView", "Touch Position = (" + x + ", " + y + ")");
		if (rectButtonPlayAgain.contains(x, y)) {
			Log.d("ResultsView", "Touch - Play Again");
			// TODO: the same mission ID
			father.changeGameView();
		} else if (rectButtonPlayNext.contains(x, y)) {
			Log.d("ResultsView", "Touch - Play Next");
			// TODO: next mission ID
			father.changeGameView();
		} else if (rectButtonHome.contains(x, y)) {
			Log.d("ResultsView", "Touch - Home");
			father.changeMainView();
		} else {
			// Check collected pics
			for (int collected_pic_index = 0 ; collected_pic_index < intCollectedPicNumber ; collected_pic_index++) {
				if (rectCollectedPics[collected_pic_index].contains(x, y)) {
					father.changeObjectDescriptionView();
					break;
				}
			}
			// Check missed pics
			for (int missed_pic_index = 0 ; missed_pic_index < intMissedPicNumber ; missed_pic_index++) {
				if (rectMissedPics[missed_pic_index].contains(x, y)) {
					father.changeObjectDescriptionView();
					break;
				}
			}
		}
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
