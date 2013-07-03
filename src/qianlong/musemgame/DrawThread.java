package qianlong.musemgame;

import android.util.Log;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawThread extends Thread{
	GameView father;				
	SurfaceHolder surfaceHolder;	
	boolean isGameOn = true;
	int sleepSpan = 20;		
	
	/* contructor */
	public DrawThread(GameView father,SurfaceHolder surfaceHolder){
		Log.d("DrawThread", "DrawThread is created...");
		super.setName("##-DrawThread");			// set thread name, for debug
		this.father = father;				
		this.surfaceHolder = surfaceHolder;
		isGameOn = true;
	}
	
	public void run(){
		Log.d("DrawThread", "DrawThread is running... (isGameOn=" + isGameOn + ")");
		Canvas canvas = null;
		while(isGameOn){
			try{
				canvas = surfaceHolder.lockCanvas(null);	//lock canvas
				synchronized(surfaceHolder){
					father.doDraw(canvas);					//redraw canvas
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				if(canvas != null){		//unlock canvas and release
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			try{						
				Thread.sleep(sleepSpan);		//sleep for a while
			}
			catch(Exception e){
				e.printStackTrace();			
			}
		}
	}
}

