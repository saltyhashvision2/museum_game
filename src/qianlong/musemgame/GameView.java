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
	int missionID;		// record the mission number 
	int questionID;		// record the question number 
	int status;			// record the game progress 
	int timeCounter;	// current time counter 
	DrawThread dt;		// draw screen thread 
	ArrayList<Question> allQuestion = new ArrayList<Question>(5);
	ArrayList<Rect> allCandidate = new ArrayList<Rect>(3);
	
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
		this.missionID = missionID;	/* get mission id */
		initMissionResource(father, missionID);			
		
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
    	Rect candidate = new Rect();
    	for(int i=0;i<3;i++){
    		//allCandidate.a = new Rect(244,200+40*i,280,236+40*i);
    	}
    	//rectQuestion = new Rect();
    	//rectAntique = new Rect();
    	
    }
	
	/* Initialize question and antiques according to mission id */
	public void initMissionResource(Context context,int missionID){
		Resources r = context.getResources();
		if(missionID == 1)
		{
			/* Mission one object */
			Question q1 = new Question();
			q1.bmpantique = BitmapFactory.decodeResource(r,R.drawable.ic_launcher);
			q1.answer = Question.CHOICE_A;
			q1.question = R.string.m1_q1;
			q1.candidate.add(R.string.m1_q1_a1);
			q1.candidate.add(R.string.m1_q1_a2);
			q1.candidate.add(R.string.m1_q1_a3);
			Question q2 = new Question();
			q2.bmpantique = BitmapFactory.decodeResource(r,R.drawable.ic_launcher);
			q2.answer = Question.CHOICE_C;
			q2.question = R.string.m1_q2;
			q2.candidate.add(R.string.m1_q2_a1);
			q2.candidate.add(R.string.m1_q2_a2);
			q2.candidate.add(R.string.m1_q2_a3);
			Question q3 = new Question();
			q3.bmpantique = BitmapFactory.decodeResource(r,R.drawable.ic_launcher);
			q3.answer = Question.CHOICE_B;
			q3.question = R.string.m1_q3;
			q3.candidate.add(R.string.m1_q3_a1);
			q3.candidate.add(R.string.m1_q3_a2);
			q3.candidate.add(R.string.m1_q3_a3);
			Question q4 = new Question();
			q4.bmpantique = BitmapFactory.decodeResource(r,R.drawable.ic_launcher);
			q4.answer = Question.CHOICE_C;
			q4.question = R.string.m1_q4;
			q4.candidate.add(R.string.m1_q4_a1);
			q4.candidate.add(R.string.m1_q4_a2);
			q4.candidate.add(R.string.m1_q4_a3);
			Question q5 = new Question();
			q5.bmpantique = BitmapFactory.decodeResource(r,R.drawable.ic_launcher);
			q5.answer = Question.CHOICE_B;
			q5.question = R.string.m1_q5;
			q5.candidate.add(R.string.m1_q5_a1);
			q5.candidate.add(R.string.m1_q5_a2);
			q5.candidate.add(R.string.m1_q5_a3);
			
			/* add all question to arraylist */
			allQuestion.add(q1);
			allQuestion.add(q2);
			allQuestion.add(q3);
			allQuestion.add(q4);
			allQuestion.add(q5);	
		}
		
		/* check if we need to separate mission to another class */
		
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
