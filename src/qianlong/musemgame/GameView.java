package qianlong.musemgame;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("ViewConstructor")
public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	int missionID;
	int questionID;		// record the question number 
	int status;			// record the game progress 
	int timeCounter;	// current time counter 
	DrawThread dt;		// draw screen thread 
	ArrayList<Rect> allCandidate = new ArrayList<Rect>(3);
	Mission mission;	// mission
	Question current;	// current question
	
	// game progress constant 
	private static final int BEFORE_QUESTION = 1;
	private static final int BEGIN_QUESTION = 2;
	private static final int AFTER_QUESTION = 3;
	
	//total question number 
	private static final int QUESTION_NUMBER = 5;
	
	//expire time between game status
	private static final int WAIT_BEFORE_QUESTION = 100;
	private static final int WAIT_AFTER_QUESTION = 100;
	private static final int WAIT_USER_CHOOSE = 300;
	
	//TextSize 
	private static final float TEXT_SIZE=16.0f;
	
	//antique's height and width
	private int antique_X;
	private int antique_Y;
    private int choiceA_X;
    private int choiceA_Y;
    private int choiceB_X;
    private int choiceB_Y;
    private int choiceC_X;
    private int choiceC_Y;
    private int question_X;
    private int question_Y;
    
	public GameView(CreativeMusem father, int missionID) {
		super(father);				
		getHolder().addCallback(this);
		mission = new Mission(missionID);
		mission.initMission(father);			
		
		initRects();
		/* init game settings */
		this.missionID = missionID;
		this.questionID = 1;
		this.status = BEFORE_QUESTION;
		
		current = mission.question.get(questionID-1);
		
		dt = new DrawThread(this, getHolder());
	}	

	/* screen drawing method */
	protected void doDraw(Canvas canvas){
		Paint paint = new Paint();		
		
		// calculate x,y coordinate 
		calculateCoordinate(canvas.getWidth(),canvas.getHeight());

		Log.d("GameView_doDraw", "mWidth = "+canvas.getWidth());
		Log.d("GameView_doDraw", "mHeight = "+canvas.getHeight());
		
		canvas.drawColor(Color.BLACK); // clear screen
		paint.setAlpha(255);
		switch(status){
			case BEFORE_QUESTION:
				//canvas.drawBitmap(bmpBackMainView, 0, 0, paint);	//draw background
				canvas.drawBitmap(current.antique.bmpAntique, antique_X, antique_Y, null);
				Log.d("GameView_doDraw", "timeCounter"+timeCounter);
				timeCounter++;
				if(timeCounter == WAIT_BEFORE_QUESTION){
					Log.d("GameView_doDraw", "BEFORE -> BEGIN");
					status = BEGIN_QUESTION;
					timeCounter = 0;
				}
				break;
			case BEGIN_QUESTION:	
				canvas.drawBitmap(current.antique.bmpAntique, antique_X, antique_Y, null);			
				// for debug, draw touch rect area
				paint.reset();
				paint.setAlpha(30);
				paint.setColor(Color.RED);
				canvas.drawRect(allCandidate.get(Question.CHOICE_A),paint);
				paint.reset();
				paint.setAlpha(30);
				paint.setColor(Color.GREEN);
				canvas.drawRect(allCandidate.get(Question.CHOICE_B),paint);
				paint.reset();
				paint.setAlpha(30);
				paint.setColor(Color.YELLOW);
				canvas.drawRect(allCandidate.get(Question.CHOICE_C),paint);
				
				paint.reset();
				paint.setColor(Color.WHITE);
				paint.setTextSize(dipToPx(TEXT_SIZE));
				canvas.drawText(current.question, question_X, question_Y, paint);        //drawQuestionID();
				canvas.drawText(current.candidate.get(Question.CHOICE_A), choiceA_X, choiceA_Y, paint);
				canvas.drawText(current.candidate.get(Question.CHOICE_B), choiceB_X, choiceB_Y, paint);
				canvas.drawText(current.candidate.get(Question.CHOICE_C), choiceC_X, choiceC_Y, paint);
				
				Log.d("GameView_doDraw", "timeCounter"+timeCounter);
				timeCounter++;
				if(current.choice != Question.NO_CHOICE){
					Log.d("GameView_doDraw", "BEGIN -> AFTER");
					status = AFTER_QUESTION;
					timeCounter = 0;
				}
				
				if(timeCounter == WAIT_USER_CHOOSE){
					Log.d("GameView_doDraw","User don't choose any answer");
					Log.d("GameView_doDraw", "BEGIN -> AFTER");
					status = AFTER_QUESTION;
					timeCounter = 0;
					current.choice = Question.CHOICE_A;
				}
					
				break;
			case AFTER_QUESTION:
				canvas.drawBitmap(current.antique.bmpAntique, antique_X, antique_Y, null);
				paint.reset();
				paint.setColor(Color.WHITE);
				paint.setTextSize(dipToPx(TEXT_SIZE));
				canvas.drawText(current.question,question_X,question_Y,paint);        //drawQuestionID();
				//this should be drawed according to answer's position
				switch(current.choice){
					case Question.CHOICE_A:
					    //for debug, paint touch area
						paint.reset();
						paint.setAlpha(30);
						paint.setColor(Color.RED);
						canvas.drawRect(allCandidate.get(Question.CHOICE_A),paint);
						paint.reset();
						paint.setColor(Color.WHITE);
						paint.setTextSize(dipToPx(TEXT_SIZE));
						canvas.drawText(current.candidate.get(Question.CHOICE_A), choiceA_X, choiceA_Y, paint);
						break;
					case Question.CHOICE_B:
						//for debug, paint touch area
						paint.reset();
						paint.setAlpha(30);
						paint.setColor(Color.GREEN);
						canvas.drawRect(allCandidate.get(Question.CHOICE_B),paint);
						paint.reset();
						paint.setColor(Color.WHITE);
						paint.setTextSize(dipToPx(TEXT_SIZE));
						canvas.drawText(current.candidate.get(Question.CHOICE_B), choiceB_X, choiceB_Y, paint);
						break;
					case Question.CHOICE_C:
						//for debug, paint touch area
						paint.reset();
						paint.setAlpha(30);
						paint.setColor(Color.YELLOW);
						canvas.drawRect(allCandidate.get(Question.CHOICE_C),paint);
						paint.reset();
						paint.setColor(Color.WHITE);
						paint.setTextSize(dipToPx(TEXT_SIZE));
						canvas.drawText(current.candidate.get(Question.CHOICE_C), choiceC_X, choiceC_Y, paint);
						break;
				}
				
				Log.d("GameView_doDraw", "timeCounter"+timeCounter);
				timeCounter++;
				if(timeCounter == WAIT_AFTER_QUESTION){
					timeCounter = 0;
					if(questionID == QUESTION_NUMBER){
						Log.d("GameView_doDraw","end of question");
						dt.isGameOn = false;
						//calculate the total score 
						mission.calculateTotalScore();
						Log.d("GameView","Total score="+mission.score);
					}
					else{
						questionID++;
						Log.d("GameView_doDraw","question "+questionID);
						current = mission.question.get(questionID-1);
						Log.d("GameView_doDraw", "AFTER -> BEFORE");
						status = BEFORE_QUESTION;
					}
				}	
				break;
		}
	}
	
	//create empty touch area
	private void initRects(){		
		allCandidate.add(Question.CHOICE_A, new Rect());
		allCandidate.add(Question.CHOICE_B, new Rect());
		allCandidate.add(Question.CHOICE_C, new Rect()); 	
    }
	
	//calcuate object coordinate when doRraw
	private void calculateCoordinate(int width, int height){
		
		//calculate bitmap coordinate
		int antiqueWidth = current.antique.bmpAntique.getWidth();
		int antiqueHeight = current.antique.bmpAntique.getHeight();
		antique_X = (width/2-antiqueWidth)/2;
		antique_Y = (height/2-antiqueHeight)/2;
		choiceA_X = width/2;
		choiceA_Y = height/4;
		choiceB_X = width/2;
		choiceB_Y = (height*2)/4;
		choiceC_X = width/2;
		choiceC_Y = (height*3)/4;
		question_X = 0;
		question_Y = (height*3)/4;
		//configure touch area range
		allCandidate.get(Question.CHOICE_A).set(width/2,0,width,height/3);
		allCandidate.get(Question.CHOICE_B).set(width/2,height/3,width,(height*2)/3);
		allCandidate.get(Question.CHOICE_C).set(width/2,(height*2)/3,width,height);
	}
	
	
	// Handle onTouchEvent in main activity
	public void touchEvent(int x, int y){
		Log.d("GameView","touch event");
		// user choose answer A
		if(allCandidate.get(Question.CHOICE_A).contains(x, y)){
			Log.d("GameView", "touch answer A");
			current.choice = Question.CHOICE_A;
		}
		
		// user choose answer B
		if(allCandidate.get(Question.CHOICE_B).contains(x, y)){
			Log.d("GameView", "touch answer B");
			current.choice = Question.CHOICE_B;
		}
		
		// user choose answer C
		if(allCandidate.get(Question.CHOICE_C).contains(x, y)){
			Log.d("GameView", "touch answer C");
			current.choice = Question.CHOICE_C;
		}
	}
	
	private int dipToPx(float dip){
		final float scale = getResources().getDisplayMetrics().density;
		return (int)(dip * scale + 0.5f);
	}
		
	@Override
	protected void finalize() throws Throwable {
		System.out.println("############ FieldView  is dead##########");
		super.finalize();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {		
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if(!dt.isAlive()){
			dt.start();
		}			 
        //father.pmt = new PlayerMoveThread(father);//初始化並啟動球員的移動處理執行緒
        //father.pmt.start();
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		dt.isGameOn = false;	//停止更新螢幕執行緒的執行
		//father.pmt.flag = false;
	}
}
