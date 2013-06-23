package qianlong.musemgame;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	int missionID;
	int questionID;		// record the question number 
	int status;			// record the game progress 
	int timeCounter;	// current time counter 
	DrawThread dt;		// draw screen thread 
	ArrayList<Question> allQuestion = new ArrayList<Question>(5);
	ArrayList<Rect> allCandidate = new ArrayList<Rect>(3);
	Mission mission;	// mission
	Rect [] rectCandidate;
	//Rect rectQuestion;
	//Rect rectAntique;
	
	// game progress constant 
	private static final int BEFORE_QUESTION = 1;
	private static final int BEGIN_QUESTION = 2;
	private static final int AFTER_QUESTION = 3;
	
	//total question number 
	private static final int QUESTION_NUMBER = 5;
	
	//expire time between game status
	private static final int WAIT_BEFORE_QUESTION = 1;
	private static final int WAIT_AFTER_QUESTION = 1;
	private static final int WAIT_USER_CHOOSE = 5;
	public GameView(CreativeMusem father, int missionID) {
		super(father);				
		getHolder().addCallback(this);
		mission = new Mission(missionID);
		mission.initMission(father);			
		
		/* init game settings */
		this.missionID = missionID;
		this.questionID = 1;
		this.status = BEFORE_QUESTION;
		
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
		switch(status){
			case BEFORE_QUESTION:
				// draw question number animation 
				//drawQuestionID();
				
				break;
			case BEGIN_QUESTION:
				// draw question and candidate 
				//
				
				break;
			case AFTER_QUESTION:
				if(questionID == QUESTION_NUMBER){
					
				}
				else{
					questionID++;
					status = BEFORE_QUESTION;
				}
					
				break;
		}
		/* draw question animation */
		
		/* draw question */
		
		/* draw antique object */
	}
	
	//initialize touch rectangular range 
	public void initRects(){
		rectCandidate = new Rect[3];
    	for(int i=0;i<3;i++){
    		rectCandidate[i] = new Rect(244,200+40*i,280,236+40*i);
    	}
    	//rectQuestion = new Rect();
    	//rectAntique = new Rect();
    	
    }
	
	// Handle onTouchEvent in main activity
	public void touchEvent(int x, int y){
		
		// user choose answer A
		if(rectCandidate[0].contains(x, y)){
			allQuestion.get(questionID).choice = Question.CHOICE_A;
		}
		
		// user choose answer B
		if(rectCandidate[1].contains(x, y)){
			allQuestion.get(questionID).choice = Question.CHOICE_B;
		}
		
		// user choose answer C
		if(rectCandidate[2].contains(x, y)){
			allQuestion.get(questionID).choice = Question.CHOICE_C;
		}
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
		//if(!dt.isAlive()){
		//	dt.start();
		//}			 
        //father.pmt = new PlayerMoveThread(father);//初始化並啟動球員的移動處理執行緒
        //father.pmt.start();
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//dt.isGameOn = false;	//停止更新螢幕執行緒的執行
		//father.pmt.flag = false;
	}
}
