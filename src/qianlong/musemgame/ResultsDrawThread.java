package qianlong.musemgame;

import android.graphics.Canvas;		
import android.util.Log;
import android.view.SurfaceHolder;	

public class ResultsDrawThread extends Thread{
	ResultsView father;				
	SurfaceHolder surfaceHolder;	
	boolean flag;
	int sleepSpan = 100;		
	
	/* Constructor */
	public ResultsDrawThread(ResultsView father, SurfaceHolder surfaceHolder) {
		Log.d("DrawThread", "ResultsDrawThread is created...");
		super.setName("##-ResultsDrawThread"); // set thread name, for debug
		this.father = father;
		this.surfaceHolder = surfaceHolder;
		flag = true;
	}
	
	public void run() {
		Log.d("DrawThread", "ResultsDrawThread is running... (flag=" + flag + ")");
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
