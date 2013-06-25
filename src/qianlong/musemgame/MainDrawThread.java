package qianlong.musemgame;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
/*
 * 該類別繼承自Thread，主要負責定時更新WelcomeView
 */
public class MainDrawThread extends Thread{
	MainView father;					//WelcomeView物件的引用
	SurfaceHolder surfaceHolder;		//WelcomeView物件的SurfaceHolder
	int sleepSpan = 100;				//休眠時間
	boolean flag;						//執行緒執行旗標位元
	//建構式：初始化主要的成員變數
	public MainDrawThread(MainView father,SurfaceHolder surfaceHolder){
		this.father = father;
		this.surfaceHolder = surfaceHolder;
		this.flag = true;
	}
	//方法：執行緒執行方法
	public void run(){
		Canvas canvas = null;			//新建一個Canvas物件
		while(flag){
			try{
				canvas = surfaceHolder.lockCanvas(null);//為畫布加鎖
				synchronized(surfaceHolder){
					father.doDraw(canvas);				//重新繪製螢幕
				}
			}
			catch(Exception e){
				e.printStackTrace();//捕獲異常並列印
			}
			finally{
				if(canvas != null){//釋放畫布並將其傳回
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			try{
				Thread.sleep(sleepSpan);		//休眠一段時間
			}
			catch(Exception e){
				e.printStackTrace();			//捕獲異常並列印
			}
		}
	}
}