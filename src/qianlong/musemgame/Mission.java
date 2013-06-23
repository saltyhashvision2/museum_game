package qianlong.musemgame;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class Mission {
	ArrayList<Question> question;	//question of mission
	int missionID;					//id of mission
	int	score;						//total score of mission
	
	private static final int QUESTION_NUM = 5; 
	
	//Constructor
	public Mission(int missionID)
	{
		this.question = new ArrayList<Question>(QUESTION_NUM);
		this.score = 0;
		this.missionID = missionID;
	}
	
	//Initialize mission resource
	public void initMission(Context context)
	{
		Resources r = context.getResources();
		Question q1 = new Question();
		Question q2 = new Question();
		Question q3 = new Question();
		Question q4 = new Question();
		Question q5 = new Question();
		switch(missionID)
		{
			case 1:
				q1.antique.bmpAntique = BitmapFactory.decodeResource(r,R.drawable.ic_launcher);
				q1.answer = Question.CHOICE_A;
				q1.question =r.getString(R.string.m1_q1);
				q1.candidate.add(r.getString(R.string.m1_q1_a1));
				q1.candidate.add(r.getString(R.string.m1_q1_a2));
				q1.candidate.add(r.getString(R.string.m1_q1_a3));

				q2.antique.bmpAntique = BitmapFactory.decodeResource(r,R.drawable.ic_launcher);
				q2.answer = Question.CHOICE_C;
				q2.question = r.getString(R.string.m1_q2);
				q2.candidate.add(r.getString(R.string.m1_q2_a1));
				q2.candidate.add(r.getString(R.string.m1_q2_a2));
				q2.candidate.add(r.getString(R.string.m1_q2_a3));

				q3.antique.bmpAntique = BitmapFactory.decodeResource(r,R.drawable.ic_launcher);
				q3.answer = Question.CHOICE_B;
				q3.question = r.getString(R.string.m1_q3);
				q3.candidate.add(r.getString(R.string.m1_q3_a1));
				q3.candidate.add(r.getString(R.string.m1_q3_a2));
				q3.candidate.add(r.getString(R.string.m1_q3_a3));

				q4.antique.bmpAntique = BitmapFactory.decodeResource(r,R.drawable.ic_launcher);
				q4.answer = Question.CHOICE_C;
				q4.question = r.getString(R.string.m1_q4);
				q4.candidate.add(r.getString(R.string.m1_q4_a1));
				q4.candidate.add(r.getString(R.string.m1_q4_a2));
				q4.candidate.add(r.getString(R.string.m1_q4_a3));

				q5.antique.bmpAntique = BitmapFactory.decodeResource(r,R.drawable.ic_launcher);
				q5.answer = Question.CHOICE_B;
				q5.question = r.getString(R.string.m1_q5);
				q5.candidate.add(r.getString(R.string.m1_q5_a1));
				q5.candidate.add(r.getString(R.string.m1_q5_a2));
				q5.candidate.add(r.getString(R.string.m1_q5_a3));
				
				/* add all question to arraylist */
				question.add(q1);
				question.add(q2);
				question.add(q3);
				question.add(q4);
				question.add(q5);	
				break; 
		}
	}
}
