package qianlong.musemgame;

import android.util.Log;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainDrawThread extends Thread{
	MainView father;				
	SurfaceHolder surfaceHolder;	
	boolean flag;
	int sleepSpan = 100;		
	
	/* Constructor */
	public MainDrawThread(MainView father, SurfaceHolder surfaceHolder) {
		Log.d("DrawThread", "MainDrawThread is created...");
		super.setName("##-MainDrawThread"); // set thread name, for debug
		this.father = father;
		this.surfaceHolder = surfaceHolder;
		flag = true;
	}
	
	public void run() {
		Log.d("DrawThread", "MainDrawThread is running... (flag=" + flag + ")");
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
