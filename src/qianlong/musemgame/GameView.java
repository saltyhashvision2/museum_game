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
	//view's height and width
	private int mWidth;
	private int mHeight;
	//antique's height and width
	private int antiqueWidth;
	private int antiqueHeight;
	private int antique_X;
	private int antique_Y;
    private int choiceA_X;
    private int choiceA_Y;
    private int choiceB_X;
    private int choiceB_Y;
    private int choiceC_X;
    private int choiceC_Y;
    
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
		//this.father = father;
		//this.clubID = clubID;		//獲得俱樂部logo		
		//ball = new Ball(this);		//新建足球執行緒	
		//initPlayerInstance();		//初始化雙方隊員		
		//initGame();					//初始化遊戲		
		//initBitmap(father);			//初始化圖片資源			
		
		//ait = new AIThread(this);	//新建AI分析執行緒		
		//bm = new BonusManager(this);//初始化BonusManager		
		//dt = new DrawThread(this,getHolder());//新建後臺更新螢幕執行緒
	}	

	/* screen drawing method */
	protected void doDraw(Canvas canvas){
		Paint paint = new Paint();		
		
		// calculate x,y coordinate 
		mWidth = canvas.getWidth();
		mHeight = canvas.getHeight();
		antiqueWidth = current.antique.bmpAntique.getWidth();
		antiqueHeight = current.antique.bmpAntique.getHeight();
		antique_X = (mWidth/2-antiqueWidth)/2;
		antique_Y = (mHeight/2-antiqueHeight)/2;
		choiceA_X = mWidth/2;
		choiceA_Y = mHeight/4;
		choiceB_X = mWidth/2;
		choiceB_Y = (mHeight*2)/4;
		choiceC_X = mWidth/2;
		choiceC_Y = (mHeight*3)/4;
		
		Log.d("GameView", "mWidth = "+mWidth);
		Log.d("GameView", "mHeight = "+mHeight);
		
		canvas.drawColor(Color.BLACK); // clear screen
		paint.setAlpha(255);
		switch(status){
			case BEFORE_QUESTION:
				//canvas.drawBitmap(bmpBackMainView, 0, 0, paint);	//draw background
				canvas.drawBitmap(current.antique.bmpAntique, antique_X, antique_Y, null);
				Log.d("GameView", "timeCounter"+timeCounter);
				timeCounter++;
				if(timeCounter == WAIT_BEFORE_QUESTION){
					Log.d("GameView", "BEFORE -> BEGIN");
					status = BEGIN_QUESTION;
					timeCounter = 0;
				}
				break;
			case BEGIN_QUESTION:	
				canvas.drawBitmap(current.antique.bmpAntique, antique_X, antique_Y, null);
				paint.setColor(Color.WHITE);
				paint.setTextSize(dipToPs(TEXT_SIZE));
				canvas.drawText(current.question, 0, (mHeight*3)/4, paint);        //drawQuestionID();
				canvas.drawText(current.candidate.get(Question.CHOICE_A), choiceA_X, choiceA_Y, paint);
				canvas.drawText(current.candidate.get(Question.CHOICE_B), choiceB_X, choiceB_Y, paint);
				canvas.drawText(current.candidate.get(Question.CHOICE_C), choiceC_X, choiceC_Y, paint);
				Log.d("GameView", "timeCounter"+timeCounter);
				timeCounter++;
				if(current.choice != 0){
					Log.d("GameView", "BEGIN -> AFTER");
					status = AFTER_QUESTION;
					timeCounter = 0;
				}
				
				if(timeCounter == WAIT_USER_CHOOSE){
					Log.d("GameView","User don't choose any answer");
					Log.d("GameView", "BEGIN -> AFTER");
					status = AFTER_QUESTION;
					timeCounter = 0;
					current.choice = Question.CHOICE_A;
				}
					
				break;
			case AFTER_QUESTION:
				canvas.drawBitmap(current.antique.bmpAntique, antique_X, antique_Y, null);
				paint.setColor(Color.WHITE);
				paint.setTextSize(dipToPs(TEXT_SIZE));
				canvas.drawText(current.question,0,(mHeight*3)/4,paint);        //drawQuestionID();
				//this should be drawed according to answer's position
				switch(current.choice){
					case Question.CHOICE_A:
						canvas.drawText(current.candidate.get(Question.CHOICE_A), choiceA_X, choiceA_Y, paint);
						break;
					case Question.CHOICE_B:
						canvas.drawText(current.candidate.get(Question.CHOICE_B), choiceB_X, choiceB_Y, paint);
						break;
					case Question.CHOICE_C:
						canvas.drawText(current.candidate.get(Question.CHOICE_A), choiceC_X, choiceC_Y, paint);
						break;
				}
				
				Log.d("GameView", "timeCounter"+timeCounter);
				timeCounter++;
				if(timeCounter == WAIT_AFTER_QUESTION){
					timeCounter = 0;
					if(questionID == QUESTION_NUMBER){
						Log.d("GameView","end of question");
						dt.isGameOn = false;
					}
					else{
						questionID++;
						Log.d("GameView","question "+questionID);
						current = mission.question.get(questionID-1);
						Log.d("GameView", "AFTER -> BEFORE");
						status = BEFORE_QUESTION;
					}
				}	
				break;
		}
	}
	
	//initialize touch rectangular range 
	public void initRects(){
		Rect rectA = new Rect(224, 240, 280, 276);
		Rect rectB = new Rect(224, 280, 280, 316);
		Rect rectC = new Rect(224, 320, 280, 356);
		
		allCandidate.add(Question.CHOICE_A, rectA);
		allCandidate.add(Question.CHOICE_B, rectB);
		allCandidate.add(Question.CHOICE_C, rectC); 	
    }
	
	// Handle onTouchEvent in main activity
	public void touchEvent(int x, int y){
		// user choose answer A
		if(allCandidate.get(Question.CHOICE_A).contains(x, y)){
			current.choice = Question.CHOICE_A;
		}
		
		// user choose answer B
		if(allCandidate.get(Question.CHOICE_B).contains(x, y)){
			current.choice = Question.CHOICE_B;
		}
		
		// user choose answer C
		if(allCandidate.get(Question.CHOICE_C).contains(x, y)){
			current.choice = Question.CHOICE_C;
		}
	}
	
	private int dipToPs(float dip){
		final float scale = getResources().getDisplayMetrics().density;
		return (int)(dip * scale + 0.5f);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
	    mWidth = MeasureSpec.getSize(widthMeasureSpec);
	    mHeight = MeasureSpec.getSize(heightMeasureSpec);

	    setMeasuredDimension(mWidth, mHeight);
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
