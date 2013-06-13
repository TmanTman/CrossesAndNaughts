package com.codeconquerers.crossesandnaughts;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	FrameLayout fl;
	//LOG	private String TAG = "TmanLog";
	boolean Player1 = false;
	int[][] tttArray;
	private final static int CROSS = 1;
	private final static int NAUGHT = 2;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tttArray = new int[3][3];
		fl = (FrameLayout) findViewById(R.id.image);
		fl.addView(new Grid(fl.getContext()));
		fl.setOnTouchListener(new View.OnTouchListener() {		
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				//LOG	Log.d(TAG, "in onTouch method");
				//Prevent action from being on ACTION_DOWN and again on ACTION_UP
				if (e.getAction() == MotionEvent.ACTION_DOWN){
					float x = e.getX();
					float y = e.getY();
					drawSymbol(x, y);
				}
				return true;
			}
		});
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
	
	public void btnClick(View v){
		//LOG	Log.d(TAG, "in btnClick method");
		newGame();
	}
	
	public void drawSymbol (float x, float y){
		//LOG	Log.d(TAG, "in drawSymbol method");
    	int w = fl.getWidth();
    	int h = fl.getHeight();
    	int startXLeft = w / 8;
    	int startYTop = (h - w) / 2;
    	int l = w * 3 / 4; // length
    	//IMPROVEMENT: Single for loop that loops till 9, but then implements a break when correct block was found
    	//LOG	Log.d(TAG, "in onTouch method: Reached for loop");
    	for (int i = 0; i < 3; i++){
    		for (int j = 0; j < 3; j++){
    			//Check in which box the user touches
    			//LOG	Log.d(TAG, "in onTouch method: Reached inner loop");
    			if (x>(startXLeft+l*i/3) && x<(startXLeft+l*(i+1)/3) && y>(startYTop+l*j/3)&& y<(startYTop+l*(j+1)/3)){
    				if (Player1) {
    					//LOG	Log.d(TAG, "in onTouch method: Reached Player 1 commands");
    					fl.addView(new Naught(fl.getContext(), l/6+startXLeft+l*i/3, l/6+startYTop+l*j/3, 25));
    					//LOG	Log.d(TAG, "in onTouch method: Player 1 - Passed addView commands");
    					tttArray[i][j] = NAUGHT;
    					//LOG	Log.d(TAG, "in onTouch method: Player 1 - Passed insert CROSS commands");
    					
    				}
    				else {
    					//LOG	Log.d(TAG, "in onTouch method: Reached Player 2 commands");
    					fl.addView(new Cross(fl.getContext(), l/6+startXLeft+l*i/3, l/6+startYTop+l*j/3, 25));
    					//LOG	Log.d(TAG, "in onTouch method: Player 2 - Passed addView commands");
    					tttArray[i][j] = CROSS;
    					//LOG	Log.d(TAG, "in onTouch method: Player 1 - Passed insert CROSS commands");
    				}    			
    			}
    		}
    	}
    	//LOG	Log.d(TAG, "int onTouch method: Made Naught/Cross");
		Player1 = !Player1;
		checkStraight();
		checkDiagonal();
	}
	
	public void checkStraight (){
		//LOG	Log.d(TAG, "in checkStraight method");
		int counthorC = 0;//Count horizontal crosses
		int counthorN = 0;//Count horizontal naughts
		int countvertC = 0; //Count vertical crosses
		int countvertN=  0; //Count vertical naughts
		for (int i = 0; i < 3; i++){
			counthorC = 0;
			counthorN = 0;
			countvertC = 0;
			countvertN = 0;
			for (int j = 0; j < 3; j++){
				if (tttArray[i][j] == CROSS){counthorC++;}
				else if (tttArray[i][j] == NAUGHT){counthorN++;}
				if (tttArray[j][i] == CROSS){countvertC++;}
				else if (tttArray[j][i] == NAUGHT){countvertN++;}
			}
			if (counthorC == 3||countvertC == 3 || counthorN == 3||countvertN == 3) {
				if (counthorC == 3||countvertC == 3){Toast.makeText(MainActivity.this, "Cross Wins", Toast.LENGTH_LONG).show();}
				else if (counthorN == 3||countvertN == 3){Toast.makeText(MainActivity.this, "Naughts Wins", Toast.LENGTH_LONG).show();}
				//IMPROVEMENT: After a player wins, automatically make new game. But draw the last naught/cross before new game starts
//				try{
//				Thread.sleep(1000);}
//				catch (Exception e){
//					Toast.makeText(MainActivity.this, "Exception Error", Toast.LENGTH_LONG).show();
//				}
//				newGame();
				break;
			}
		}
	}
	
	public void checkDiagonal(){
		int countBackslashDiag = 0;
		int countForwardslashDiag = 0;
		for (int i = 0; i < 3; i++){
			if (tttArray[i][i] == CROSS) countBackslashDiag++;
			else if (tttArray[i][i] == NAUGHT) countBackslashDiag--;
			if (tttArray[2-i][i] == CROSS) countForwardslashDiag++;
			else if (tttArray[2-i][i] == NAUGHT) countForwardslashDiag--;
		}
		if (countBackslashDiag == 3 || countForwardslashDiag == 3) {Toast.makeText(MainActivity.this, "Cross Wins", Toast.LENGTH_LONG).show();}
		else if (countBackslashDiag == -3 || countForwardslashDiag == -3) {Toast.makeText(MainActivity.this, "Naught Wins", Toast.LENGTH_LONG).show();}
		
	}
	

	public void newGame(){
		//LOG	Log.d(TAG, "in newGame method");
		Player1 = false;
		for (int i = 0; i < 3; i++){
			for (int j= 0; j < 3; j++){
				tttArray[i][j] = 0;
			}
		}
		fl.removeAllViews();
		fl.addView(new Grid(fl.getContext()));
	}
		
			

}
