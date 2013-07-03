package qianlong.musemgame;

import android.util.Log;
import android.graphics.Canvas;
import android.view.SurfaceHolder;	

public class GameDescriptionDrawThread extends Thread{
	GameDescriptionView father;				
	SurfaceHolder surfaceHolder;	
	boolean flag;
	int sleepSpan = 100;		
	
	/* Constructor */
	public GameDescriptionDrawThread(GameDescriptionView father, SurfaceHolder surfaceHolder) {
		Log.d("DrawThread", "GameDescriptionDrawThread is created...");
		super.setName("##-GameDescriptionDrawThread"); // set thread name, for debug
		this.father = father;
		this.surfaceHolder = surfaceHolder;
		flag = true;
	}
	
	public void run() {
		Log.d("DrawThread", "GameDescriptionDrawThread is running... (flag=" + flag + ")");
		Canvas canvas = null;
		while (flag) {
			try {
				canvas = surfaceHolder.lockCanvas(null); //lock canvas
				synchronized(surfaceHolder) {
					father.doDraw(canvas); //redraw canvas
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas); //unlock canvas and release
				}
			}
			
			try {						
				Thread.sleep(sleepSpan); //sleep for a while
			} catch(Exception e) {
				e.printStackTrace();			
			}
		}
	}
}
