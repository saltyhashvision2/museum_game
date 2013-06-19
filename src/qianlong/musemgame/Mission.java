package qianlong.musemgame;

import java.util.ArrayList;

public class Mission {
	ArrayList<Question> question;	//question of mission
	int	score;						//total score of mission
	
	private static final int QUESTION_NUM = 5; 
	//contructor
	public Mission()
	{
		this.question = new ArrayList<Question>(QUESTION_NUM);
		this.score = 0;
	}
}
