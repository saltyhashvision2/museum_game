package qianlong.musemgame;


import java.util.ArrayList;

public class Question {
	int answer;		 	//	correct answer id
	int	choice;		 	//	user's answer choice
	String question; 	//	question string
	ArrayList<String> candidate = new ArrayList<String>(3);	//answer candidate string
	Antique antique;

	public static final int CHOICE_A = 1;
	public static final int CHOICE_B = 2;
	public static final int CHOICE_C = 3;

	//constructor
	public Question(){
		this.answer = 0;
		this.choice = 0;
		this.antique = new Antique();
	}
}
