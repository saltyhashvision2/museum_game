package qianlong.musemgame;

import android.graphics.Canvas;		
import android.view.SurfaceHolder;	

public class ObjectDescriptionDrawThread extends Thread{
	ObjectDescriptionView father;				
	SurfaceHolder surfaceHolder;	
	boolean flag;
	int sleepSpan = 100;		
	
	/* Constructor */
	public ObjectDescriptionDrawThread(ObjectDescriptionView father, SurfaceHolder surfaceHolder) {
		//super.setName("##-ObjectDescriptionDrawThread"); // set thread name, for debug
		this.father = father;
		this.surfaceHolder = surfaceHolder;
		flag = true;
	}
	
	public void run() {
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
