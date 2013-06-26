package qianlong.musemgame;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Rect;
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
	private static final int WAIT_BEFORE_QUESTION = 10;
	private static final int WAIT_AFTER_QUESTION = 10;
	private static final int WAIT_USER_CHOOSE = 30;
	public GameView(CreativeMusem father, int missionID) {
		super(father);				
		getHolder().addCallback(this);
		mission = new Mission(missionID);
		mission.initMission(father);			
		
		initRects();
		
		current = mission.question.get(questionID);
		/* init game settings */
		this.missionID = missionID;
		this.questionID = 1;
		this.status = BEFORE_QUESTION;
		
		dt = new DrawThread(this, getHolder());
		//this.father = father;
		//this.clubID = clubID;		//��o�Ѽֳ�logo		
		//ball = new Ball(this);		//�s�ب��y�����	
		//initPlayerInstance();		//��l�����趤��		
		//initGame();					//��l�ƹC��		
		//initBitmap(father);			//��l�ƹϤ��귽			
		
		//ait = new AIThread(this);	//�s��AI���R�����		
		//bm = new BonusManager(this);//��l��BonusManager		
		//dt = new DrawThread(this,getHolder());//�s�ث�O��s�ù������
	}
	
	/* screen drawing method */
	protected void doDraw(Canvas canvas){
		
		switch(status){
			case BEFORE_QUESTION:
				canvas.drawBitmap(current.antique.bmpAntique, 30, 30, null);
				timeCounter++;
				if(timeCounter == WAIT_BEFORE_QUESTION){
					status = BEGIN_QUESTION;
					timeCounter = 0;
				}
				break;
			case BEGIN_QUESTION:		
				canvas.drawBitmap(current.antique.bmpAntique, 30, 30, null);
				canvas.drawText(current.question, 30, 60, null);        //drawQuestionID();
				canvas.drawText(current.candidate.get(Question.CHOICE_A), 60, 30, null);
				canvas.drawText(current.candidate.get(Question.CHOICE_B), 60, 60, null);
				canvas.drawText(current.candidate.get(Question.CHOICE_C), 60, 90, null);
				timeCounter++;
				if(current.choice != 0){
					status = AFTER_QUESTION;
					timeCounter = 0;
				}
				
				if(timeCounter == WAIT_USER_CHOOSE){
					status = AFTER_QUESTION;
					timeCounter = 0;
					current.choice = Question.CHOICE_A;
				}
					
				break;
			case AFTER_QUESTION:
				canvas.drawBitmap(current.antique.bmpAntique, 30, 30, null);
				canvas.drawText(current.question,30,60,null);        //drawQuestionID();
				//this should be drawed according to answer's position
				canvas.drawText(current.candidate.get(current.choice), 60, 30, null);
				timeCounter++;
				if(timeCounter == WAIT_AFTER_QUESTION){
					timeCounter = 0;
					if(questionID == QUESTION_NUMBER){
						dt.isGameOn = false;
					}
					else{
						questionID++;
						current = mission.question.get(questionID);
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
        //father.pmt = new PlayerMoveThread(father);//��l�ƨñҰʲy�������ʳB�z�����
        //father.pmt.start();
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//dt.isGameOn = false;	//�����s�ù������������
		//father.pmt.flag = false;
	}
}
